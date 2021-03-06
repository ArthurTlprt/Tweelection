/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetExtraction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;

/**
 * Classe utilisée pour récupérer et sauvegarder les tweets sur un sujet
 * Sur une période
 */
public class TweetAboutSubject {

    private static final int hourInMilli = 600000;

    private final String candidateName;

    private Query query;
    private final List<String> dates;
    private final List<String> texts;

    private final ConfigurationBuilder cb;
    private final TwitterFactory tf;
    private final Twitter twitter;

    private List<String> period;

    public TweetAboutSubject(String candidateName, List<String> period) throws ParseException {
        this.candidateName = candidateName;
        this.query = new Query(candidateName);
        this.dates = new ArrayList<>();
        this.texts = new ArrayList<>();
        this.setPeriod(period);

        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ZroXRDzuSrvwL6hYhgizrkQtR")
                .setOAuthConsumerSecret("OpK1ze2cdxM9ImgFdPsuhJaxwJJSld2rPrkjK7AZdDisFeCn1C")
                .setOAuthAccessToken("849890633770815488-wstUiGK2ydWmCcJdpdhjo8mnUBoobrw")
                .setOAuthAccessTokenSecret("TLho2WgfGxtmWNB3o9BLVxqb1rJzJmBLHmbyDFgCNit63");
        tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();

        this.extractThisDay();
    }

    
    public void setPeriod(List<String> period) { this.period = period; }

    public List<String> getDates() { return dates; }
    public List<String> getTexts() { return texts; }
    
    /* Récupère les tweets sur toute la période réglée */
    public void extractThisDay() throws ParseException {
        period.forEach((day) -> {
            if (isNotExtracted(day)) {
                try {
                    query = new Query(candidateName);
                    query.setCount(100);
                    System.out.println(day);
                    query.setUntil(day);
                    QueryResult result;
                    result = twitter.search(query);

                    for (int i = 0; i < 2; i++) {
                        query = result.nextQuery();
                        result = twitter.search(query);
                        for (Status status : result.getTweets()) {
                            texts.add(status.getText());
                        }
                    }
                    writeInFile(day);
                    this.texts.clear();

                } catch (TwitterException e) {
                    System.err.println("In extractTweet date " + e);
                }
            }
        });
    }
    
    /* Vérifie si le jour a déjà été traité lors d'une utilisation antérieure du programme */
    public Boolean isNotExtracted(String day) {
        String path = "tweets_files/" + candidateName + "/" + day;
        File fileToSaveTweets = new File(path);

        File folderToSaveTweets = new File("tweets_files/" + candidateName);
        if (!folderToSaveTweets.exists() || !folderToSaveTweets.isDirectory()) {
            folderToSaveTweets.mkdir();
        }

        return !fileToSaveTweets.exists();
    }

    /* Sauvegarde les tweets trouvés dans un fichier */
    /* tweet_files/nom_du_sujet/YYYY-MM-DD */
    private void writeInFile(String date) {
        String path = "tweets_files/" + candidateName + "/" + date;

        try {
            PrintWriter writer;
            writer = new PrintWriter(path, "UTF-8");
            System.out.println("New File created");
            texts.forEach((text) -> {
                writer.println(text);
            });

            writer.close();

        } catch (IOException e) {
            System.err.println("Unable to write in the file");
        }
    }
}
