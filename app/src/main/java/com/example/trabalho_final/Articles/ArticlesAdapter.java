package com.example.trabalho_final.Articles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_final.ArticleDetails.ArticleDetailsFragment;
import com.example.trabalho_final.Details.DetailFragment;
import com.example.trabalho_final.Models.ResponseModelsArticles;

import com.example.trabalho_final.R;
import com.squareup.picasso.Picasso;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder> {
    ResponseModelsArticles.Articles articles;
    Context context;

    public ArticlesAdapter(Context ct, ResponseModelsArticles.Articles articles) {

        this.context = ct;
        this.articles = articles;

    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.questions_row, parent, false);
        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        ResponseModelsArticles.Item item = articles.items.get(position);
        Picasso.get().load(item.owner.profile_image).into(holder.imageView);
        holder.textView.setText(item.title);

        holder.itemView.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            ArticleDetailsFragment articleDetailsFragment = new ArticleDetailsFragment(item.article_id);
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.mQuestions, articleDetailsFragment).addToBackStack(null).commit();
        });



    }

    @Override
    public int getItemCount() {
        if(articles == null || articles.items == null)
            return 0;
        return articles.items.size();
    }

    public static class ArticlesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
