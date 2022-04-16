package com.example.riyac.paymentmethod;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;

    final String payeeaddress="8299288499@upi";     //upi
    final String payeeName="XYZ";     //name of the person
    String transactionNode="Paying for this book";   //reason for paying
    String amount;
    String currencyUnit="INR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("upi://pay?pa=" + payeeaddress
                + "&pn=" + payeeName
                + "&tn=" + transactionNode
                + "&am=" + amount
                + "&cu=" + currencyUnit);
                Intent intent=new Intent(Intent.ACTION_VIEW, uri);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null)
        {
            String res=data.getStringExtra("response");
            String search="SUCCESS";
            if (res.toLowerCase().contains(search.toLowerCase()))
            {
                Toast.makeText(getApplicationContext(), "Successful.", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Payment Failed.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
