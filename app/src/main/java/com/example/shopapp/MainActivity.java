package com.example.shopapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.*;

// main activity is what runs when phone opens.
public class MainActivity extends AppCompatActivity {
    // attributes
    public static EditText user;
    public static EditText password;
    private static Button login_button;
    private static TextView attempts;
    public static EditText response;
    int attempt_counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton();
    }
    // why is setOnclick Listener inside loginButton method?
    public void LoginButton() {
        // get parameters from view
        user          = (EditText)findViewById(R.id.editText_user);
        password      = (EditText)findViewById(R.id.editText_password);
        attempts      = (TextView)findViewById(R.id.textView_attempt_counter);
        login_button  = (Button)  findViewById(R.id.button_login);
        response      = (EditText)findViewById(R.id.textView_response);

        attempts.setText(Integer.toString(attempt_counter));

        //Creating an onclick listener
        login_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        L("Login button Pressed!");

                        if (user.length() == 0  || password.length() == 0){
                            Toast.makeText(MainActivity.this,"Please fill in Username and Password.",
                                    Toast.LENGTH_SHORT).show();

                            //Run this to get Json when they don't put any data into fields.
                            //Networking n = new Networking();
                            //n.execute();
                            //return;
                        } else {
                            Networking n = new Networking(user.getText().toString(), password.getText().toString());
                            n.execute();
                            return;
                        }

                        /*if (user.getText().toString().equals("Denise")&&
                            password.getText().toString().equals("pass") ) {

                             Toast.makeText(MainActivity.this,"User and Password is correct",
                                     Toast.LENGTH_SHORT).show();
                             //redirect to User Screen
                             Intent intent = new Intent("com.example.shopapp.User" );
                             startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this, "User or Password is NOT correct",
                                    Toast.LENGTH_SHORT).show();

                            attempt_counter--;
                            attempts.setText(Integer.toString(attempt_counter));
                            if (attempt_counter == 0) {
                                login_button.setEnabled(false);
                            }
                          } */
                        }

                    });
        }

    ///////////////////////////////////////////////////////////////////////////////
    //wrapper so you don't have to type this in everytime. This makes nice output messages to console, so you can see what you are doing.
    //////////////////////////////////////////////////////////////////////////////
    private void L(String s) {

        Log.d("MyApp", "LoginActivity" + "#######" + s);
    }

 }

