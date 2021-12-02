package com.example.test3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.test3.DB.DBHelper;
import com.example.test3.DB.Thedays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class cal_2019 extends AppCompatActivity {
    List<EventDay> events=new ArrayList<>();
    public static Context con;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_activity);
        ActionBar action=getSupportActionBar();
        action.setDisplayHomeAsUpEnabled(true);
        action.hide();
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        con=this;

        DBHelper db=new DBHelper(getApplicationContext());
        List<Thedays> days=new ArrayList<Thedays>();
        Thedays thedays=new Thedays();
        days=db.getAllDays();
        if(days.size()>0) {
            for (int i = 0; i < days.size(); i++) {
                thedays = days.get(i);
                makeicon(thedays.getDay());
            }
        }

        try {
            Calendar cal=Calendar.getInstance();
            cal.set(2019, 4,11);
            calendarView.setDate(cal);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM");
            Date date=sim.parse("2019-04");
            Calendar cc=Calendar.getInstance();
            cc.setTime(date);
            cc.add(Calendar.MONTH,0);
            calendarView.setMinimumDate(cc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM");
            Date date=sim.parse("2019-05");
            Calendar cc=Calendar.getInstance();
            cc.setTime(date);
            cc.add(Calendar.MONTH, 1);
            calendarView.setMaximumDate(cc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void makeicon(String yymmdd){
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        try {
            Calendar call=Calendar.getInstance();
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
            Date date=sim.parse(yymmdd);
            call.setTime(date);
            getResources().getAssets();
            events.add(new EventDay(call, R.drawable.redcircle));
            calendarView.setEvents(events);
        } catch (ParseException e) { e.printStackTrace(); }
    }
}
