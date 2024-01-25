package com.example.trabalho_final.Models;

import java.util.ArrayList;

public class ResponseModelsQuestions {

        public static class Item{
            public ArrayList<String> tags;
            public Owner owner;
            public boolean is_answered;
            public int view_count;
            public int answer_count;
            public int score;
            public int last_activity_date;
            public int creation_date;
            public int question_id;
            public String content_license;
            public String link;
            public String title;
            public int last_edit_date;
            public int accepted_answer_id;
            public int bounty_amount;
            public int bounty_closes_date;
        }

        public static class Owner{
            public int account_id;
            public int reputation;
            public int user_id;
            public String user_type;
            public String profile_image;
            public String display_name;
            public String link;
            public int accept_rate;
        }

        public static class Questions{
            public ArrayList<Item> items;
            public boolean has_more;
            public int quota_max;
            public int quota_remaining;
        }
}
