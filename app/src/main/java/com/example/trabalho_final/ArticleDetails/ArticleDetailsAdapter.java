package com.example.trabalho_final.ArticleDetails;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_final.Models.ResponseModelsArticles;
import com.example.trabalho_final.Models.ResponseModelsArticlesDetails;
import com.example.trabalho_final.Models.ResponseModelsQuestionsDetails;
import com.example.trabalho_final.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleDetailsAdapter extends RecyclerView.Adapter <ArticleDetailsAdapter.ArticleDetailsViewHolder> {
    ArrayList<ResponseModelsArticlesDetails.Item> items;
    Context context;

    public ArticleDetailsAdapter(Context ct, ArrayList<ResponseModelsArticlesDetails.Item> items) {
        this.context = ct;
        this.items = items;
    }

    @NonNull
    @Override
    public ArticleDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_article_detail, parent, false);
        return new ArticleDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleDetailsViewHolder holder, int position) {
        ResponseModelsArticlesDetails.Item Item = items.get(position);
        Picasso.get().load(Item.owner.profile_image).into(holder.imageView);
        holder.textView.setText(Html.fromHtml(Item.body));
    }



    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        return items.size();
    }

    public class ArticleDetailsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ArticleDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ownerImageArticle);
            textView = itemView.findViewById(R.id.textBodyArticle);
        }
    }
}

