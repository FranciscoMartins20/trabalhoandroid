package com.example.trabalho_final.HomePage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_final.Models.ResponseModelsQuestions;
import com.example.trabalho_final.R;
import com.squareup.picasso.Picasso;

import com.example.trabalho_final.Details.DetailFragment;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    ResponseModelsQuestions.Questions questions;
    Context context;

    public HomeAdapter(Context ct, ResponseModelsQuestions.Questions questions) {

        this.context = ct;
        this.questions = questions;

    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.questions_row, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        ResponseModelsQuestions.Item item = questions.items.get(position);
        Picasso.get().load(item.owner.profile_image).into(holder.imageView);
        holder.textView.setText(item.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                DetailFragment detailFragment = new DetailFragment(item.question_id);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mQuestions, detailFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(questions == null || questions.items == null)
            return 0;
        return questions.items.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
