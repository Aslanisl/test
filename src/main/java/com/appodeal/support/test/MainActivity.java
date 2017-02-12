package com.appodeal.support.test;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;

public class MainActivity extends AppCompatActivity {
    private static final String APP_KEY = "34c9e34bf2c6581929ffaceea03480565432a850b6354239";

    private TextView mTimerView;
    private long mStartTime = 0;
    private long mAppTimer = 0;
    private Boolean mButtonIsClicked = false;

    Handler mHandler = new Handler();
    Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - mStartTime;
            int seconds = (int) (millis / 1000);

            mTimerView.setText(String.format("%02d", seconds));

            if(Appodeal.isLoaded(Appodeal.BANNER_TOP)){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Appodeal.hide(MainActivity.this, Appodeal.BANNER_TOP);
                    }
                }, 5000);
            }

            if(seconds == 30 && !mButtonIsClicked){
                Appodeal.show(MainActivity.this, Appodeal.INTERSTITIAL | Appodeal.SKIPPABLE_VIDEO);
            }

            mHandler.postDelayed(this, 500);
        }
    };

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerView = (TextView) findViewById(R.id.timer_view);
        mButton = (Button) findViewById(R.id.button);

        mStartTime = System.currentTimeMillis();
        mAppTimer = System.currentTimeMillis();

        Appodeal.disableNetwork(this, "cheetah");

        Appodeal.disableLocationPermissionCheck();
        Appodeal.confirm(Appodeal.SKIPPABLE_VIDEO);
        Appodeal.initialize(this, APP_KEY, Appodeal.INTERSTITIAL
                | Appodeal.SKIPPABLE_VIDEO | Appodeal.BANNER_TOP | Appodeal.NATIVE);

        Appodeal.show(MainActivity.this, Appodeal.BANNER_TOP);

        mHandler.post(mRunnable);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - mAppTimer) < 30000) {
                    mButtonIsClicked = true;
                }
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        Appodeal.onResume(this, Appodeal.BANNER_TOP);
        mStartTime = System.currentTimeMillis();
        mHandler.post(mRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }
}
