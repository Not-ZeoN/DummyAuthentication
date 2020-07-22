package com.example.dummyfbloginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText User_email, User_pass;
    private Button login_btn, reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        User_email = (EditText) findViewById(R.id.user_name);
        User_pass = (EditText) findViewById(R.id.user_pass);
        login_btn = (Button) findViewById(R.id.btn_log);
        reg=(Button) findViewById(R.id.new_acc_btn);



        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user !=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,Home.class));
        }
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivityRegister.class);
                startActivity(intent);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin(User_email.getText().toString(),User_pass.getText().toString());
            }
        });
    }
    private void validateLogin(String userName, String userPass)
    {
        firebaseAuth.signInWithEmailAndPassword(userName, userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Home.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}