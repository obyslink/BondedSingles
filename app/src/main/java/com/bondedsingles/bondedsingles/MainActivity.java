package com.bondedsingles.bondedsingles;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    //SPLASH SCREEN ACTIVITY FOR 2000
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView myImageView = (ImageView) findViewById(R.id.imageView);
        TextView myTextView = (TextView) findViewById(R.id.textView);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.tween);
        myImageView.startAnimation(myFadeInAnimation);
        myTextView.startAnimation(myFadeInAnimation);


        new Handler().postDelayed(new Runnable(){
            @Override
            public  void run(){
                Intent mainIntent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(mainIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
