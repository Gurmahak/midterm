package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.midterm.R.id.spinner1;

public class MainActivity extends AppCompatActivity {

        EditText input;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                Spinner spinner = (Spinner) findViewById(spinner1);
                //spinner.setOnItemSelectedListener(MainActivity.this);

                Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.planets_array, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);








                findViewById(R.id.btn).setOnClickListener((view) -> {

                        input = (EditText)findViewById(R.id.inputbox);
                        String Input = input.getText().toString();

                        OkHttpClient client = new OkHttpClient();
                        String url;
                        url = "https://learn.operatoroverload.com/rental/"+ Input;
                        Request req = new Request.Builder().url(url).build();
                        TextView textView = (TextView)findViewById(R.id.textView);





                        Thread t = new Thread() {
                                @Override
                                public void run() {
                                        try {
                                                Response response = client.newCall(req).execute();
                                                String text = response.body().string();
                                                Log.d("response", text);


                                                runOnUiThread(() -> {
                                                        textView.setText("text");
                                                        ((TextView) findViewById(R.id.textView)).setText(text);
                                                });
                                        } catch (IOException e) {
                                                runOnUiThread(() -> {
                                                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                                                });

                                        }
                                }
                        };

                        t.start();

                });




        }

}


