package com.example.projetoreceita.Listeners;

import com.example.projetoreceita.Models.RecipeInstructionsResponse;

import java.util.List;

public interface RecipeInstructionsListener {
    void didFetch(List<RecipeInstructionsResponse> response, String message);
    void didError(String message);
}
