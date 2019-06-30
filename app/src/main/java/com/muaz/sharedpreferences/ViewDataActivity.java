package com.muaz.sharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class ViewDataActivity extends AppCompatActivity {

    TextView text;

 protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);
        text = findViewById(R.id.result);
     SharedPreferences sharedPreferences = getSharedPreferences("this", Activity.MODE_PRIVATE);
     String data = sharedPreferences.getString("value", null);
     Log.i("data", data);
     if(data != null){
         text.setText(data);
     }else {
         text.setText("No data is passed");
     }
    }


    public void onBackPressed() {
     Intent intent = new Intent(ViewDataActivity.this, MainActivity.class) ;
    startActivity(intent);
    }
}
