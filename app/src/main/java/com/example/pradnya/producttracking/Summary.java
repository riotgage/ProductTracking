package com.example.pradnya.producttracking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pradnya.producttracking.Info.InformationAdapter;
import com.example.pradnya.producttracking.Info.ProductInfo;
import com.example.pradnya.producttracking.Info.info;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {


    Button btn_prod_scan,btn_accept,btn_reject;

    private TextView box_quant,desc,cat_id;
    private ListView l;
    private ArrayList<info>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        btn_prod_scan=findViewById(R.id.btn_prod_scan);
        btn_reject=findViewById(R.id.reject);
        btn_accept=findViewById(R.id.accept);
        box_quant=findViewById(R.id.box_quant);
        desc=findViewById(R.id.desc);
        cat_id=findViewById(R.id.cat_id);
        l=findViewById(R.id.list);

        box_quant.setText(Scanner.Box_quant);
        desc.setText(Scanner.Desc);
        cat_id.setText(Scanner.cat_no);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setIcon(R.mipmap.question);
        builder.setMessage("Are you sure you want to cancel all previous scans ?");
        builder.setCancelable(false);

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                ProductInfo.list.clear();
                ProductInfo.information.clear();
                startActivity(new Intent(Summary.this,ProductScanner.class));
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        final AlertDialog dialog=builder.create();

        btn_prod_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Summary.this,ProductScanner.class));
            }
        });

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Summary.this,Store.class));
            }
        });

        btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        list=ProductInfo.information;
        Toast.makeText(this, Integer.toString(list.size()), Toast.LENGTH_SHORT).show();
        InformationAdapter adapter=new InformationAdapter(this,R.layout.list_item,list);
        l.setAdapter(adapter);
    }


}
