/*Group Members: SIVALINGAM SUNDARARAJ SHANTHI z1829451
                 YOKESH SRIHARI z1809328*/

/////////////////////////////////////////////////////////////////////////////////
//This activity is used to display the splash screen at the begining of the app//
/////////////////////////////////////////////////////////////////////////////////
package edu.niu.cs.z1829451.assignment4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,5000); //Set the timer for the splash screen
    }
}
