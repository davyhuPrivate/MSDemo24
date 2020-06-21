package com.ms.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ms.demoapp.model.MsUser;
import com.ms.demoapp.utility.MsCommon;

public class LoginActivity extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSingIn;
    TextView txtSignUp, txtForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPassword = (EditText)findViewById(R.id.ms_edt_password);
        edtPhone = (EditText)findViewById(R.id.ms_edt_phone);
        btnSingIn = (Button)findViewById(R.id.ms_fbtn_sign_in);
        txtSignUp = (TextView) findViewById(R.id.ms_fbtn_sign_up);
        txtForgot = (TextView) findViewById(R.id.ms_txt_forgot);


        // initial firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");
        Log.w("LoginActivity", "**************" + table_user.getKey());

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUp);
            }
        });

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check if user not exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            // get user info
                            mDialog.dismiss();
                            MsUser user = dataSnapshot.child(edtPhone.getText().toString()).getValue(MsUser.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Toast.makeText(LoginActivity.this, "Sign in successfully! ", Toast.LENGTH_SHORT).show();
                                // redirect to nearme page
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                MsCommon.currentMsUser = user;
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Password is wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "MsUser not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
