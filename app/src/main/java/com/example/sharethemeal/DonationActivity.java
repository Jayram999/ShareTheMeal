package com.example.sharethemeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonationActivity extends AppCompatActivity {

    TextView mTitletv,mDescriptiontv;
     Button donate;
    OrderDetails orderDetails;
    DatabaseReference myRef;
    long maxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        ActionBar actionBar = getSupportActionBar();
        mTitletv = findViewById(R.id.foodName);
        mDescriptiontv = findViewById(R.id.foodDescription);
        donate = findViewById(R.id.donate);

        Intent intent = getIntent();
        // Write a message to the database
        myRef = FirebaseDatabase.getInstance("https://firstproject-5e922.firebaseio.com")
                .getReference("Orders");
        myRef.setValue("Hello, World!");
        final String mTitle = intent.getStringExtra("iTitle");
        final String mDescription = intent.getStringExtra("iDescription");

        actionBar.setTitle(mTitle);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mTitletv.setText(mTitle);
        mDescriptiontv.setText(mDescription);
        orderDetails = new OrderDetails();

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getRootView().getContext())
                        .setMessage(""+mTitle)
                        .setTitle("Do you want to Donate this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                orderDetails.setName(mTitle);
                                orderDetails.setQuantity(mDescription);
                                myRef.child(String.valueOf(maxid+1)).setValue(orderDetails);
                                Toast.makeText(getApplicationContext(),"Donation Successful",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Result","Success");
                            }
                        })
                        .show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
