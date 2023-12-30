package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView weather, temper, feel, humidity;
    private EditText editText;
    private ImageButton imageButton;
    private Retrofit retrofit;
    private MainInterface interF;
    private Call<Model> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weather = findViewById(R.id.weatherTV);
        temper = findViewById(R.id.tempTV);
        feel = findViewById(R.id.feelsTV);
        humidity = findViewById(R.id.humidityTV);
        editText = findViewById(R.id.editTextCity);
        imageButton = findViewById(R.id.search);
        retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/").addConverterFactory(GsonConverterFactory.create()).build();
        interF=retrofit.create(MainInterface.class);
        call = interF.getData();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editText.getText().toString();
                if(cityName.isEmpty()){
                    editText.setError("Enter a City!");
                    editText.requestFocus();
                    return;
                }

                call = interF.getCityData(cityName,"1f1759c1d0feb050922ad9715e703f47");
                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        if(!response.isSuccessful()){
                            weather.setText("Not Found");
                            return;
                        }
                        Model model = response.body();
                        Main main = model.getMain();
                        double temp = main.getTemp();
                        double feels = main.getFeels();
                        int hum = main.getHumidity();
                        List<Weather> weather1 = model.getWeathers();
                        String s = weather1.get(0).getMain();
                        weather.setText("Current Condition: " + s);
                        temper.setText("Current Temperature: " + Math.round((1.8*(temp-273.15)+32))+" °F");
                        feel.setText("What it Feels Like: " + Math.round((1.8*(feels-273.15)+32))+" °F");
                        humidity.setText("Current Humidity: " + hum+"%");
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {

                    }
                });
            }
        });
    }
}