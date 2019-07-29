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
import com.example.pradnya.producttracking.Scanner;

public class LoginPage extends AppCompatActivity {

    EditText etextuser;
    EditText etextpassword;
    TextView textRegister;
    TextView textadmin;
    Button btn_login;
    DatabaseHelper db;
    Button go;
    EditText admin_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        db = new DatabaseHelper(this);
        etextuser = (EditText) findViewById(R.id.usrid);
        etextpassword = (EditText) findViewById(R.id.pass);
        btn_login = (Button) findViewById(R.id.btn_login);
        textadmin = (TextView) findViewById(R.id.admin);
        textRegister = (TextView) findViewById(R.id.reg);
        go = (Button) findViewById(R.id.btn_go);
        admin_pass = (EditText) findViewById(R.id.adminpass);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent registerIntent = new Intent(LoginPage.this, RegisterActivity.class);
                admin_pass.setVisibility(View.VISIBLE);
                go.setVisibility(View.VISIBLE);
                go.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                           String adminpass = admin_pass.getText().toString();
                        if (adminpass.equals("12345")){
                            Toast.makeText(getApplication(),"Confirmed as Admin", Toast.LENGTH_SHORT).show();
                            startActivity(registerIntent);
                        }
                        else {
                            Toast.makeText(getApplication(),"Cannot register Contact Admin", Toast.LENGTH_SHORT).show();
                        }
                    }   });

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etextuser.getText().toString().trim();
                String pwd = etextpassword.getText().toString().trim();
                boolean res = db.checkUser(user,pwd);
                if(res == true )
                {
                    Toast.makeText(getApplication(),"Successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent scannerIntent = new Intent(LoginPage.this, Scanner.class);
                    startActivity(scannerIntent);
                }
                else
                {
                    Toast.makeText(getApplication(),"Incorrect Userid or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        }

    }

