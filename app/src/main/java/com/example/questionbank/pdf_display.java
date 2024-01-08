package com.example.questionbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class pdf_display extends AppCompatActivity {
    FloatingActionButton fb;
    FirebaseAuth auth;

    RecyclerView recview;
    myadapter adapter;
    String email,password;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_display);
        auth = FirebaseAuth.getInstance();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        mDatabase =database.getReference("user").child(auth.getUid());

        // Read the value of the "data" child in the database

//        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                password=String.valueOf(dataSnapshot.child("password").getValue());
//                email =String.valueOf(dataSnapshot.child("email").getValue());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(email);
//                System.out.println(password);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                System.out.println(databaseError);
//            }
//        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String email=user.getEmail();


        String branchname=getIntent().getStringExtra("branchname");
        String year=getIntent().getStringExtra("year");
        String xxx=getIntent().getStringExtra("code");
        System.out.println(xxx);
        System.out.println(xxx);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(xxx);
        System.out.println(xxx);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//            fb = (FloatingActionButton) findViewById(R.id.floatingActionButton);
//            fb.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                 Intent intent = new Intent(pdf_display.this,uploadfile.class);
//                 intent.putExtra("branchname",branchname);
//                 intent.putExtra("year",year);
//                 intent.putExtra("code",xxx);
//
//
//
//                    startActivity(new Intent(getApplicationContext(), uploadfile.class));
//                }
//            });

            recview = (RecyclerView) findViewById(R.id.recview);
//            recview.setLayoutManager(new LinearLayoutManager(this));
        recview.setLayoutManager(new WrapContentLinearLayoutManager(pdf_display.this, LinearLayoutManager.VERTICAL, false));

            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child(branchname).child("upload").child(year).child(xxx), model.class)
                            .build();

            adapter=new myadapter(options);
            recview.setAdapter(adapter);
        Intent intent = new Intent(pdf_display.this,uploadfile.class);
        intent.putExtra("a",branchname);
        intent.putExtra("b",year);
        intent.putExtra("c",xxx);

        fb = (FloatingActionButton) findViewById(R.id.floatingActionButton);




        if (email.equals("varunsukalkar31@gmail.com")) {
            fb.setVisibility(View.VISIBLE);
            fb.setEnabled(true);
        } else {
            fb.setVisibility(View.GONE);
            fb.setEnabled(false);
        }











        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                startActivity(intent);
            }
        });

        }

        @Override
        protected void onStart() {
            super.onStart();
            adapter.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            adapter.stopListening();
        }








}