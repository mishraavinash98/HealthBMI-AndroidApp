package com.mishraavinash98.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    TextView tvResultBMI,tvResultCondtion,tvResultUnderWeight,tvResultNormal,tvResultOverWeight,tvResultObese;
    Button btnResultBack,btnSaveResult,btnShareResult;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResultBMI=(TextView)findViewById(R.id.tvResultBMI);
        tvResultCondtion=(TextView)findViewById(R.id.tvResultCondtion);

        tvResultUnderWeight=(TextView)findViewById(R.id.tvResultUnderWeight);
        tvResultNormal=(TextView)findViewById(R.id.tvResultNormal);
        tvResultOverWeight=(TextView)findViewById(R.id.tvResultOverWeight);
        tvResultObese=(TextView)findViewById(R.id.tvResultObese);

        btnResultBack=(Button)findViewById(R.id.btnResultBack);
        btnShareResult=(Button)findViewById(R.id.btnShareResult);
        btnSaveResult=(Button)findViewById(R.id.btnSaveResult);

        sp=getSharedPreferences("f1",MODE_PRIVATE);


        Intent intent=getIntent();
        final double BMI=intent.getDoubleExtra("BMI",0);

        tvResultBMI.setText("Your BMI is "+BMI);
        tvResultCondtion.setText("Your condition is ");

        if(BMI<18.5000){
            String condition="Under Weight";
            tvResultCondtion.append(condition);
            tvResultUnderWeight.setTextColor(Color.parseColor("#FF0000"));

        }else if(BMI>=18 && BMI<25){
            String condition="Normal";
            tvResultCondtion.append(condition);
            tvResultNormal.setTextColor(Color.parseColor("#FF0000"));

        }else if(BMI>25 && BMI<30){
            String condition="Over Weight";
            tvResultCondtion.append(condition);
            tvResultOverWeight.setTextColor(Color.parseColor("#FF0000"));

        }else{
            String condition="Obese";
            tvResultCondtion.append(condition);
            tvResultObese.setTextColor(Color.parseColor("#FF0000"));

        }

        btnResultBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Refreshes the Main Activity
                Intent intent1=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent1);
                */

                //To avoid refreshing
                ResultActivity.super.onBackPressed();
            }
        });

        btnShareResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get info from SP
                String name=sp.getString("name","");
                int age=sp.getInt("age",0);
                String phoneNo=sp.getString("phoneNo","");
                String gender=sp.getString("gender","");

                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,"Name : "+name+"\nAge : "+age+"\nPhone No : "+phoneNo+"\nGender : "+gender+"\nBMI : "+BMI+
                        "\n"+tvResultCondtion.getText().toString());
                startActivity(i);

            }
        });

        btnSaveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get info from SP
                String name=sp.getString("name","");
                String date = DateFormat.getDateTimeInstance().format(new Date());
                String condition=tvResultCondtion.getText().toString();

                //Toast.makeText(ResultActivity.this, name+" "+date+" "+BMI, Toast.LENGTH_SHORT).show();

                MainActivity.db.addRecord(name,date,BMI,condition);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.About){

            Intent intent =new Intent(ResultActivity.this,AboutActivity.class);
            startActivity(intent);
            return false;
        }

        if(item.getItemId()==R.id.Website){

            Toast.makeText(this, "website", Toast.LENGTH_SHORT).show();
            Intent website=new Intent(Intent.ACTION_VIEW);
            website.setData(Uri.parse("https://"+"github.com/mishraavinash98"));
            startActivity(website);
        }
        if(item.getItemId()==R.id.Contact){


            Toast.makeText(this, "About to call the app service office", Toast.LENGTH_SHORT).show();

            Intent c=new Intent(Intent.ACTION_VIEW);
            c.setData(Uri.parse("tel:"+"9967605390"));
            startActivity(c);
        }

        return super.onOptionsItemSelected(item);
    }

}
