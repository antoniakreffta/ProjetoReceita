package com.example.projetoreceita.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoreceita.Models.RecipeInstructionsResponse;
import com.example.projetoreceita.R;

import java.util.List;

public class RecipeInstructionsAdapter extends RecyclerView.Adapter<RecipeInstructionsViewHolder>{

    Context context;
    List<RecipeInstructionsResponse> list;

    public RecipeInstructionsAdapter(Context context, List<RecipeInstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecipeInstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeInstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeInstructionsViewHolder holder, int position) {
        holder.textView_instruction_name.setText(list.get(position).name);
        holder.recycler_instruction_steps.setHasFixedSize(true);
        holder.recycler_instruction_steps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        RecipeInstructionStepAdapter stepAdapter = new RecipeInstructionStepAdapter(context, list.get(position).steps);
        holder.recycler_instruction_steps.setAdapter(stepAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RecipeInstructionsViewHolder extends RecyclerView.ViewHolder {

    TextView textView_instruction_name;
    RecyclerView recycler_instruction_steps;

    public RecipeInstructionsViewHolder(@NonNull View itemView) {
        super(itemView);

        textView_instruction_name = itemView.findViewById(R.id.textView_instruction_name);
        recycler_instruction_steps = itemView.findViewById(R.id.recycler_instruction_steps);

    }
}