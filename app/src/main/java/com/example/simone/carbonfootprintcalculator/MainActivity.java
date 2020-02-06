package com.example.simone.carbonfootprintcalculator;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    // connection to Server
    public static Socket socket;
    public String host = "";
    public int port = 0;
    public static BufferedReader in = null;
    public static PrintWriter out = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        communicate();

        Intent intent = new Intent(getApplicationContext(), Welcome.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fr=null;
        int id = item.getItemId();

        if (id == R.id.action_menu1) {
            Toast.makeText(this, "action Menu1", Toast.LENGTH_SHORT).show();
            //fr = new ListOptions();
            Intent intent = new Intent(getApplicationContext(), ListOptions.class);
            startActivity(intent);
        }
        if (id == R.id.action_menu2) {
            Toast.makeText(this, "action Menu2", Toast.LENGTH_SHORT).show();
            //fr = new activity for menu2();
        }

        if (id == R.id.action_logout) {
            Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (fr!=null) {
            FragmentManager fm2 = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm2.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_main, fr);
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    // send message to server
    public static void sendMessageToServer(final String str) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
                    if (!str.isEmpty()){
                        out.println(str);
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // connection
    public void communicate() {

        new Thread(new Runnable() {
            public void run() {

                try {
                    socket = new Socket(host, port);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    Log.d("", "unknown host*");
                } catch (IOException e) {
                    Log.d("", "io exception*");
                    e.printStackTrace();
                }

                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (true) {
                    String msg = null;
                    try {
                        msg = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (msg == null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    else if (msg.equals("0")) {
                        Intent intent = new Intent(getApplicationContext(), ListOptions.class);
                        startActivity(intent);
                    }
                    else if (msg.equals("1")) {
                        showAlert("Invalid username or password");
                    }
                    else if (msg.startsWith("-")) {     //calculation results
                        int i = msg.indexOf('@');
                        String co2 = msg.substring(1, i);
                        String carEquiv = msg.substring(i+1);
                        Intent intent = new Intent(getApplicationContext(), Results.class);

                        //Create the bundle
                        Bundle bundle = new Bundle();

                        //Add your data to bundle
                        bundle.putString("co2", co2);
                        bundle.putString("car", carEquiv);

                        //Add the bundle to the intent
                        intent.putExtras(bundle);

                        //Fire that second activity
                        startActivity(intent);
                    }
                    else if (msg.equals("4")) {
                        showAlert("Registration is successful!");
                        Intent intent = new Intent(getApplicationContext(), ListOptions.class);
                        startActivity(intent);
                    }
                    else if (msg.equals("5")) {
                        showAlert("Unable to register. Try again later.");
                    }
                    else if (msg.equals("6")) {
                        showAlert("Invalid input. Please enter a number.");
                    }
                    else if (msg.equals("7")) {
                        showAlert("This food item does not exist in the database.");
                    }
                    else if (msg.equals("8")) {
                        showAlert("This username is already taken.");
                    }
                    else {
                        // do some more
                    }
                }  // end while

            }  // end run

        }).start();
    }

    public void showAlert(final String alertMessage)
    {
        runOnUiThread(() -> {
            Toast toast = Toast.makeText(this, alertMessage, Toast.LENGTH_LONG);
            View view = toast.getView();

            view.setBackgroundColor(Color.DKGRAY);
            TextView text = view.findViewById(android.R.id.message);

            //Shadow of the Of the Text Color
            text.setShadowLayer(0, 0, 0, Color.TRANSPARENT);
            text.setGravity(Gravity.CENTER);
            text.setTextColor(Color.WHITE);
            text.setTextSize(20);
            toast.show();
        });
    }


}
