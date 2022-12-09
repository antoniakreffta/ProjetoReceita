package com.example.projetoreceita.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoreceita.Models.Step;
import com.example.projetoreceita.R;

import java.util.List;

public class RecipeInstructionStepAdapter extends RecyclerView.Adapter<RecipeInstructionStepViewHolder>{

    Context context;
    List<Step> list;

    public RecipeInstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecipeInstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeInstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_steps, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeInstructionStepViewHolder holder, int position) {
        holder.textView_instructions_step_number.setText(String.valueOf(list.get(position).number));
        holder.textView_instructions_step_title.setText(list.get(position).step);
        holder.recycler_instruction_ingredients.setHasFixedSize(true);
        holder.recycler_instruction_ingredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        RecipeInstructionsIngredientsAdapter recipeInstructionsIngredientsAdapter = new RecipeInstructionsIngredientsAdapter(context, list.get(position).ingredients);
        holder.recycler_instruction_ingredients.setAdapter(recipeInstructionsIngredientsAdapter);
        holder.recycler_instruction_equipments.setHasFixedSize(true);
        holder.recycler_instruction_equipments.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        RecipeInstructionsEquipmentsAdapter recipeInstructionsEquipmentsAdapter = new RecipeInstructionsEquipmentsAdapter(context, list.get(position).equipment);
        holder.recycler_instruction_equipments.setAdapter(recipeInstructionsEquipmentsAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RecipeInstructionStepViewHolder extends RecyclerView.ViewHolder {

    TextView textView_instructions_step_number, textView_instructions_step_title;
    RecyclerView recycler_instruction_equipments, recycler_instruction_ingredients;

    public RecipeInstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_instructions_step_number = itemView.findViewById(R.id.textView_instructions_step_number);
        textView_instructions_step_title = itemView.findViewById(R.id.textView_instructions_step_title);
        recycler_instruction_equipments = itemView.findViewById(R.id.recycler_instruction_equipments);
        recycler_instruction_ingredients = itemView.findViewById(R.id.recycler_instruction_ingredients);
    }
}
