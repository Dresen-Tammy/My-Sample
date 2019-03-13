package com.example.shopapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

//////////////////////////////////////////////////////
// NETWORKING
/////////////////////////////////////////////////////
public class Networking extends AsyncTask<Void,Void,Void> {


    StringBuffer stringBuffer = new StringBuffer("");
    String firstname;
    String lastname;
    String uname;
    String pword;
    String id;

    public Networking(String user, String password) {
        this.uname = user;
        this.pword = password;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        L("doInBackground");
        HashMap<String, Object> responseMap = null;
        HashMap<String, Object> data = new HashMap<>();
        String id = "";
        data.put("command", "login");
        data.put("uname", uname);
        data.put("pword", pword);

        try {
            URL url = new URL("http://10.0.2.2:8080/Store_war_exploded/LibraryServlet");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();   //Connection formed here
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            // add requestData to connection
            ObjectMapper mapper = new ObjectMapper();
            PrintWriter out = new PrintWriter((con.getOutputStream()));
            mapper.writeValue(out, data);

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                L("response received");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // close buffered reader
                in.close();
                // deserialize and return HashMap
                responseMap = mapper.readValue(response.toString(), HashMap.class);
                this.id = (String) responseMap.get("id");

            }
            con.disconnect();
            System.out.print("/////Output from JSON/////" + responseMap);






        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.response.setText(id);

    }

    ///////////////////////////////////////////////////////////////////////////////
    //wrapper so you don't have to type this in everytime. This makes nice output messages to console, so you can see what you are doing.
    //////////////////////////////////////////////////////////////////////////////
    private void L(String s) {

        Log.d("MyApp", "Networking" + "#######" + s);
    }


}