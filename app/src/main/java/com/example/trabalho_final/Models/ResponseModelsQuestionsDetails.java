package com.example.trabalho_final.Models;

import java.util.ArrayList;

public class ResponseModelsQuestionsDetails {
    public class Answer{
        public Owner owner;
        public boolean is_accepted;
        public int score;
        public int last_activity_date;
        public int creation_date;
        public int answer_id;
        public int question_id;
        public String content_license;
        public String body;
    }

    public class Item{
        public ArrayList<String> tags;
        public ArrayList<Answer> answers;
        public Owner owner;
        public boolean is_answered;
        public int view_count;
        public int accepted_answer_id;
        public int answer_count;
        public int score;
        public int last_activity_date;
        public int creation_date;
        public int question_id;
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
        public int accept_rate;
        public String profile_image;
        public String display_name;
        public String link;
    }

    public class QuestionsDetails{
        public ArrayList<Item> items;
        public boolean has_more;
        public int backoff;
        public int quota_max;
        public int quota_remaining;
    }
}
