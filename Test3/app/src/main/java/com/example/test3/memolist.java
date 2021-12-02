package com.example.test3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.test3.DB.DBHelper;
import com.example.test3.DB.Informations;

import java.util.ArrayList;
import java.util.List;

public class memolist extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_activity);
        DBHelper dbHelper=new DBHelper(getApplicationContext());
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");

        ListView listview=(ListView)findViewById(R.id.list);
        List<String> list= new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        List<Informations> info=new ArrayList<Informations>();
        Informations informations=new Informations();
        info=dbHelper.getAllInfo();
        if(info.size()>0) {
            for (int i = 0; i < info.size(); i++) {
                informations = info.get(i);
                list.add(informations.getInfo());
            }
        }
    }

}
