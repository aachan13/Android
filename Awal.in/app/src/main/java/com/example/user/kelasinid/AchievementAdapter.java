package com.example.user.kelasinid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementAViewHolder> {
    private Context mctx;
    private List<Achievement> achievementsList;

    public AchievementAdapter(Context mctx, List<Achievement> achievementsList) {
        this.mctx = mctx;
        this.achievementsList = achievementsList;
    }

    @NonNull
    @Override
    public AchievementAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new AchievementAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementAViewHolder holder, int position) {
        final Achievement achievement = achievementsList.get(position);

        holder.TextViewTitle.setText(achievement.getTitle());
        holder.TextViewDesc.setText(achievement.getDesc());
        holder.TextViewRating.setText(String.valueOf(achievement.getRating()));

        holder.imageView.setImageDrawable(mctx.getResources().getDrawable(achievement.getImage()));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mctx, "You Clicked "+achievement.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return achievementsList.size();
    }

    class AchievementAViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView TextViewTitle, TextViewDesc, TextViewRating, TextViewSemangat;
        RelativeLayout relativeLayout;

        public AchievementAViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            TextViewTitle = itemView.findViewById((R.id.textViewTitle));
            TextViewDesc = itemView.findViewById((R.id.textViewDesc));
            TextViewRating = itemView.findViewById((R.id.textViewRating));
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}