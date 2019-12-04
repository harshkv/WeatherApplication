package com.example.midterm_801084151;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class secondActivity extends AppCompatActivity {
    ArrayList<Weather> weatherList;
    TextView tv_city,tv_min,tv_max,tv_desc,tv_humi,tv_speed,tv_temp;
    String city;
    Button b_forecast;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("Current Weather");
        weatherList = new ArrayList<>();
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_min =(TextView) findViewById(R.id.tv_min);
        tv_max =(TextView) findViewById(R.id.tv_max);
        tv_desc =(TextView) findViewById(R.id.tv_desc);
        tv_humi =(TextView) findViewById(R.id.tv_humi);
        tv_speed =(TextView) findViewById(R.id.tv_speed);
        tv_temp =(TextView) findViewById(R.id.tv_temp);
        b_forecast =(Button)findViewById(R.id.b_forecast);
        imageView =(ImageView) findViewById(R.id.imageView);


        b_forecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTwo = new Intent(secondActivity.this, thirdActivity.class);
                intentTwo.putExtra("city", city);
                startActivity(intentTwo);
                finish();


            }
        });






        if(getIntent() != null  && getIntent().getExtras() != null ){
             city = getIntent().getExtras().getString("currentWeather");
            if (isConnected()==true) {
                Toast.makeText(this, "Connected to wifi and phone", Toast.LENGTH_SHORT).show();
                new GetDataAsync().execute("http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=71ed5868f11ea02543d4602f2fc77ccf&units=imperial");
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
    private class GetDataAsync extends AsyncTask<String, Void, Weather > {
        Weather weatherObj = new Weather();
        @Override
        protected void onPreExecute() {


        }

        @Override
        protected Weather  doInBackground(String... strings) {
            HttpURLConnection connection = null;
            String json = null;
            String result = null;


            try {
                URL urls = new URL(strings[0]);
                connection = (HttpURLConnection) urls.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                     json =  IOUtils.toString(connection.getInputStream(),"UTF8");
                    JSONObject root = new JSONObject(json);
                    JSONObject main = root.getJSONObject("main");
                    weatherObj.temp = main.getString("temp");
                    weatherObj.maxTemp = main.getString("temp_max");
                    weatherObj.minTemp = main.getString("temp_min");
                    weatherObj.humidity = main.getString("humidity");
                    JSONObject wind = root.getJSONObject("wind");
                    weatherObj.windSpeed = wind.getString("speed");

                    JSONArray weatherArray = root.getJSONArray("weather");
                    JSONObject weatherType = weatherArray.getJSONObject(0);
                    weatherObj.desc = weatherType.getString("description");
                    weatherObj.imageIcon = weatherType.getString("icon");

                       weatherList.add(weatherObj);

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

            return weatherObj;
        }

        @Override
        protected void onPostExecute(Weather  weatherObj) {
            tv_city.setText(city);
            tv_max.setText("Temperature Max "+weatherObj.getMaxTemp() + " F");
            tv_min.setText("Temperature Min "+weatherObj.getMinTemp() +" F");
            tv_temp.setText("Temperature "+ weatherObj.getTemp() +" F");
            tv_desc.setText("Description "+weatherObj.desc);
            tv_humi.setText("Humidity "+weatherObj.getHumidity()+"%");
            tv_speed.setText("Wind Speed "+weatherObj.getWindSpeed() +"miles/hr");


            String url = "http://openweathermap.org/img/wn/" + weatherObj.imageIcon + "@2x.png";
            Picasso.get().load(url).resize(300, 300).centerCrop().into((imageView));





        }
    }
}