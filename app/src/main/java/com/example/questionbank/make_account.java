package com.example.questionbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class make_account extends AppCompatActivity {
    AutoCompleteTextView actv;
    String path="user";
    DatabaseReference databaseReference;
    String[] language ={
            "automobile engineering" ,
            "AI and Machine learning" ,
            "Metallurgy engineering" ,
            "Packaging " ,
            "Mechatronics" ,
            "Electronics and telecommunication " ,
            "Minning" ,
            "Civil enginnering" ,
            "Travel and Tourism",
            "Textile",
            "Information Technology",
            "computer science",
            "Electrical engineering",
            "Mechanical engineering"







    };

FirebaseAuth mAuth;
    TextInputLayout name1 ,email1,password1;
    Button btn1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);
        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);
        //Getting the instance of AutoCompleteTextView
        actv= (AutoCompleteTextView)findViewById(R.id.gaurav);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.RED);
        name1=(TextInputLayout) findViewById(R.id.name_account);
        email1=(TextInputLayout) findViewById(R.id.email_account);
        password1=(TextInputLayout) findViewById(R.id.password_account);
        btn1 =findViewById(R.id.account_btn);
//          String name= Objects.requireNonNull(name1.getText()).toString();
//          String email = Objects.requireNonNull(email1.getText()).toString();
//          String password= Objects.requireNonNull(password1.getText()).toString();

mAuth=FirebaseAuth.getInstance();





  String branch = actv.getText().toString();
        databaseReference= FirebaseDatabase.getInstance().getReference(path);
          btn1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  account();
              }
          });
    }

 void account(){
//
//             .getInstance()
//             FirebaseAuth .createUserWithEmailAndPassword(email.trim(),password)
//             .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                 @Override
//                 public void onSuccess(AuthResult authResult) {
//                     UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
//                     FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//                     firebaseUser.updateProfile(userProfileChangeRequest);
//                     account_modal account_modal= new account_modal(FirebaseAuth.getInstance().getUid(),email,password,name,branch);
//                     databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(account_modal);
//                     startActivity(new Intent(make_account.this,MainActivity.class));
//                     finish();
//                 }
//             });

     String name =name1.getEditText().getText().toString();
     String email = email1.getEditText().getText().toString();
     String password = password1.getEditText().getText().toString();
     String branch = actv.getText().toString();

     mAuth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {




                             UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                             FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                             firebaseUser.updateProfile(userProfileChangeRequest);
                             account_modal account_modal = new account_modal(FirebaseAuth.getInstance().getUid(),email,password,name,branch);
                             databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(account_modal);
                             startActivity(new Intent(make_account.this,MainActivity.class));

                             finish();















                     } else {

                     }
                 }
             });
 }
}
