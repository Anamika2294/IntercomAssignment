package com.example.intercomassignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnHit;
    TextView id,username;
    TextView txtJson;
    ProgressDialog pd;
    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
    JSONObject obj;
    List<Person> personList= new ArrayList<>();
    JSONArray array=new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHit = (Button) findViewById(R.id.btnHit);
        id = (TextView) findViewById(R.id.text_id);
        username = (TextView) findViewById(R.id.username);




        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new JsonTask().execute("https://s3.amazonaws.com/intercom-take-home-test/customers.txt");
            }
        });


    }

    private class JsonTask extends AsyncTask<String, String, JSONArray> {

        protected void onPreExecute() {
            super.onPreExecute();


            if(pd == null){
                pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Please wait");
                pd.setCancelable(false);
//                if(!this.isFinishing()){
                    pd.show();
              //  }

            }

        }

        protected JSONArray doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line+"\n");
//                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
//
//                }

                while((line = reader.readLine()) != null) {
                    obj = (JSONObject) new JSONParser().parse(line);
                    array.add(obj);



                }

                return array;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
                pd=null;
            }
            Log.v("Json Object",""+result);

            personList=Get100Customer.getCustomers(result);


         SortUserId.sort_userId(personList);
            placeData(personList);

     }
    }


    public void placeData(List<Person> personList){
        StringBuilder builderId = new StringBuilder();
        StringBuilder builderUsername = new StringBuilder();

        for(int i=0;i<personList.size();i++){
            builderId.append(""+personList.get(i).getUserId()+ "\n");
            builderId.append(""+personList.get(i).getName()+ "\n");


        }
        id.setText(builderId.toString());
        username.setText(builderUsername.toString());


    }





}
