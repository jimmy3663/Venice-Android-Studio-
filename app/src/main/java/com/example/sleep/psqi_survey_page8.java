package com.example.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.CheckBox;

import static com.example.sleep.psqi_managing.setq10_a_b_c;


public class psqi_survey_page8 extends AppCompatActivity {
    CheckBox a_cb1, b_cb1, c_cb1;
    CheckBox a_cb2, b_cb2, c_cb2;
    CheckBox a_cb3, b_cb3, c_cb3;
    CheckBox a_cb4, b_cb4, c_cb4;

    int a_result = 0, b_result = 0, c_result = 0;

    Button btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psqi_survey_page8);

        a_cb1 = (CheckBox)findViewById(R.id.a_checkBox1);
        a_cb2 = (CheckBox)findViewById(R.id.a_checkBox2);
        a_cb3 = (CheckBox)findViewById(R.id.a_checkBox3);
        a_cb4 = (CheckBox)findViewById(R.id.a_checkBox4);


        b_cb1 = (CheckBox)findViewById(R.id.b_checkBox1);
        b_cb2 = (CheckBox)findViewById(R.id.b_checkBox2);
        b_cb3 = (CheckBox)findViewById(R.id.b_checkBox3);
        b_cb4 = (CheckBox)findViewById(R.id.b_checkBox4);

        c_cb1 = (CheckBox)findViewById(R.id.c_checkBox1);
        c_cb2 = (CheckBox)findViewById(R.id.c_checkBox2);
        c_cb3 = (CheckBox)findViewById(R.id.c_checkBox3);
        c_cb4 = (CheckBox)findViewById(R.id.c_checkBox4);


        btn_next = (Button)findViewById(R.id.psqi_next);
        btn_next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                onChecked();
                setq10_a_b_c(a_result,b_result,c_result);
                Intent intent = new Intent(getApplicationContext(),psqi_survey_page9.class);
                startActivity(intent);
            }
        });
    }
    public void onChecked() {
        if(a_cb1.isChecked()) a_result= 1;
        if(a_cb2.isChecked()) a_result= 2;
        if(a_cb3.isChecked()) a_result= 3;
        if(a_cb4.isChecked()) a_result= 4;

        if(b_cb1.isChecked()) b_result= 1;
        if(b_cb2.isChecked()) b_result= 2;
        if(b_cb3.isChecked()) b_result= 3;
        if(b_cb4.isChecked()) b_result= 4;

        if(c_cb1.isChecked()) c_result= 1;
        if(c_cb2.isChecked()) c_result= 2;
        if(c_cb3.isChecked()) c_result= 3;
        if(c_cb4.isChecked()) c_result= 4;
    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
