package com.example.projetoreceita.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projetoreceita.Adapters.RandomRecipeAdapter;
import com.example.projetoreceita.Listeners.RandomRecipeResponseListener;
import com.example.projetoreceita.Models.RandomRecipeApiResponse;
import com.example.projetoreceita.R;
import com.example.projetoreceita.RequestManager;

public class HomeFragment extends Fragment {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loading...");

        manager = new RequestManager(getActivity());
        manager.getRandomRecipes(randomRecipeResponseListener);
        dialog.show();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = getView().findViewById(R.id.recycler_view);
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            randomRecipeAdapter = new RandomRecipeAdapter(getActivity(), response.recipes);
            recyclerView.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    };

}