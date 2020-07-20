package com.example.sharethemeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    Spinner sp;
    EditText username,email,phone,address,password;
    Button register;
    DatabaseReference mref;
    FirebaseAuth mauth;
    TextView al,toogle;
    private static final String[] paths = {"Restaurant", "NGO"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sp = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        register = findViewById(R.id.reg);
        al = findViewById(R.id.textView2);
        mauth = FirebaseAuth.getInstance();
        toogle = findViewById(R.id.toggle);
        toogle.setVisibility(View.GONE);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(password.getText().length() > 0){
                   toogle.setVisibility(View.VISIBLE);
               }
               else{
                   toogle.setVisibility(View.GONE);
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        toogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toogle.getText() == "Show"){
                    toogle.setText("Hide");
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                }
                else{
                    toogle.setText("Show");
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setSelection(password.length());
                }
            }
        });
        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_phone = phone.getText().toString();
                String txt_address = address.getText().toString();
                String txt_spinner = sp.getSelectedItem().toString();

                if(TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email)
                        ||TextUtils.isEmpty(txt_password)||TextUtils.isEmpty(txt_phone)
                        ||TextUtils.isEmpty(txt_address)||TextUtils.isEmpty(txt_spinner)){
                  Toast.makeText(RegisterActivity.this,"All Fields are required",Toast.LENGTH_SHORT).show();
              }
              else if(txt_password.length() < 6){
                  Toast.makeText(RegisterActivity.this,"Password Length should be graeter than 6",Toast.LENGTH_SHORT).show();

              }
              else{
                  register(txt_username,txt_phone,txt_spinner,txt_address,txt_email,txt_password);
              }

            }
        });
    }
    private void register(final String username,final String phone,final String spinner,final String address,String email, String password){
        mauth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                        FirebaseUser firebaseUser = mauth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();
                        mref = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("id",userid);
                        hashMap.put("username",username);
                        hashMap.put("phone",phone);
                        hashMap.put("Select one",spinner);
                        hashMap.put("address",address);

                        mref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,"Registeration successful",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                    else{
                            Toast.makeText(RegisterActivity.this,"You can't register",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
