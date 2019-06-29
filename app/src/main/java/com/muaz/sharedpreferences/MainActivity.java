package com.muaz.sharedpreferences;

import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

public class MainActivity extends AppCompatActivity {
    ListView display;
    Button result;
    ArrayList<String> data;
    String json_result;
    JSONObject object;
//    @Override

    private String readData(Reader rd) throws IOException {
        StringBuilder builder = new StringBuilder();

        int next = rd.read();
        while(next != -1){
            builder.append((char) next);

        }
        return builder.toString();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
        result = findViewById(R.id.result);
        data = new ArrayList<String>();
        String url = "http://www.boredapi.com/api/activity";
        try {
            InputStream stream = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            json_result = readData(reader);
             object = new JSONObject(json_result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        data.add(json_result);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, display,  data);
        display.setAdapter(adapter);
    }
}
