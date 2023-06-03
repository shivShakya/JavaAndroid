package com.example.stripe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    private static final String MERCHANT_ID = "8318357977@ibl";
    private static final String MERCHANT_NAME = "Shivam Shakya";
    private static final String MERCHANT_CODE = "your_merchant_code";
    private static final String TRANSACTION_ID = "your_transaction_id";
    private static final String TRANSACTION_AMOUNT = "your_transaction_amount";
    private static final String TRANSACTION_URL = "your_transaction_url";

    private Button phonePeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phonePeButton = findViewById(R.id.button);

        phonePeButton.setOnClickListener(view -> {
            Uri uri = Uri.parse("upi://pay").buildUpon()
                    .appendQueryParameter("pa", MERCHANT_ID)
                    .appendQueryParameter("pn", MERCHANT_NAME)
                    .appendQueryParameter("mc", MERCHANT_CODE)
                    .appendQueryParameter("tr", TRANSACTION_ID)
                    .appendQueryParameter("tn", "Purchase")
                    .appendQueryParameter("am", TRANSACTION_AMOUNT)
                    .appendQueryParameter("cu", "INR")
                    .appendQueryParameter("url", TRANSACTION_URL)
                    .build();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage("com.phonepe.app");

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "PhonePe app not installed", Toast.LENGTH_SHORT).show();
            }
        });


    }


}