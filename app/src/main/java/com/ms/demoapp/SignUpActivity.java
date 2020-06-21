package com.ms.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ms.demoapp.model.MsUser;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUpActivity extends AppCompatActivity {

    MaterialEditText edtPhone, edtName, edtPassword;
    Button btnSignUp;
    private int isNewUser = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = (MaterialEditText)findViewById(R.id.ms_edt_name);
        edtPassword = (MaterialEditText)findViewById(R.id.ms_edt_password);
        edtPhone = (MaterialEditText)findViewById(R.id.ms_edt_phone);

        btnSignUp = (Button) findViewById(R.id.ms_fbtn_sign_up);

        // initial firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");
        Log.w("SignUpActivity", "**************" + table_user.getKey());

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //check if user already exists in database
                        if ((dataSnapshot.child(edtPhone.getText().toString()).exists()) && (isNewUser==1)) {
                            mDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "MsUser already exist!", Toast.LENGTH_SHORT).show();

                            // redirect to login page
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        }
                        else
                        {
                            // add new user
                            isNewUser = 0;
                            mDialog.dismiss();
                            MsUser user = new MsUser(edtName.getText().toString(), edtPassword.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this, "Sign up successfully! ", Toast.LENGTH_SHORT).show();
                            // redirect to login page
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            finish();
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
