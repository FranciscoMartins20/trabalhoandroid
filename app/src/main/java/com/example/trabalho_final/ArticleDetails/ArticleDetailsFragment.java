package com.example.trabalho_final.ArticleDetails;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.trabalho_final.Comunication.RetrofitClient;

import com.example.trabalho_final.Models.ResponseModelsArticlesDetails;

import com.example.trabalho_final.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDetailsFragment extends Fragment {
    int ArticleId;
    ImageView ownerArticleImage;
    TextView titleArticle;
    TextView bodyArticle;

    public ArticleDetailsFragment(int ArticleId) {
        this.ArticleId = ArticleId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail,container,false);

        ownerArticleImage = (ImageView) view.findViewById(R.id.ownerImageArticle);
        titleArticle = (TextView)  view.findViewById(R.id.titleArticle);
        bodyArticle = (TextView)  view.findViewById(R.id.textBodyArticle);


        Call<ResponseModelsArticlesDetails.ArticlesDetails> call = RetrofitClient.getInstance().getMyApi().getArticlesDetails(ArticleId);
        call.enqueue(new Callback<ResponseModelsArticlesDetails.ArticlesDetails>() {
            @Override
            public void onResponse(Call<ResponseModelsArticlesDetails.ArticlesDetails> call, Response<ResponseModelsArticlesDetails.ArticlesDetails> response) {
                ResponseModelsArticlesDetails.ArticlesDetails articlesDetailsObject = response.body();
                if(articlesDetailsObject.items.size() > 0){
                    ResponseModelsArticlesDetails.Item item = articlesDetailsObject.items.get(0);
                    ResponseModelsArticlesDetails.Owner owner = item.owner;
                    Picasso.get().load(owner.profile_image).into(ownerArticleImage);
                    titleArticle.setText(item.title);
                    bodyArticle.setText(Html.fromHtml(item.body));


                }
            }

            @Override
            public void onFailure(Call<ResponseModelsArticlesDetails.ArticlesDetails> call, Throwable t) {
                Toast.makeText(view.getContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}