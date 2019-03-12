package com.example.shopapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    public static EditText user;
    public static EditText password;
    private static Button login_button;
    private static TextView attempts;
    int attempt_counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton();
    }

    public void LoginButton() {

        user          = (EditText)findViewById(R.id.editText_user);
        password      = (EditText)findViewById(R.id.editText_password);
        attempts      = (TextView)findViewById(R.id.textView_attempt_counter);
        login_button  = (Button)  findViewById(R.id.button_login);

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
                            Networking n = new Networking();
                            n.execute();
                            return;
                        }

                        if (user.getText().toString().equals("Denise")&&
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
                          }
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

