package com.example.projetoreceita;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.projetoreceita.Listeners.RandomRecipeResponseListener;
import com.example.projetoreceita.Listeners.RecipeDetailsListener;
import com.example.projetoreceita.Listeners.RecipeInstructionsListener;
import com.example.projetoreceita.Models.RandomRecipeApiResponse;
import com.example.projetoreceita.Models.RecipeDetailsResponse;
import com.example.projetoreceita.Models.RecipeInstructionsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(FragmentActivity context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "3");
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id) {
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeInstructions(RecipeInstructionsListener listener, int id) {
        CallRecipeInstructions callRecipeInstructions = retrofit.create(CallRecipeInstructions.class);
        Call<List<RecipeInstructionsResponse>> call = callRecipeInstructions.callRecipeInstructions(id, context.getString(R.string.api_key));
        call.enqueue(new Callback<List<RecipeInstructionsResponse>>() {
            @Override
            public void onResponse(Call<List<RecipeInstructionsResponse>> call, Response<List<RecipeInstructionsResponse>> response) {
                if (!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<RecipeInstructionsResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRandomRecipes {
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );
    }

    private interface CallRecipeDetails {
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallRecipeInstructions {
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<RecipeInstructionsResponse>> callRecipeInstructions(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

}
