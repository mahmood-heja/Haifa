package com.example.adellahlouh.haifa;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    FirebaseDatabase database;

    EditText edt_phone;
    Button btn_login;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        database = FirebaseDatabase.getInstance();

        edt_phone = findViewById(R.id.phoneNum);
        btn_login = findViewById(R.id.btn_login);

        mDatabase = database.getReference();


        DatabaseReference users = mDatabase.child("users");


        btn_login.setOnClickListener(v -> {

            users.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.child(edt_phone.getText().toString()).exists()) {
                        Toast.makeText(LoginActivity.this, "exists", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "not exists", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        });


    }


    public void goToSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
