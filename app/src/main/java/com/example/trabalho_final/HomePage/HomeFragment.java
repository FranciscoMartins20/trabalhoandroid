package com.example.trabalho_final.HomePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trabalho_final.Comunication.RetrofitClient;
import com.example.trabalho_final.Models.ResponseModelsQuestions;
import com.example.trabalho_final.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    public HomeFragment() {
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

        Call<ResponseModelsQuestions.Questions> call = RetrofitClient.getInstance().getMyApi().getQuestions();
        call.enqueue(new Callback<ResponseModelsQuestions.Questions>() {
            @Override
            public void onResponse(Call<ResponseModelsQuestions.Questions> call, Response<ResponseModelsQuestions.Questions> response) {
                ResponseModelsQuestions.Questions questionsObject = response.body();
                //Inserir dados caso o limite de pedidos tenha sido alcançado
                if(questionsObject == null)
                {
                    questionsObject = new ResponseModelsQuestions.Questions();
                    questionsObject.items = new ArrayList<ResponseModelsQuestions.Item>();

                    ResponseModelsQuestions.Item item = new ResponseModelsQuestions.Item();
                    item.title = "TESTE";
                    item.owner = new ResponseModelsQuestions.Owner();
                    questionsObject.items.add(item);
                }
                HomeAdapter homeAdapter = new HomeAdapter(view.getContext(), questionsObject);
                recyclerView.setAdapter(homeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            }

            @Override
            public void onFailure(Call<ResponseModelsQuestions.Questions> call, Throwable t) {
                Toast.makeText(view.getContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}