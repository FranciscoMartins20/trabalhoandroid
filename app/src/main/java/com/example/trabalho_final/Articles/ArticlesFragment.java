package com.example.trabalho_final.Articles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_final.Comunication.RetrofitClient;
import com.example.trabalho_final.HomePage.HomeAdapter;
import com.example.trabalho_final.Models.ResponseModelsArticles;
import com.example.trabalho_final.Models.ResponseModelsQuestions;
import com.example.trabalho_final.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArticlesFragment extends Fragment {


    public ArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);

        Call<ResponseModelsArticles.Articles> call = RetrofitClient.getInstance().getMyApi().getArticles();
        call.enqueue(new Callback<ResponseModelsArticles.Articles>() {
            @Override
            public void onResponse(Call<ResponseModelsArticles.Articles> call, Response<ResponseModelsArticles.Articles> response) {
                ResponseModelsArticles.Articles articlesObject = response.body();
                ArticlesAdapter articlesAdapter = new ArticlesAdapter(view.getContext(), articlesObject);
                recyclerView.setAdapter(articlesAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            }

            @Override
            public void onFailure(Call<ResponseModelsArticles.Articles> call, Throwable t) {
                Toast.makeText(view.getContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}