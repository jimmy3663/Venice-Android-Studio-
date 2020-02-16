package com.example.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import android.os.Handler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.security.Key;
import java.util.ArrayList;

import android.widget.CheckBox;
import android.content.SharedPreferences;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

public class gettingphoto extends AppCompatActivity {

    private Handler mHandler;

    private DataOutputStream dos;
    private DataInputStream dis;

    private String ip = "192.168.2.1";            // IP 번호
    private int port = 9998;
    private Socket socket;
    String response = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gettingphoto);

    }

    public void photo_button(View view){
        connect();
        //make_graph();
    }
    void connect() {
        mHandler = new Handler();
        Log.w("connect", "연결하는중");

        Thread checkUpdate = new Thread() {
            public void run() {
                String send_value = "G";
                try {
                    socket = new Socket(ip, port);
                    Log.w("server connected ", "server connected");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    Log.w("server not connected", "server not connected");
                }

                try {
                    dos = new DataOutputStream(socket.getOutputStream());
                    dis = new DataInputStream(socket.getInputStream());
                    dos.writeUTF(send_value);
                    Log.w("message sent", "message sent");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.w("wrong buffer", "wrong buffer");
                }

                try {
                    byte[] buffer = new byte[256];
                    int byteRead;

                    int count = 0;
                    while ((byteRead = dis.read(buffer))!=-1) {
                        Log.w("message gotten", "message gotten");
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byteArrayOutputStream.write(buffer, 0, byteRead);
                        //final int val = Integer.parseInt(line);
                        response+=byteArrayOutputStream.toString("utf-8");
                        //System.out.println("gotten message: "+response);
                        count += 1;
                    }

                    LineChart lineChart = (LineChart) findViewById(R.id.chart);
                    //lineChart.setRotation(90);

                    ArrayList<Entry> entries = new ArrayList<>();
                    System.out.println(response.indexOf("\n"));
                    String st_x = response.substring(0,response.indexOf("\n"));
                    String st_y = response.substring(response.indexOf("\n")+1,response.length()-1);

                    Log.w("in make_graph", "in make_graph");
                    String[] arr_x = st_x.split(",");
                    String[] arr_y = st_y.split(",");
                    int[] x_label = new int[arr_x.length];
                    int[] y_label = new int[arr_y.length];
                    //System.out.println(Integer.parseInt(arr_x[0]));
                    for(int i=0 ;i <arr_x.length; i++){
                        x_label[i] = Math.round(Float.parseFloat(arr_x[i]));
                    }
                    Log.w("???", "???");
                    for(int i=0 ; i<arr_y.length; i++){
                        y_label[i] = Math.round(Float.parseFloat(arr_y[i]));
                    }

                    for(int i=0; i<arr_x.length ; i++){
                        entries.add(new Entry(y_label[i],i)); // y_label이 y축으로 나오긴
                    }

                    LineDataSet dataset1 = new LineDataSet(entries, "# of Calls");

                    ArrayList<String> labels = new ArrayList<String>(); // x 축입니다.
                    for(int i=0; i<x_label.length; i++){
                        labels.add(String.valueOf(x_label[i]));
                    }
                    Log.w("???", "???");
                    LineData data = new LineData(labels,dataset1);
                    dataset1.setColor(Color.parseColor("BLACK"));   // graph line color
                    dataset1.setCircleColor(Color.parseColor("RED"));   //point color

                    XAxis xAxis = lineChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setTextColor(Color.BLACK);
                    xAxis.enableGridDashedLine(8, 24, 0);

                    YAxis yLAxis = lineChart.getAxisLeft();
                    yLAxis.setTextColor(Color.BLACK);

                    YAxis yRAxis = lineChart.getAxisRight();
                    yRAxis.setDrawLabels(true);
                    yRAxis.setDrawAxisLine(false);
                    yRAxis.setDrawGridLines(false);

                    lineChart.setData(data);
                    lineChart.setDoubleTapToZoomEnabled(true);
                    lineChart.setDragEnabled(true);
                    lineChart.animateY(5000);

                    Log.w("end of chart ", "end of chart");
                } catch (Exception e) {

                }

            }
        };
        checkUpdate.start();
    }

    /*void make_graph(){
        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();

        Log.w("in make_graph", "in make_graph");
        String[] array = response.split(",");

        for(int i=0; i<array.length; i++){
            System.out.println(array[i]);
        }
    }*/
}