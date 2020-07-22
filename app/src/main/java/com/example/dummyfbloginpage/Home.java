package com.example.dummyfbloginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button) findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                Toast.makeText(Home.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this,MainActivity.class));
            }
        });
    }
}