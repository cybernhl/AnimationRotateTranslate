package com.example.animationrotatetranslate;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CopyOfMainActivity extends Activity
{
    private ImageView imgView1, imgView2, imgView3, imgView4, imgView5, imgView6, imgView7;
    private Button btnStart;
    private LinearLayout llNiuDanJi;
    private int circle = 3;
    private int baseTimeMillis = 100;
    private int timeMillis = 1500;
    private int moveHeight = 200;
    private Random random = new Random();
    private int count = 30;
    private int xoffset = 150;

    private int screenHeight = 0;
    private int screenWidth = 0;
    private float baseDegrees = 1800f;
    private int baseDegreesRand = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bak);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new NiuDanAnimationListener());

        // llNiuDanJi = (LinearLayout) findViewById(R.id.ll_ndj);

        imgView1 = (ImageView) findViewById(R.id.imgView_1);
        imgView2 = (ImageView) findViewById(R.id.imgView_2);
        imgView3 = (ImageView) findViewById(R.id.imgView_3);
        imgView4 = (ImageView) findViewById(R.id.imgView_4);
        imgView5 = (ImageView) findViewById(R.id.imgView_5);
        imgView6 = (ImageView) findViewById(R.id.imgView_6);
        imgView7 = (ImageView) findViewById(R.id.imgView_7);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        screenHeight = windowManager.getDefaultDisplay().getHeight();
        screenWidth = windowManager.getDefaultDisplay().getWidth();

        Toast.makeText(this, "ÆÁÄ»¸ß¿í£¬screenWidth=" + screenWidth + ",screenHeight=" + screenHeight, Toast.LENGTH_SHORT).show();

    }

    class NiuDanAnimationListener implements OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            startAnimation(imgView1, baseDegrees + random.nextInt(baseDegreesRand));
            startAnimation(imgView2, -(baseDegrees + random.nextInt(baseDegreesRand)));
            startAnimation(imgView3, baseDegrees + random.nextInt(baseDegreesRand));
            startAnimation(imgView4, -(baseDegrees + random.nextInt(baseDegreesRand)));
            startAnimation(imgView5, baseDegrees + random.nextInt(baseDegreesRand));
            startAnimation(imgView6, -(baseDegrees + random.nextInt(baseDegreesRand)));
            startAnimation(imgView7, baseDegrees + random.nextInt(baseDegreesRand));
        }
    }

    private void startAnimation(View view, float toDegrees)
    {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(timeMillis);
        animationSet.setFillAfter(true);

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        float xstart = random.nextInt(100);
        float xend = random.nextInt(100);

        Toast.makeText(this, "Å¤µ°×ø±ê£¬x=" + x + ",y=" + y, Toast.LENGTH_SHORT).show();
        if (x + xstart + xoffset > screenWidth)
        {
            xstart = -xstart;
        }

        if (x + xend + xoffset > screenWidth)
        {
            Toast.makeText(this, "Å¤µ°×ø±ê£¬x=" + x + ",y=" + y, Toast.LENGTH_SHORT).show();
            xend = -xend - xoffset;
        }

        TranslateAnimation translateAnimation = new TranslateAnimation(xstart, xend, random.nextInt(10) + random.nextInt(20), -random.nextInt(100));
        translateAnimation.setDuration(baseTimeMillis);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        // view.setAnimation(translateAnimation);
        // translateAnimation.start();
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);

        // RotateAnimation rotateAnimation = new RotateAnimation(0f, 900f, 0.5f, 0.5f);
        RotateAnimation rotateAnimation = new RotateAnimation(0f, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(baseTimeMillis);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setFillAfter(true);
        animationSet.addAnimation(rotateAnimation);

        view.startAnimation(animationSet);
    }
}
