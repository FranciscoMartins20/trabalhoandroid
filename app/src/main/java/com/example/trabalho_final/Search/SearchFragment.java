package com.example.trabalho_final.Search;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trabalho_final.Comunication.RetrofitClient;
import com.example.trabalho_final.HomePage.HomeAdapter;
import com.example.trabalho_final.Models.ResponseModelsQuestions;
import com.example.trabalho_final.Models.ResponseModelsSearch;
import com.example.trabalho_final.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerViewSearch);
        SearchView searchView = (SearchView) view.findViewById(R.id.editText);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s != null && s != "") {
                    ObterQuestoes(view, recyclerView, s);
                }
                return true;
            }
        });


        ObterQuestoes(view, recyclerView, null);

        return view;
    }

    private void ObterQuestoes(View view, RecyclerView recyclerView, String queryQuestao) {
        Call<ResponseModelsSearch.Search> call = RetrofitClient.getInstance().getMyApi().getSearch(queryQuestao);
        call.enqueue(new Callback<ResponseModelsSearch.Search>() {
            @Override
            public void onResponse(Call<ResponseModelsSearch.Search> call, Response<ResponseModelsSearch.Search> response) {
                ResponseModelsSearch.Search searchObject = response.body();
                SearchAdapter searchAdapter = new SearchAdapter(view.getContext(), searchObject);
                recyclerView.setAdapter(searchAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            }

            @Override
            public void onFailure(Call<ResponseModelsSearch.Search> call, Throwable t) {
                Toast.makeText(view.getContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });
    }

}