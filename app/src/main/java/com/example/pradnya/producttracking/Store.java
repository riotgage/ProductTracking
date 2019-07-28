package com.example.pradnya.producttracking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pradnya.producttracking.Info.ProductInfo;
import com.example.pradnya.producttracking.database.Constants;
import com.example.pradnya.producttracking.database.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Store extends AppCompatActivity {

    private TextView box_quant,desc,cat_id,uni_no;
    ProgressBar bar;
    String res;
    ProgressDialog dialog;
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
        box_quant.setText(Scanner.Prod_quant);
        desc.setText(Scanner.Desc);
        cat_id.setText(Scanner.cat_no);
        bar=findViewById(R.id.progressBar);
    }

    public void store(View view) {

        Toast.makeText(this, "Storing Data", Toast.LENGTH_SHORT).show();
        checkProducts(ProductInfo.list,ProductInfo.list.get(5));
        finish();
    }

    private void checkProducts(final ArrayList<String> barcodes, final String s) {
        dialog=new ProgressDialog(this);
        dialog.setMessage("Please wait, while we fetch information");
        dialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_CHECK_Product, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    res=jsonObject.getString("message");
                    //Toast.makeText(CodeList.this, "fuck this", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Store.this,Scanner.class));

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        res="True";
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("box_id",barcodes.get(0));
                params.put("prod_id",s);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void CreateTable(final ArrayList<String> barcode){
        dialog=new ProgressDialog(this);
        dialog.setMessage("Creating Box");
        dialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_Create_Table, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("box_id",barcode.get(0));
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}
