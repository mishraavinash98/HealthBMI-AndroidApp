package com.mishraavinash98.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewHistoryActivity extends AppCompatActivity {

    TextView tvViewHistoryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        tvViewHistoryData=(TextView)findViewById(R.id.tvViewHistoryData);

        String data=MainActivity.db.viewRecord();

        if(data.length()==0)
            tvViewHistoryData.setText("No Records for now");
        else
            tvViewHistoryData.setText(data);
    }
}
