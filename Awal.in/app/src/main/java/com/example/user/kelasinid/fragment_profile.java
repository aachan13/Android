package com.example.user.kelasinid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class fragment_profile extends Fragment {
    TextView tvToatalPoint;
    RecyclerView recyclerView;
    AchievementAdapter adapter;

    List<Achievement> achievementList;
    int totalPoint = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<toDo> toDoList = toDoAdapter.list;
        for (int i = 0; i < toDoList.size(); i++) {
            totalPoint = totalPoint + toDoList.get(i).getPoint();
            Log.d(TAG, "totalPoint: " + totalPoint);
        }


        achievementList = new ArrayList<>();


        if (totalPoint >= 5) {

            achievementList.add(
                    new Achievement(
                            1,
                            "Achievement 1",
                            "Anda Telah Menyelesaikan 1 Aktivitas.",
                            "Penghargaan 5 Point",
                            R.drawable.trophy1));
        }
        if (totalPoint >= 10) {
            achievementList.add(
                    new Achievement(
                            1,
                            "Achievement 2",
                            "Anda Telah Menyelesaikan 2 Aktivitas",
                            "Penghargaan 10 Point",

                            R.drawable.trophy2));
        }

        if (totalPoint >= 20) {
            achievementList.add(
                    new Achievement(
                            1,
                            "Achievement 3",
                            "Anda Telah Menyelesaikan 4 Aktivitas.",
                            "Penghargaan 20 Point",
                            R.drawable.trophy6));
        }


        if (totalPoint >= 30) {
            achievementList.add(
                    new Achievement(
                            1,
                            "Achievement 4",
                            "Anda Telah Menyelesaikan 6 Aktivitas.",
                            "Penghargaan 30 Point",
                            R.drawable.trophy4));
        }

        if (totalPoint >= 40) {
            achievementList.add(
                    new Achievement(
                            1,
                            "Achievement 5",
                            "Anda Telah Menyelesaikan 8 Aktivitas.",
                            "Penghargaan 40 Point",

                            R.drawable.trophy5));
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AchievementAdapter(getContext(), achievementList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        tvToatalPoint = rootView.findViewById(R.id.totalPoint);
        tvToatalPoint.setText(String.valueOf(totalPoint));
        return rootView;
    }


}
