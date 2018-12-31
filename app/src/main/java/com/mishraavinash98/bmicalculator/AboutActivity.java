package com.mishraavinash98.bmicalculator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {

    ImageButton ibInstagram,ibFacebook,ibLinkedIn,ibGooglePlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ibInstagram=(ImageButton)findViewById(R.id.ibInstagram);
        ibFacebook=(ImageButton)findViewById(R.id.ibFacebook);
        ibLinkedIn=(ImageButton)findViewById(R.id.ibLinkedIn);
        ibGooglePlus=(ImageButton)findViewById(R.id.ibGooglePlus);

        ibInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton("https://www.instagram.com/mishraavinash98");
            }
        });

        ibGooglePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton("https://plus.google.com/+AvinashMishra98");
            }
        });
        ibLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton("https://www.linkedin.com/in/mishraavinash98");
            }
        });
        ibFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButton("https://www.facebook.com/mishraavinash98");
            }
        });
    }
    public void clickedButton(String url){

        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
