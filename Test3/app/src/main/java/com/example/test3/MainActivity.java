package com.example.test3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.test3.DB.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener{

    private TextView tv;
    private TextView stv;
    ArrayList<HashMap<String, String>> processList = new ArrayList<>();
    HashMap<String, String> hashMap = null;
    int i = 0;
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar action=getSupportActionBar();
        action.hide();

        ImageButton btn_note = (ImageButton) findViewById(R.id.note);
        btn_note.setOnClickListener(this);
        ImageButton btn_cal2019 = (ImageButton) findViewById(R.id.cal2019);
        btn_cal2019.setOnClickListener(this);
        ImageButton btn_cal2020 = (ImageButton) findViewById(R.id.cal2020);
        btn_cal2020.setOnClickListener(this);
        ImageButton btn_pick = (ImageButton) findViewById(R.id.pick);
        btn_pick.setOnClickListener(this);

        tv=findViewById(R.id.textview);
        stv = findViewById(R.id.subtextview);

        getJson(jsonRead("Event_00.json"));
        getData(i);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.note:
                Intent intent1 = new Intent(getApplicationContext(), memolist.class);
                startActivity(intent1);
                break;
            case R.id.cal2019:
                Intent intent2 = new Intent(getApplicationContext(), cal_2019.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
                break;

            case R.id.cal2020:
                Intent intent3 = new Intent(getApplicationContext(), cal_activity.class);
                startActivity(intent3);
                break;
            case R.id.pick:
                try {
                    popup(getCase());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
        }
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

                tempMap.put("conversation", jo.getString("conversation"));
                if(jo.has("choice")){
                    tempMap.put("choice", jo.getString("choice"));}
                processList.add(tempMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void popup(List<String> case0) {
        final CharSequence[] items = case0.toArray(new String[case0.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("선택지");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                //Toast.makeText(getApplicationContext(), items[pos], Toast.LENGTH_LONG).show();
                try {
                    getInnerData(pos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void getData(int i) {
        hashMap = processList.get(i);
        String str = " ";
        String processesId = hashMap.get("processesId");
        String text = hashMap.get("text");
        String conversation = hashMap.get("conversation");
        if (text != "null") {
            str += text;
        }
        if (conversation != "null") {
            str += conversation;
        }
        tv.setText(str);

    }

    public List<String> getCase() throws JSONException {

        List<String> case0 = new ArrayList<String>();
        int ch = 0;
        JSONArray choiceArray = new JSONArray(hashMap.get("choice"));
        while(ch<choiceArray.length()){
            JSONObject co = choiceArray.getJSONObject(ch);
            case0.add(co.getString("case0"));
            ch++;
        }
        return case0;
    }

    public void getInnerData(int i) throws JSONException {
        int pI = 0;
        JSONObject co;
        String case0="";
        String substr = "";
        String subText = "";
        String subconversation = "";
        String  moveJson = "";
        String processId = "";
        String database="";
        String opencal="";
        JSONArray choiceArray = new JSONArray(hashMap.get("choice"));
        co = choiceArray.getJSONObject(i);
        case0=co.getString("case0");
        subconversation=co.getString("subConversation");
        subText = co.getString("subText");
        opencal=co.getString("openCal");
        moveJson = co.getString("moveJson");
        processId = co.getString("processId");
        database=co.getString("database");
        DBHelper db=new DBHelper(getApplicationContext());

        if(subconversation!="null"){
            if(subconversation.equals("flag")){
                Flags fl=new Flags();
                fl.setName("safe");
                fl.setFlag(11);
                db.addFlag(fl);
            }else{
                substr += subconversation;
            }
        }
        if (subText != "null") {
            if(subText.equals("flag")){
                Flags ff=new Flags();
                ff=db.getOneFlag("safe");
                if(ff.getFlag()==11) {
                    //금고 액티비티로 이동해야함

                }
            }else{
                substr += subText;
            }
        }
        stv.setText(substr);

        if(processId!="null"){
            pI = Integer.parseInt(processId);
            getData(pI);
        }else{
            //moveJson
            processList.clear();
            getJson(jsonRead(moveJson));
            getData(0);
        }

        if(database!="null"){
            Informations info=new Informations();
            String[] data=database.split("\n");
            for(int k=0;k<data.length;k++){
                info.setInfo(data[k]);
                db.addInformations(info);
            }
        }

        if(opencal!="null"){
            Thedays day=new Thedays();
            String[] data=opencal.split("\n");
            for(int j=0;j<data.length;j++){
                day.setName(data[j]);
                day.setDay(data[j]);
                db.addDays(day);
                Toast.makeText(getApplicationContext(),data[j]+" 날짜가 활성화 되었습니다.",Toast.LENGTH_SHORT).show();
            }

        }
    }

}
