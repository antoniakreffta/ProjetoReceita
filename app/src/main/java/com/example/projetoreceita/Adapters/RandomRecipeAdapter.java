package com.example.projetoreceita.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoreceita.Listeners.RecipeClickListener;
import com.example.projetoreceita.Models.Recipe;
import com.example.projetoreceita.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeAdapter.RandomRecipeViewHolder>{
    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));

        View v = LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false);
        return new RandomRecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.title_textView.setText(list.get(position).title);
        holder.title_textView.setSelected(true);
        Picasso.get().load(list.get(position).image).into(holder.recipe_imageView);

        holder.random_list_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class RandomRecipeViewHolder extends RecyclerView.ViewHolder {

        CardView random_list_container;
        TextView title_textView;
        ImageView recipe_imageView;

        public RandomRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            random_list_container = itemView.findViewById(R.id.random_list_container);
            title_textView = itemView.findViewById(R.id.title_textView);
            recipe_imageView = itemView.findViewById(R.id.recipe_imageView);

        }
    }
}


