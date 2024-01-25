package com.example.trabalho_final.Models;

import java.util.ArrayList;

public class ResponseModelsArticles {
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
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
        public String link;
        public String title;
    }

    public class Owner{
        public int account_id;
        public int reputation;
        public int user_id;
        public String user_type;
        public String profile_image;
        public String display_name;
        public String link;
        public int accept_rate;
    }

    public class Articles{
        public ArrayList<Item> items;
        public boolean has_more;
        public int quota_max;
        public int quota_remaining;
    }


}
