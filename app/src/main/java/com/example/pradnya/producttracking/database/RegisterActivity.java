package com.example.pradnya.producttracking.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pradnya.producttracking.R;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText etuserid;
    EditText etpass;
    EditText desig;
    Button register;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        etuserid = (EditText)findViewById(R.id.usrid);
        etpass = (EditText)findViewById(R.id.pass);
        desig = (EditText)findViewById(R.id.et_desig);
        register = (Button) findViewById(R.id.btn_reg);
        login = (TextView)findViewById(R.id.log);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginPage.class);
                startActivity(loginIntent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid = etuserid.getText().toString().trim();
                String pass = etpass.getText().toString().trim();
                String desigt = desig.getText().toString().trim();
               long val =  db.addUser(userid,pass,desigt);
                if(val>0){
                    Toast.makeText(RegisterActivity.this, "Registration Successfully done", Toast.LENGTH_SHORT).show();
                    Intent backtoLogin = new Intent(RegisterActivity.this, LoginPage.class);
                    startActivity(backtoLogin);
                    }
                    else {

                    Toast.makeText(RegisterActivity.this, "Error..!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
