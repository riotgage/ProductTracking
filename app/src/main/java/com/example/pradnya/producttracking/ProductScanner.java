package com.example.pradnya.producttracking;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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
    TextView box_quant,desc,cat_id;
    private DecoratedBarcodeView barcodeView;
    private BeepManager beepManager;
    private String text="";
    private ArrayList<String> list;
    private BarcodeCallback callback = new BarcodeCallback() {
        @Override

        public void barcodeResult(BarcodeResult result) {
            if(text.equals(result.getText())){
                return;
            }
            text=result.getText();
            if(list.contains(text)){
                Toast.makeText(ProductScanner.this, "Product Already Scanned", Toast.LENGTH_SHORT).show();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vib.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    vib.vibrate(500);
                }
                return;
            }
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

        box_quant.setText(Scanner.Box_quant);
        desc.setText(Scanner.Desc);
        cat_id.setText(Scanner.cat_no);
        list=new ArrayList<>();
        barcodeView = findViewById (R.id.scanner);

        Collection<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39);
        barcodeView.initializeFromIntent(getIntent());
        barcodeView.decodeContinuous(callback);
        barcodeView.setStatusText("Please First Scan .");

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
