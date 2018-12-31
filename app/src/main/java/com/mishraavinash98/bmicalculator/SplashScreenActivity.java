package com.mishraavinash98.bmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashScreenActivity extends AppCompatActivity {

    TextView tvSplash;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Animation zoom= AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation fadein=AnimationUtils.loadAnimation(this,R.anim.fade_in);

        tvSplash=(TextView)findViewById(R.id.tvSplash);
        ivLogo=(ImageView)findViewById(R.id.ivLogo);

        ivLogo.setAnimation(zoom);
        tvSplash.setAnimation(fadein);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    Thread.sleep(3000);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }

                Intent i =new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        }).start();

    }
}
