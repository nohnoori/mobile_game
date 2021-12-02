package com.example.test3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.test3.DB.DBHelper;
import com.example.test3.DB.Thedays;
import org.json.JSONException;

public class safe_activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safeactivity);

        EditText one =(EditText)findViewById(R.id.one);
        EditText two =(EditText)findViewById(R.id.two);
        EditText three =(EditText)findViewById(R.id.three);
        EditText four =(EditText)findViewById(R.id.four);
        Button submit=(Button)findViewById(R.id.submit);
        Button cancel=(Button)findViewById(R.id.cancel);

        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //다이얼로그
                String str=one.getText().toString()+two.getText().toString()+three.getText().toString()+four.getText().toString();
                if(str.equals("0430")){
                    subDialog();
                }else{
                    canDialog();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이전으로
                finish();
            }
        });

    }

    private void subDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("").setMessage("금고가 열렸습니다.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectDialog();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void canDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("").setMessage("입력한 비밀번호가 틀렸습니다.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //다이얼로그 창 닫기
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //날짜 선택 다이얼로그
    private void selectDialog(){
        DBHelper db=new DBHelper(getApplicationContext());
        final CharSequence[] items ={"2019-04-02","2019-04-05", "2020-02-20"} ;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("날짜 선택");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int pos) {
                Thedays day1=new Thedays();
                day1.setName(items[pos].toString());
                day1.setDay(items[pos].toString());
                db.addDays(day1);
                Toast.makeText(getApplicationContext(),items[pos].toString(),Toast.LENGTH_LONG).show();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
