package com.example.dummyfbloginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegister extends AppCompatActivity {

    private Button reg;
    private EditText firstName,lastName,email,phone,password;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUI_cont();

        firebaseAuth = FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    String user_name = email.getText().toString().trim();
                    String user_pass = password.getText().toString().trim();
                    String user_phone = phone.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_name,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(ActivityRegister.this, "Congratulations!! You have successfully registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ActivityRegister.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(ActivityRegister.this, "Sorry, registration failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityRegister.this,MainActivity.class));
            }
        });

    }
    private void setupUI_cont()
    {
        firstName = (EditText)findViewById(R.id.fst_name);
        lastName = (EditText) findViewById(R.id.lst_name);
        email = (EditText) findViewById(R.id.email_id);
        password = (EditText) findViewById(R.id.pass);
        phone = (EditText) findViewById(R.id.phone_no);
        reg = (Button) findViewById(R.id.btn_reg);
        userLogin = (TextView) findViewById(R.id.take_login);

    }
    private Boolean validate()
    {
        Boolean result = false;
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String user_email = email.getText().toString();
        String pass = password.getText().toString();
        String pno = phone.getText().toString();
        if(fName.isEmpty())
            Toast.makeText(this, "Please enter your first name", Toast.LENGTH_SHORT).show();
        else if(lName.isEmpty())
            Toast.makeText(this, "Please enter your last name", Toast.LENGTH_SHORT).show();
        else if(user_email.isEmpty())
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        else if(pass.isEmpty())
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show();
        else if(pno.isEmpty())
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
        else
            result = true;
        return result;

    }
}