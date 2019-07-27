package com.example.pradnya.producttracking;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pradnya.producttracking.Info.ProductInfo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class Scanner extends AppCompatActivity {
    private static final String TAG = Scanner.class.getSimpleName();
    private DecoratedBarcodeView barcodeView;
    private BeepManager beepManager;
    private String lastText;
    private LinearLayout box_desc,box_desc_down;
    private Button scan_prod;
    static String cat_no,Desc,Box_quant,unique_no;
    private  TextView box_quant,desc,cat_id,prod_quant,box_no,date,pono,loc;
    private BarcodeCallback callback = new BarcodeCallback() {

        @Override

        public void barcodeResult(BarcodeResult result) {


            lastText = result.getText();
            String[] splits;  //cat-1,quant-7
            splits = lastText.split("\\|");

            if(splits.length<8){
                Toast.makeText(Scanner.this, "Somethins is Missing", Toast.LENGTH_SHORT).show();
            }
            else{
                unique_no=splits[0];
                box_quant.setText(splits[6]);
                Box_quant=splits[6];
                cat_id.setText(splits[1]);
                cat_no=splits[1];
                desc.setText("done");
                Desc="done";
                box_no.setText(splits[2]);
                date.setText(splits[3]);
                pono.setText(splits[4]);
                loc.setText(splits[5]);
                prod_quant.setText(splits[7]);

                box_desc.setVisibility(View.VISIBLE);
                box_desc_down.setVisibility(View.VISIBLE);
            }

            barcodeView.setStatusText(lastText);
            ProductInfo.box=lastText;

        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        box_desc=findViewById(R.id.ll_desc);
        box_desc_down=findViewById(R.id.ll_desc_down);

        box_quant=findViewById(R.id.box_quant);
        desc=findViewById(R.id.desc);
        cat_id=findViewById(R.id.cat_id);
        box_no=findViewById(R.id.board_no);
        prod_quant=findViewById(R.id.prod_quant);
        pono=findViewById(R.id.pono);
        loc=findViewById(R.id.loc);
        date=findViewById(R.id.date);

        box_desc.setVisibility(View.INVISIBLE);
        barcodeView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
        else{
            barcodeView.pause();
        }

        Collection<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39);
        barcodeView.initializeFromIntent(getIntent());
        barcodeView.decodeContinuous(callback);
        barcodeView.setStatusText("Please First Scan Box.");

        beepManager = new BeepManager(this);
        scan_prod=findViewById(R.id.btn_scan);

        scan_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Scanner.this, ProductScanner.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
        box_desc.setVisibility(View.INVISIBLE);
    }

    public void triggerScan(View view) {
        barcodeView.decodeSingle(callback);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }




}