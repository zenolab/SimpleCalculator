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


/**
 * A simple {@link Fragment} subclass.
 */
public class MultiplicationFragment extends Fragment {

    private double result;

    public static MultiplicationFragment newInstance(double param) {
        MultiplicationFragment fragment = new MultiplicationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.result = param;
        return fragment;
    }


    public MultiplicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multiplication, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView text = (TextView) view.findViewById(R.id.text_view_multiplication);
        text.setText(String.format( getString(R.string.you_result), String.valueOf(result)));
    }

}
