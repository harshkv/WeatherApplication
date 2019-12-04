package com.example.midterm_801084151;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class thirdActivity extends AppCompatActivity {
    String city;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<weatherForcast> forecastList;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        textView =(TextView ) findViewById(R.id.textView);

        setTitle("Forecast ");

        if (getIntent() != null && getIntent().getExtras() != null) {
            city = getIntent().getExtras().getString("city");

            if (isConnected() == true) {
                Toast.makeText(this, "Connected to wifi and phone", Toast.LENGTH_SHORT).show();
                new GetForecastDataAsync().execute("https://api.openweathermap.org/data/2.5/forecast?q="+city+"&units=imperial&appid=71ed5868f11ea02543d4602f2fc77ccf");
            } else {
                Toast.makeText(this, "Not connected", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected() || (networkInfo.getType() != connectivityManager.TYPE_WIFI &&
                networkInfo.getType() != connectivityManager.TYPE_MOBILE)) {
            return false;
        }

        return true;
    }


    private class GetForecastDataAsync extends AsyncTask<String, Void, ArrayList<weatherForcast>> {

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected ArrayList<weatherForcast>  doInBackground(String... strings) {
            HttpURLConnection connection = null;
            String json = null;
            forecastList = new ArrayList<>();


            try {
                URL urls = new URL(strings[0]);
                connection = (HttpURLConnection) urls.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                     json = IOUtils.toString(connection.getInputStream(), "UTF8");

                    JSONObject root = new JSONObject(json);
                    JSONArray list = root.getJSONArray("list");
                    Log.i("demo" , "im here ");

                    for (int i = 0; i <list.length(); i++) {
                        JSONObject data = list.getJSONObject(i);
                        JSONObject main = data.getJSONObject("main");
                        weatherForcast weatherobj = new weatherForcast();
                        weatherobj.tempf = main.getString("temp");
                        weatherobj.maxTempf = main.getString("temp_max");
                        weatherobj.minTempf = main.getString("temp_min");
                        weatherobj.humidityf = main.getString("humidity");
                        weatherobj.datef = data.getString("dt_txt");
                        JSONObject wind = data.getJSONObject("wind");
                        weatherobj.windSpeedf = wind.getString("speed");

                        JSONArray weatherArray = data.getJSONArray("weather");
                        JSONObject weatherType = weatherArray.getJSONObject(0);
                        weatherobj.descf = weatherType.getString("description");
                        weatherobj.imageIconf = weatherType.getString("icon");
                        forecastList.add(weatherobj);
                    }


                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

            finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

            return forecastList;

        }

        @Override
        protected void onPostExecute(ArrayList<weatherForcast>  forecastList) {
            textView.setText(city);
            recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(thirdActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            mAdapter = new forecastAdapter(forecastList);
            recyclerView.setAdapter(mAdapter);

        }
    }
}

