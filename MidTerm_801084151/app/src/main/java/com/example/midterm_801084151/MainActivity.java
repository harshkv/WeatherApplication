package com.example.midterm_801084151;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   ListView listView;
   TextView tv_title;
   ArrayList<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String data = loadJSONFromAsset(this);
        setTitle("Select City");
        listView = (ListView) findViewById(R.id.listView);
        tv_title = (TextView) findViewById(R.id.tv_title);
        result = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            JSONArray dataJSON = root.getJSONArray("data");
            for(int i=0; i<dataJSON.length();i++){
                JSONObject eachJSON = dataJSON.getJSONObject(i);
                result.add(eachJSON.getString("city")+ "," + eachJSON.getString("country"));
            }



            ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, result);
            listView.setAdapter(arrayadapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MainActivity.this, secondActivity.class);
                    intent.putExtra("currentWeather", result.get(i));
                    startActivity(intent);

                }
            });



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.cities);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }


}

