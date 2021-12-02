package com.example.test3;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class past_json extends AppCompatActivity {
    private TextView tv;
    ArrayList<HashMap<String, String>> processList = new ArrayList<>();
    HashMap<String, String> hashMap = null;
    int i = 0;
    String str = "";
    static int j=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pas_json);
        ActionBar action=getSupportActionBar();
        action.hide();

        tv=findViewById(R.id.textview);
        Button btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String day=getIntent().getStringExtra("processId");

        getJson(jsonRead("Event_past.json"));
        for(j=0;j<processList.size();j++){
            if(day.equals(processList.get(j).get("processesId")+""))
                break;
        }
        getData(j);
    }
    public String jsonRead(String jsonname) {
        AssetManager assetManager = getAssets();
        String jsonData = "";
        try {
            InputStream is = assetManager.open(jsonname);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line + "\n");
                line = reader.readLine();
            }
            jsonData = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    public void getJson(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);

                HashMap<String, String> tempMap = new HashMap<String, String>();
                tempMap.put("processesId", jo.getString("processesId"));
                tempMap.put("text", jo.getString("text"));
                processList.add(tempMap);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void getData(int i) {
        hashMap = processList.get(i);
        String str = " ";
        String processesId = hashMap.get("processesId");
        String text = hashMap.get("text");
        if (text != "null") {
            str += text;
        }
        tv.setText(str);

    }
}
