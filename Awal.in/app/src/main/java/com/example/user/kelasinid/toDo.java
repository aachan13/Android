package com.example.user.kelasinid;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import static android.support.constraint.Constraints.TAG;

public class toDo {
     String id;
    String title;

    String Date;
    String Time;
    int Point;
    boolean isChecked;
    Long delay;


    public toDo() {
    }

    public toDo(String id, String title, String date, String time, int point, boolean isChecked) {
        this.id = id;
        this.title = title;
        Date = date;
        Time = time;
        Point = point;
        this.isChecked = isChecked;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getPoint() {
        return Point;
    }

    public void setPoint(int point) {
        Point = point;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public long getDelay(){
        int month=Integer.parseInt(Date.substring(5,7));
        int date=Integer.parseInt(Date.substring(8,10));
        int year=Integer.parseInt(Date.substring(0,4));
        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DAY_OF_MONTH,month);
        thatDay.set(Calendar.MONTH,date); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, year);

        Calendar today = Calendar.getInstance();

        long diff =  -today.getTimeInMillis()+thatDay.getTimeInMillis() ;
        Log.d(TAG, "getDelay: "+month);
        Log.d(TAG, "getDelay: "+date);
        Log.d(TAG, "getDelay: "+year);
        long days = diff / (24 * 60 * 60 * 1000);
        return days;
    }
}
