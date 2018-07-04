package com.example.user.kelasinid;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;


public class fragment_toDo extends Fragment {
    static toDoAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TAG", "onCreate: INI FRAGMENT TODO");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_todo, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.todoRv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        adapter = new toDoAdapter(((MainActivity) getContext()).getModel(), getContext());
        rv.setAdapter(adapter);
        rv.setItemAnimator(new SlideInLeftAnimator());
        return rootView;
    }


}
