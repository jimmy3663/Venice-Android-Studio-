package com.example.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.EditText;
import android.widget.CheckBox;

import static com.example.sleep.psqi_managing.setq10_d;


public class psqi_survey_page9 extends AppCompatActivity {
    CheckBox d_cb1, d_cb2, d_cb3, d_cb4;

    int d_result = 0;

    Button btn_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psqi_survey_page9);

        d_cb1 = (CheckBox)findViewById(R.id.d_checkBox1);
        d_cb2 = (CheckBox)findViewById(R.id.d_checkBox2);
        d_cb3 = (CheckBox)findViewById(R.id.d_checkBox3);
        d_cb4 = (CheckBox)findViewById(R.id.d_checkBox4);


        btn_end = (Button)findViewById(R.id.psqi_end);
        btn_end.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                onChecked();
                setq10_d(d_result);
                Intent intent = new Intent(getApplicationContext(),psqi_main.class);
                startActivity(intent);
            }
        });
    }
    public void onChecked() {
        if(d_cb1.isChecked()) d_result= 1;
        if(d_cb2.isChecked()) d_result= 2;
        if(d_cb3.isChecked()) d_result= 3;
        if(d_cb4.isChecked()) d_result= 4;
    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
