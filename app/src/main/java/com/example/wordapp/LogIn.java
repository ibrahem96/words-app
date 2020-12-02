package com.example.wordapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {


    Button btnLog;
    Button btnAdd;
    EditText TxtName;
    EditText TxtPass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        btnLog = findViewById(R.id.btnLog);
        btnAdd = findViewById(R.id.btnAdd);
        TxtName = findViewById(R.id.TxtName);
        TxtPass = findViewById(R.id.TxtPass);
        mAuth = FirebaseAuth.getInstance();

        final Intent i = new Intent(this, MainActivity.class);
        if (mAuth.getCurrentUser() != null) {
            startActivity(i);
            return;
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword("Ibrahem@yahoo.com", "456789")
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    // Log.d("Login", "createUserWithEmail:success");
                                    Toast.makeText(LogIn.this, "User E-Mail Created ", Toast.LENGTH_SHORT).show();


                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w("Login", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "E-Mail saved already!!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(TxtName.getText().toString(), TxtPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(i);

                                } else {
                                    //   Log.w("Login", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed:" + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
//
//
//        btnLog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signInWithEmailAndPassword(TxtName.getText().toString(),TxtPass.getText().toString() )
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    startActivity(i);
//
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w("Login", "createUserWithEmail:failure", task.getException());
//                                    Toast.makeText(getApplicationContext(), "Authentication failed:"+task.getException().getMessage(),
//                                            Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//                        });
//
//
//            }
//     FirebaseUser user = mAuth.getCurrentUser();
//                                    user.getUid();
//                                    mAuth.signOut();
//                  });
//
//



                /*
                myRef.setValue(TxtName.getText().toString());
                myRef.push();

                 */



/*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                TxtName.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

 */


    }
}
