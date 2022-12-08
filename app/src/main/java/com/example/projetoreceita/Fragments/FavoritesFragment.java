package com.example.projetoreceita.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projetoreceita.Adapters.RandomRecipeAdapter;
import com.example.projetoreceita.Listeners.RandomRecipeResponseListener;
import com.example.projetoreceita.MainActivity;
import com.example.projetoreceita.Models.RandomRecipeApiResponse;
import com.example.projetoreceita.R;
import com.example.projetoreceita.RequestManager;

public class FavoritesFragment extends Fragment {

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }


}