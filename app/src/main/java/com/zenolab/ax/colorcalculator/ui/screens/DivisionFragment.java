package com.zenolab.ax.colorcalculator.ui.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zenolab.ax.colorcalculator.R;


public class DivisionFragment extends Fragment {


    private double result;

    public DivisionFragment() {
        // Required empty public constructor
    }

    public static DivisionFragment newInstance(double param) {
        DivisionFragment fragment = new DivisionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.result =  param;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_division, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView text = (TextView) view.findViewById(R.id.text_view_division);
        text.setText(String.format( getString(R.string.you_result), String.valueOf(result)));
    }


}
