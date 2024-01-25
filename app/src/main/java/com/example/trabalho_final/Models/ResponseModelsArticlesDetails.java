package com.example.trabalho_final.Models;

import java.util.ArrayList;

public class ResponseModelsArticlesDetails {
    public class Item{
        public ArrayList<String> tags;
        public Owner owner;
        public int view_count;
        public int score;
        public int last_activity_date;
        public int creation_date;
        public int last_edit_date;
        public int article_id;
        public String article_type;
        public String body_markdown;
        public String link;
        public String title;
        public String body;
    }

    public class Owner{
        public int account_id;
        public int reputation;
        public int user_id;
        public String user_type;
        public String profile_image;
        public String display_name;
        public String link;
    }

    public class ArticlesDetails{
        public ArrayList<Item> items;
        public boolean has_more;
        public int quota_max;
        public int quota_remaining;
    }
}
