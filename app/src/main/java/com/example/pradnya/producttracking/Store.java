package com.example.pradnya.producttracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pradnya.producttracking.Info.ProductInfo;

public class Store extends AppCompatActivity {

    private TextView box_quant,desc,cat_id,uni_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        box_quant=findViewById(R.id.box_quant);
        desc=findViewById(R.id.desc);
        cat_id=findViewById(R.id.cat_id);
        uni_no=findViewById(R.id.uni_no);
        String[] splits=ProductInfo.list.get(0).split("\\|");
        uni_no.setText(splits[0]);
        box_quant.setText(Scanner.Box_quant);
        desc.setText(Scanner.Desc);
        cat_id.setText(Scanner.cat_no);
    }

    public void store(View view) {
        Toast.makeText(this, "Storing Data", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,Scanner.class));
        finish();
    }
}
