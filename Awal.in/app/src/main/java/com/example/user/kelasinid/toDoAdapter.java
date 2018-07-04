package com.example.user.kelasinid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class toDoAdapter extends RecyclerView.Adapter<toDoAdapter.toDoCardViewHolder> {

    public static ArrayList<toDo> list = new ArrayList<>();
    private Context context;
    static String EXTRA_DATE = "extra_date", EXTRA_TIME = "extra_Time", EXTRA_TITLEREMAKS = "extra_title_remarks", EXTRA_POSITION = "extra_position";
    static int position;

    public int getPosition() {

        return position;
    }


    public toDoAdapter() {
    }

    public toDoAdapter(ArrayList<toDo> toDoArrayList, Context context) {
        this.list = toDoArrayList;
        this.context = context;
    }

    public ArrayList<toDo> getList() {
        return list;
    }

    public void setList(ArrayList<toDo> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public toDoCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_cardview, parent, false);
        toDoCardViewHolder viewHolder = new toDoCardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull toDoCardViewHolder holder, int position) {

        holder.dateTime.setText("Due: " + list.get(position).getDate());
//        holder.points.setText("+"+list.get(position).getPoint()+" Points");
        holder.title.setText(list.get(position).getTitle());

        if (list.get(position).isChecked() == true) {
            holder.toDoCheckbox.setChecked(true);
            holder.toDoCheckbox.setText("Done");
            Log.d(TAG, "onBindViewHolder: " + position + list.get(position).isChecked());
        } else {
            holder.toDoCheckbox.setChecked(false);
            Log.d(TAG, "onBindViewHolder: " + position + list.get(position).isChecked());
            holder.toDoCheckbox.setText("Not Done");
        }



    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    class toDoCardViewHolder extends RecyclerView.ViewHolder {
        TextView title, dateTime, status, points;
        CheckBox toDoCheckbox;
        ImageButton deleteToDo;
        ImageButton editToDo;

        public toDoCardViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.toDoTitle);
            dateTime = (TextView) itemView.findViewById(R.id.toDoDateTime);
            //points = (TextView) itemView.findViewById(R.id.points);

            toDoCheckbox = (CheckBox) itemView.findViewById(R.id.toDoCheckBox);
            deleteToDo = (ImageButton) itemView.findViewById(R.id.btn_delete);
            editToDo = (ImageButton) itemView.findViewById(R.id.btn_edit);
            toDoCheckbox.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    DatabaseHelper db = new DatabaseHelper(((MainActivity) context).getApplicationContext());
                    position = getAdapterPosition();
                    if (toDoCheckbox.isChecked() == true ) {
                        Log.d(TAG, "onClick: "+list.get(position).getDelay());
                        if( list.get(position).getDelay()>0L){

                        list.get(position).setPoint(5);
                        list.get(position).setChecked(true);
                        Log.d(TAG, "onClick:" + "add Point " + position);
                        toDoCheckbox.setText("Done");

                        db.updatePoint(list.get(position).getId(), list.get(position).getPoint(), list.get(position).isChecked());}

                    } else {
                        list.get(position).setPoint(0);
                        list.get(position).setChecked
                                (false);
                        toDoCheckbox.setText("Not Done ");
                        list.get(position).setPoint(0);
                        db.updatePoint(list.get(position).getId(), list.get(position).getPoint(), list.get(position).isChecked());
                        Log.d(TAG, "onClick:" + "remove Point " + position);
                    }

                }
            });
            deleteToDo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    Log.d(TAG, "onClick: position" + position);
                    Log.d(TAG, "onClick: title" + list.get(position).getTitle());
                    String id = (list.get(position).getId());
                    Log.d(TAG, "onClick: id " + id);
                    DatabaseHelper db = new DatabaseHelper(((MainActivity) context).getApplicationContext());
                    db.deleteName(id);
                    list.remove(position);
                    notifyItemRemoved(position);

                }
            });

            editToDo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    toDo editItemTodo = list.get(getAdapterPosition());
                    Intent intent = new Intent(context, CalenderEditor.class);
                    intent.putExtra(EXTRA_DATE, editItemTodo.getDate());
                    intent.putExtra(EXTRA_TIME, editItemTodo.getTime());
                    intent.putExtra(EXTRA_TITLEREMAKS, editItemTodo.getTitle());
                    intent.putExtra(EXTRA_POSITION, getAdapterPosition());
                    position = getAdapterPosition();
                    Log.d("TAG", "OnCllick Edit");
                    ((Activity) context).startActivityForResult(intent, 2);


                }
            });


        }
    }
}
