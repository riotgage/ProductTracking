package com.example.pradnya.producttracking;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ProductScanner extends AppCompatActivity {
    private TextView box_quant,desc,cat_id,prod_count;
    private DecoratedBarcodeView barcodeView;
    private BeepManager beepManager;
    private String text="";
    private Button prod_scan;
    static  int count=0;

    private ArrayList<String> list;
    String unique_id,prod_quant,prod_quant_no;
    private BarcodeCallback callback = new BarcodeCallback() {
        @Override

        public void barcodeResult(BarcodeResult result) {
            if(text.equals(result.getText())){
                return;
            }
            text=result.getText();
            String[] splits;
            splits=text.split("\\|");
            if(splits.length<7){
                Toast.makeText(ProductScanner.this, "Something is missing", Toast.LENGTH_SHORT).show();
                return;
            }
            unique_id=splits[0];
            prod_quant=splits[5];
            if(!unique_id.equals(Scanner.unique_no)){
                Toast.makeText(ProductScanner.this, "Unique ID miss match ,Try again", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vib.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    vib.vibrate(500);
                }
                return;
            }
            if(list.contains(text)){
                Toast.makeText(ProductScanner.this, "Product Already Scanned", Toast.LENGTH_SHORT).show();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vib.vibrate(VibrationEffect.createOneShot(500,1));
                } else {
                    vib.vibrate(500);
                }
                return;
            }
            count+=1;
            list.add(text);
            barcodeView.setStatusText(text);


        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };
    private Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_prod);
        vib=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        box_quant=findViewById(R.id.box_quant);
        desc=findViewById(R.id.desc);
        cat_id=findViewById(R.id.cat_id);
        prod_count=findViewById(R.id.prod_count);
        box_quant.setText(Scanner.Box_quant);
        desc.setText(Scanner.Desc);
        cat_id.setText(Scanner.cat_no);
        list=new ArrayList<>();
        barcodeView = findViewById (R.id.scanner);

        Collection<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39);
        barcodeView.initializeFromIntent(getIntent());
        barcodeView.decodeContinuous(callback);
        barcodeView.setStatusText("Please First Scan .");
        prod_count.setText(Integer.toString(count));
        beepManager = new BeepManager(this);

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
    }

    public void triggerScan(View view) {
        barcodeView.decodeSingle(callback);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
