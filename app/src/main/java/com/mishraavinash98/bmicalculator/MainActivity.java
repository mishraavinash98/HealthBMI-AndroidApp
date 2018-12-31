package com.mishraavinash98.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declare
    TextView tvName;
    SharedPreferences sp;
    EditText etHeightMetre,etHeightInch,etHeightFeets,etWeight;
    Button btnCalculate,btnViewHistory;
    static MyDbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //B
        tvName=(TextView)findViewById(R.id.tvName);
        sp=getSharedPreferences("f1",MODE_PRIVATE);
        etHeightMetre=(EditText)findViewById(R.id.etHeightMetre);
        etHeightInch=(EditText)findViewById(R.id.etHeightInch);
        etHeightFeets=(EditText)findViewById(R.id.etHeightFeets);
        etWeight=(EditText)findViewById(R.id.etWeight);
        btnCalculate=(Button)findViewById(R.id.btnCalculate);
        btnViewHistory=(Button)findViewById(R.id.btnViewHistory);
        db=new MyDbHandler(this);

        //getnamefromSP
        String name=sp.getString("name","");
        tvName.setText("Welcome "+name);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String hm=etHeightMetre.getText().toString();
                String hi=etHeightInch.getText().toString();
                String hf=etHeightFeets.getText().toString();
                String w=etWeight.getText().toString();

                if(hm.length()==0 && hi.length()==0 && hf.length()==0 ){
                    Toast.makeText(getApplicationContext(), "ENTER HEIGHT", Toast.LENGTH_SHORT).show();
                    etHeightMetre.requestFocus();
                    return;
                }
                if((hm.length()!=0 && hi.length()!=0 ) || (hi.length()!=0 && hf.length()!=0 )||(hf.length()!=0 && hm.length()!=0 )){
                    Toast.makeText(getApplicationContext(), "MULTIPLE HEIGHT FIELDS SELECTED ", Toast.LENGTH_SHORT).show();
                    etHeightMetre.requestFocus();
                    return;
                }

                double height=0;
                if(hm.length()!=0 && hi.length()==0 && hf.length()==0){
                     height=Double.parseDouble(hm);

                }else if(hm.length()==0 && hi.length()!=0 && hf.length()==0){
                   height=0.0254*(Double.parseDouble(hi));
                }
                else if(hm.length()==0 && hi.length()==0 && hf.length()!=0){
                    height=0.3048*(Double.parseDouble(hf));
                }

                if(height==0 ){
                    Toast.makeText(getApplicationContext(), "HEIGHT IS ZERO ", Toast.LENGTH_SHORT).show();
                    etHeightMetre.requestFocus();
                    return;
                }

                if(w.length()==0){
                    Toast.makeText(getApplicationContext(), "ENTER WEIGHT", Toast.LENGTH_SHORT).show();
                    etWeight.requestFocus();
                    return;
                }
                double weight=Double.parseDouble(w);
                if(weight==0){
                    Toast.makeText(getApplicationContext(), "WEIGHT CANNOT BE ZERO", Toast.LENGTH_SHORT).show();
                    etWeight.requestFocus();
                    return;
                }

                double BMI=weight/(height*height);
                if(w.length()==0){
                    Toast.makeText(getApplicationContext(), "ENTER WEIGHT", Toast.LENGTH_SHORT).show();
                    etWeight.requestFocus();
                    return;
                }

                //Toast.makeText(MainActivity.this, "BMI is "+BMI, Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("BMI",BMI);
                startActivity(intent);

            }
        });

        btnViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,ViewHistoryActivity.class);
                startActivity(intent);

            }
        });


    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this application?");
        builder.setCancelable(false);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert =builder.create();
        alert.setTitle("EXIT");
        alert.show();
    }
}
