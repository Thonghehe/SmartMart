package com.example.smartmart.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartmart.R;


public class fgcholayhang extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout cho Fragment 1
        View view = inflater.inflate(R.layout.fragment_fgcholayhang, container, false);
        return view;
    }
}