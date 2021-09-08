package connection;

import java.util.Random;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.*;

public class Anime extends Kitsu {
    static Random rand = new Random();
    String name;
    String en_name;
    String jp_name;
    String start_date;
    String end_date;
    String retStr;
    String small_img_link;
    String medium_img_link;
    String large_img_link;
    HashMap<String, String> map;
    int ratingRank;
    int popularityRank;
    int episode_no;
    int kitsuId;
    double averageRating;

    public Anime () {
        this (rand.nextInt(1000));
    }

    public Anime (int rand_id) {
        map = get_anime_data (rand_id);
        kitsuId = Integer.parseInt (map.get ("id"));

        name = map.get ("en_jp");
        en_name = map.get ("en");
        jp_name = map.get ("ja_jp");

        ratingRank = Integer.parseInt (map.get ("ratingRank"));
        popularityRank = Integer.parseInt (map.get ("popularityRank"));
        averageRating = Double.parseDouble (map.get ("averageRating"));

        episode_no = Integer.parseInt (map.get ("episodeCount"));
        start_date = map.get ("startDate");
        end_date = map.get ("endDate");

        small_img_link = map.get ("tiny");
        medium_img_link = map.get ("medium");
        large_img_link = map.get ("large");
    }

    public int get_kitsu_id() {
        return kitsuId;
    }

    public String get_name() {
        return name;
    }

    public String get_en_name() {
        return en_name;
    }

    public String get_jp_name() {
        return jp_name;
    }

    public int get_rating_rank() {
        return ratingRank;
    }

    public int get_popularity_rank() {
        return popularityRank;
    }

    public double get_average_rating() {
        return averageRating;
    }

    public int get_episode_no() {
        return episode_no;
    }

    public String get_start_date() {
        return start_date;
    }

    public String get_end_date() {
        return end_date;
    }

    public String get_small_img_link() {
        return small_img_link;
    }

    public String get_medium_img_link() {
        return medium_img_link;
    }

    public String get_large_img_link() {
        return large_img_link;
    }

    public boolean download_small_img(String dest) {
        return download_img (0, dest);
    }

    public boolean download_medium_img(String dest) {
        return download_img (1, dest);
    }

    public boolean download_large_img(String dest) {
        return download_img (2, dest);
    }

    protected boolean download_img(int size, String dest) {
        URL url = null;

        try {
            switch (size) {
                case 0:
                    url = new URL("https://" + small_img_link);
                    break;
                case 1:
                    url = new URL("https://" + medium_img_link);
                    break;
                case 2:
                    url = new URL("https://" + large_img_link);
                    break;
            }

            InputStream in = new BufferedInputStream (url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n=in.read(buf))) {
                out.write (buf, 0, n);
            }

            out.close();
            in.close();
            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(dest + kitsuId + ".jpg");
            fos.write (response);
            fos.close();

        } catch (IOException ex) {
            System.out.println (ex);
            return false;
        }

        return true;
    }

    public String toString() {
        if (retStr != null) {
            return retStr;
        }

        retStr = "";
        retStr += "Kitsu ID: " + kitsuId + "\n";
        retStr += "Name: " + name + "\n";
        retStr += "EN name: " + en_name + "\n";
        retStr += "JP name: " + jp_name + "\n";
        retStr += "Start Date: " + start_date + "\n";
        retStr += "End Date: " + end_date + "\n";
        retStr += "Average Rating: " + averageRating + "\n";
        retStr += "Rating Rank: " + ratingRank + "\n";
        retStr += "Popularity Rank: " + popularityRank + "\n";
        retStr += "No. of Episodes: " + episode_no + "\n";

        return retStr;
    }
}
