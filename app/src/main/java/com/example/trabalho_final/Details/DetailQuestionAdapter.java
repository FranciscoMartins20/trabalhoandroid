package com.example.trabalho_final.Details;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_final.Models.ResponseModelsQuestionsDetails;
import com.example.trabalho_final.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailQuestionAdapter extends RecyclerView.Adapter <DetailQuestionAdapter.QuestionsDetailsViewHolder> {
    ArrayList<ResponseModelsQuestionsDetails.Answer> answers;
    Context context;

    public DetailQuestionAdapter (Context ct, ArrayList<ResponseModelsQuestionsDetails.Answer> answers) {
        this.context = ct;
        this.answers = answers;
    }

    @NonNull
    @Override
    public DetailQuestionAdapter.QuestionsDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.answer_row, parent, false);
        return new DetailQuestionAdapter.QuestionsDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsDetailsViewHolder holder, int position) {
        ResponseModelsQuestionsDetails.Answer answer = answers.get(position);
        Picasso.get().load(answer.owner.profile_image).into(holder.imageView);
        holder.textView.setText(Html.fromHtml(answer.body));
    }

    @Override
    public int getItemCount() {
        if(answers == null)
            return 0;
        return answers.size();
    }

    public class QuestionsDetailsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public QuestionsDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
