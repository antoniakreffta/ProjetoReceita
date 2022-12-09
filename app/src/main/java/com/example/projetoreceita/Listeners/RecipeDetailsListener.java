package com.example.projetoreceita.Listeners;

import com.example.projetoreceita.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
