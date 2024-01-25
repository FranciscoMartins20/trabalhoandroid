package com.example.trabalho_final.Comunication;

import android.widget.EditText;

import com.example.trabalho_final.Models.ResponseModelsArticles;
import com.example.trabalho_final.Models.ResponseModelsArticlesDetails;
import com.example.trabalho_final.Models.ResponseModelsQuestions;
import com.example.trabalho_final.Models.ResponseModelsQuestionsDetails;
import com.example.trabalho_final.Models.ResponseModelsSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://api.stackexchange.com/2.3/";


    //Questões
    @GET("questions?order=desc&sort=votes&site=stackoverflow")
    Call<ResponseModelsQuestions.Questions> getQuestions();

    //Artigos
    @GET("articles?order=desc&sort=votes&site=stackoverflow")
    Call<ResponseModelsArticles.Articles> getArticles();

    //DetalhesQuestoes
    @GET("questions/{id}?order=desc&sort=activity&site=stackoverflow&filter=!6VvPDzPyz3tML")
    Call<ResponseModelsQuestionsDetails.QuestionsDetails> getQuestionsDetails(@Path("id") int id);

    //DetalhesArtigos
    @GET("articles/{id}?order=desc&sort=activity&site=stackoverflow&filter=!nKzQUR9j)M")
    Call<ResponseModelsArticlesDetails.ArticlesDetails> getArticlesDetails(@Path("id") int id);

 //SearchBar
    @GET("search?order=desc&sort=activity&site=stackoverflow")
    Call<ResponseModelsSearch.Search> getSearch(@Query("intitle") String intitle);
}
