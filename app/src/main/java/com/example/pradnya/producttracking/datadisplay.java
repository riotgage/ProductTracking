package com.example.pradnya.producttracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class datadisplay extends AppCompatActivity {
    private Button accept_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datadisplay);
        accept_data = findViewById(R.id.acc);
        accept_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptingData();
            }
        });
    }

    private void acceptingData() {

        Intent intent = new Intent(this, ustore.class);
        startActivity(intent);
    }
}
