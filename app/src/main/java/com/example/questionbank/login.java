package com.example.questionbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class login extends AppCompatActivity {
    Button LOGIN_BUTTON;
TextInputLayout ed1, ed2,namelo;
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
ed1=(TextInputLayout) findViewById(R.id.Email_login);
ed2 = (TextInputLayout) findViewById(R.id.password_login);

LOGIN_BUTTON =(Button) findViewById(R.id.LOGIN_BUTTON);
        mAuth = FirebaseAuth.getInstance();
      LOGIN_BUTTON.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              signup();
          }
      });
    }

    public void create_account(View view) {
        startActivity(new Intent(login.this,make_account.class));
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            startActivity(new Intent(login.this,MainActivity.class));
//        }
//    }
@Override
protected void onStart() {
    super.onStart();
    if(FirebaseAuth.getInstance().getCurrentUser()!=null){
        startActivity(new Intent(login.this,MainActivity.class));
        finish();
    }
}

    void signup(){
        String email = ed1.getEditText().getText().toString();
        String password = ed2.getEditText().getText().toString();













        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

//                            startActivity(new Intent(login.this,MainActivity.class));
                            Intent intent = new Intent(login.this,MainActivity.class);
                              startActivity(intent);

                        } else {
                            Toast.makeText(login.this, "Login failed ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void help(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"questooteam@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(login.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}