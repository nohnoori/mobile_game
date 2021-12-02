package com.example.test3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.Toolbar;
import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.test3.DB.DBHelper;
import com.example.test3.DB.Informations;
import com.example.test3.DB.Thedays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class cal_activity extends AppCompatActivity {
    List<EventDay> events=new ArrayList<>();
    public static Context con;
    static int flag=0;
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
            cal.set(2020, 3,19);
            calendarView.setDate(cal);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM");
            Date date=sim.parse("2020-02");
            Calendar cc=Calendar.getInstance();
            cc.setTime(date);
            cc.add(Calendar.MONTH,0);
            calendarView.setMinimumDate(cc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM");
            Date date=sim.parse("2020-04");
            Calendar cc=Calendar.getInstance();
            cc.setTime(date);
            cc.add(Calendar.MONTH, 1);
            calendarView.setMaximumDate(cc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
//                events.add(new EventDay(clickedDayCalendar,R.drawable.sample_icon_2));
//                calendarView.setEvents(events);
                String mon_day=clickedDayCalendar.get(Calendar.MONTH)+" "+clickedDayCalendar.get(Calendar.DAY_OF_MONTH)+"";
                String day_of_month="";
                if((clickedDayCalendar.get(Calendar.DAY_OF_MONTH)+"").length()==1){
                    day_of_month="0"+clickedDayCalendar.get(Calendar.DAY_OF_MONTH)+"";
                }else{
                    day_of_month=clickedDayCalendar.get(Calendar.DAY_OF_MONTH)+"";
                }
                String year_monday=clickedDayCalendar.get(Calendar.YEAR)+"-0"+(clickedDayCalendar.get(Calendar.MONTH)+1)+"-"+day_of_month;

                DBHelper dbHelper=new DBHelper(getApplicationContext());
                Thedays day=new Thedays();
                day=dbHelper.getOneday(year_monday);

                if(mon_day.equals("3 19")){
                    Intent in=new Intent(getApplicationContext(),MainActivity.class);
                    flag=1;
                    startActivity(in);
                }else if(day!=null){
                    String dday = day.getDay();
                    //날짜값 processId형식으로 전환
                    dday = dday.replaceAll("-", "");

                    //과거 json activity
                    Intent in=new Intent(getApplicationContext(),past_json.class);
                    in.putExtra("processId",dday);
                    startActivity(in);
                }
            }
        });

        if(flag==1) {
            action.setTitle("");
            action.show();
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
