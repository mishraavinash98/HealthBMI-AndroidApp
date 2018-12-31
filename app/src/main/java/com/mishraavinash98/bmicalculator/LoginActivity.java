package com.mishraavinash98.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //declare
    EditText etName,etPhoneNumber,etAge;
    RadioGroup rgGender;
    Button btnRegister;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //bind
        etName=(EditText)findViewById(R.id.etName);
        etPhoneNumber=(EditText)findViewById(R.id.etPhoneNumber);
        etAge=(EditText)findViewById(R.id.etAge);

        rgGender=(RadioGroup)findViewById(R.id.rgGender);

        btnRegister=(Button)findViewById(R.id.btnRegister);

        sp=getSharedPreferences("f1",MODE_PRIVATE);


        //perform
        String name=sp.getString("name","");
        //if user has saved the name before...(can clear the data from app for reuse)
        if(name.length()!=0){

            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        //for new user
        else{

            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name=etName.getText().toString();
                    String phoneNo=etPhoneNumber.getText().toString();
                    String a=etAge.getText().toString();
                    String gender;

                    if(name.length()==0){
                        Toast.makeText(getApplicationContext(), "INVALID NAME", Toast.LENGTH_SHORT).show();
                        etName.requestFocus();
                        return;
                    }

                    if(a.length()==0){
                        Toast.makeText(getApplicationContext(), "ENTER AGE INFORMATION", Toast.LENGTH_SHORT).show();
                        etAge.requestFocus();
                        return;
                    }
                    int age=Integer.parseInt(a);

                    if(phoneNo.length()==0){
                        Toast.makeText(getApplicationContext(), "INVALID PHONE NO", Toast.LENGTH_SHORT).show();
                        etPhoneNumber.requestFocus();
                        return;
                    }
                    if(rgGender.getCheckedRadioButtonId()==-1)
                    {
                        Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        // get selected radio button from radioGroup
                        int id = rgGender.getCheckedRadioButtonId();
                        // find the radiobutton by returned id
                        RadioButton selectedrb = (RadioButton)findViewById(id);
                        gender=selectedrb.getText().toString();
                        Toast.makeText(getApplicationContext(), gender+" is selected", Toast.LENGTH_SHORT).show();
                    }

                    //SP commit
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("name",name);
                    editor.putInt("age",age);
                    editor.putString("phoneNo",phoneNo);
                    editor.putString("gender",gender);
                    editor.commit();

                    //LOGIN_ACTI to MAIN ACTI
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            });
        }

    }
}
