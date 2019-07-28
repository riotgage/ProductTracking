package com.example.pradnya.producttracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QA_FirstPage extends AppCompatActivity {

    Button scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa__first_page);
        scan_btn = (Button)findViewById(R.id.btn_scan);
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent QAlocation =  new Intent(QA_FirstPage.this, QA_locationscanner.class);
                startActivity(QAlocation );
            }
        });
    }
}
