package com.example.trabalho_final.Details;

import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalho_final.Comunication.RetrofitClient;
import com.example.trabalho_final.HomePage.HomeAdapter;
import com.example.trabalho_final.Models.ResponseModelsQuestions;
import com.example.trabalho_final.Models.ResponseModelsQuestionsDetails;
import com.example.trabalho_final.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {
    int questaoId;
    RecyclerView recyclerViewAnswers;
    ImageView ownerQuestionImage;
    TextView titleQuestion;
    TextView bodyQuestion;

    public DetailFragment(int questaoId) {
        this.questaoId = questaoId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ownerQuestionImage = (ImageView) view.findViewById(R.id.ownerQuestionImage);
        titleQuestion = (TextView) view.findViewById(R.id.titleQuestion);
        bodyQuestion = (TextView) view.findViewById(R.id.bodyQuestion);
        recyclerViewAnswers = (RecyclerView) view.findViewById(R.id.recyclerViewAnswers);

        Call<ResponseModelsQuestionsDetails.QuestionsDetails> call = RetrofitClient.getInstance().getMyApi().getQuestionsDetails(questaoId);
        call.enqueue(new Callback<ResponseModelsQuestionsDetails.QuestionsDetails>() {
            @Override
            public void onResponse(Call<ResponseModelsQuestionsDetails.QuestionsDetails> call, Response<ResponseModelsQuestionsDetails.QuestionsDetails> response) {
                ResponseModelsQuestionsDetails.QuestionsDetails questionsDetailsObject = response.body();
                if (questionsDetailsObject != null && questionsDetailsObject.items.size() > 0) {
                    ResponseModelsQuestionsDetails.Item item = questionsDetailsObject.items.get(0);
                    ResponseModelsQuestionsDetails.Owner owner = item.owner;
                    Picasso.get().load(owner.profile_image).into(ownerQuestionImage);
                    titleQuestion.setText(item.title);
                    bodyQuestion.setText(Html.fromHtml(item.body));
                    DetailQuestionAdapter detailQuestionAdapter = new DetailQuestionAdapter(view.getContext(), item.answers);
                    recyclerViewAnswers.setAdapter(detailQuestionAdapter);
                    recyclerViewAnswers.setLayoutManager(new LinearLayoutManager(view.getContext()));
                }
            }

            @Override
            public void onFailure(Call<ResponseModelsQuestionsDetails.QuestionsDetails> call, Throwable t) {
                Toast.makeText(view.getContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

}