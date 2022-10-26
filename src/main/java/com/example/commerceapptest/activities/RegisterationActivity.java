package com.example.commerceapptest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.commerceapptest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterationActivity extends AppCompatActivity {

    EditText name,email,password;
    private FirebaseAuth auth;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        getSupportActionBar().hide();

        name = findViewById(R.id.name);
        email= findViewById(R.id.email);
        password = findViewById(R.id.password);

        auth=FirebaseAuth.getInstance();

        /*if(auth.getCurrentUser() != null){
            startActivity(new Intent(RegisterationActivity.this, MainActivity.class));
            finish();
        }*/

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        /*sharedPreferences=getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

        boolean isFirstTime=sharedPreferences.getBoolean("firstTime", true);

        if(isFirstTime){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("FirstTime",false);
            editor.commit();

            Intent intent=new Intent(RegisterationActivity.this, OnBoardingActivity.class);
            startActivity(intent);
            finish();
        }*/

    }

    public void signUp(View view) {
        String userName=name.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Enter Name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Enter Email Address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPassword.length() < 6){
            Toast.makeText(this,"Password too shor, enter minimum 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(RegisterationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterationActivity.this, "Successfully Register ", Toast.LENGTH_SHORT).show();
                            FirebaseUser user=auth.getCurrentUser();
                            startActivity(new Intent(RegisterationActivity.this, OnBoardingActivity.class));
                            finish();
                        }else{
                            Toast.makeText(RegisterationActivity.this, "Registration Failed"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    public void signIn(View view) {
        startActivity(new Intent(RegisterationActivity.this, LoginActivity.class));
    }
}