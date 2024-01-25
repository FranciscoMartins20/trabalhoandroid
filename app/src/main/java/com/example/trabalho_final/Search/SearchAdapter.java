package com.example.trabalho_final.Search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_final.Details.DetailFragment;
import com.example.trabalho_final.Models.ResponseModelsSearch;
import com.example.trabalho_final.R;
import com.squareup.picasso.Picasso;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    ResponseModelsSearch.Search search;
    Context context;

    public SearchAdapter(Context ct, ResponseModelsSearch.Search search) {

        this.context = ct;
        this.search = search;

    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.questions_row, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        ResponseModelsSearch.Item item = search.items.get(position);
        Picasso.get().load(item.owner.profile_image).into(holder.imageView);
        holder.textView.setText(item.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                DetailFragment detailFragment = new DetailFragment(item.question_id);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mSearch, detailFragment).addToBackStack(null).commit();
            }
        });
    }



    @Override
    public int getItemCount() {
        if(search == null || search.items == null)
            return 0;
        return search.items.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
