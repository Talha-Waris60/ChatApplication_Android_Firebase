package com.devdroiddev.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.devdroiddev.chatapp.databinding.ActivityAuthenticationBinding;
import com.devdroiddev.chatapp.models.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthenticationActivity extends AppCompatActivity {

    String name,email,password;
    TextView registerUser;
    ActivityAuthenticationBinding binding;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference = FirebaseDatabase.getInstance().getReference("User Info");


        // Click Listener on Login Button
        binding.LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.email.getText().toString().trim();
                password = binding.password.getText().toString();
                
                loginUser();
            }
        });

        // Click listener on Register User
        binding.registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.userName.getText().toString();
                email = binding.email.getText().toString().trim();
                password = binding.password.getText().toString();

                // Create a method to signUp the user
                signUp();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            startActivity(new Intent(AuthenticationActivity.this,MainActivity.class));
            finish();
        }
    }

    private void loginUser() {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(AuthenticationActivity.this,MainActivity.class));
                        finish();
                    }
                });
    }

    // Create User
    private void signUp() {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        // String newName = "John Doe"; -- this name we want to change
                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        user.updateProfile(userProfileChangeRequest);

                        // To store the Data in Firebase-RealTime
                        // TODO: In setValue() we pass the userModel class
                        UserModel model = new UserModel(FirebaseAuth.getInstance().getUid(),name,email,password);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(model);
                        startActivity(new Intent(AuthenticationActivity.this,MainActivity.class));
                        finish();
                    }
                });
    }
}