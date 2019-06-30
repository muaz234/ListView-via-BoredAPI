package com.muaz.sharedpreferences;

import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView display;
    Button result;
    ArrayList<String> data;
    String json_result;
    JSONObject object;
    int next;
//    @Override

    private String readData(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();

        while( (next = reader.read()) != -1){
            builder.append((char) next);

        }
        return builder.toString();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        display = findViewById(R.id.display);
        result = findViewById(R.id.result);
        data = new ArrayList<String>();
        String url = "https://www.boredapi.com/api/activity";
        try {
            InputStream stream = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            json_result = readData(reader);
            JSONObject json_data = new JSONObject(json_result);
                data.add(json_data.getString("activity"));
                data.add(json_data.getString("accessibility"));
                data.add(json_data.getString("type"));
                data.add(json_data.getString("participants"));
                data.add(json_data.getString("price"));
                data.add(json_data.getString("link"));
                data.add(json_data.getString("key"));
            Log.i("data", data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        data.add(json_result);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,  data);
        display.setAdapter(adapter);
    }
}
