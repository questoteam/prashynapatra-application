package com.example.questionbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class alldisplaycouses extends AppCompatActivity {
    Button button, button2;
    FirebaseAuth auth;
    String branch = null;
    TextView tvbranch;
    String Sem_No;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle Toggle;
    NavigationView nav;
    RecyclerView rcv;
    TextView tv;
    Context mcontext;
    private RecyclerAdapter recyclerAdapter;
    FirebaseDatabase firebaseDatabase;
    //
    public static DatabaseReference varun;

    private DatabaseReference mDatabase;
    DatabaseReference dref;

    public ArrayList<sem_modal> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alldisplaycouses);
        Intent intent = new Intent(alldisplaycouses.this,pdf_display.class);
        String branchname = getIntent().getStringExtra("branchname");
        String year =getIntent().getStringExtra("year");
        auth = FirebaseAuth.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        varun = database.getReference(branchname).child("year").child(year);
        List<String> childIds = new ArrayList<>();


        DatabaseReference yearRef = FirebaseDatabase.getInstance().getReference(branchname).child("upload").child(year);

        yearRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String childId = childSnapshot.getKey();
                    childIds.add(childId);
                }

                String child1Id = childIds.get(0);
                String child2Id = childIds.get(1);
                // Add more variables for each child ID as needed

                // Use the child ID variables as needed
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.a);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        dref = FirebaseDatabase.getInstance().getReference("user").child(auth.getUid());

        rcv = findViewById(R.id.recyclerview);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), list);
        rcv.addOnItemTouchListener(new RecyclerItemClickListener(
                this, rcv, new RecyclerItemClickListener.OnItemClickListener() {
            String databaseReferencePath;


            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0: {

//                       Intent intent = new Intent(MainActivity.this, cd.class);
                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);
                        intent.putExtra("code",childIds.get(0));

                        startActivity(intent);
                        break;
                    }
                    case 1:

                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);

                             intent.putExtra("code",childIds.get(1));

                        startActivity(intent);
                        break;

                    case 2: {
                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);
                         intent.putExtra("code",childIds.get(2));


                        startActivity(intent);
                        break;
                    }
                    case 3: {
                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);
                        intent.putExtra("code",childIds.get(3));


                        startActivity(intent);
                        break;
                    }
                    case 4: {
                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);
                        intent.putExtra("code",childIds.get(4));


                        startActivity(intent);
                        break;
                    }
                    case 5: {
                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);
                        intent.putExtra("code",childIds.get(5));


                        startActivity(intent);
                        break;
                    }
                    case 6: {
                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);
                        intent.putExtra("code",childIds.get(6));


                        startActivity(intent);
                        break;
                    }
                    case 7: {
                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);
                        intent.putExtra("code",childIds.get(7));


                        startActivity(intent);
                        break;
                    }
                    case 8: {
                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);
                        intent.putExtra("code",childIds.get(8));


                        startActivity(intent);
                        break;
                    }
                    case 9: {
                        intent.putExtra("branchname", branchname);
                        intent.putExtra("year",year);
                        intent.putExtra("code",childIds.get(9));


                        startActivity(intent);
                        break;
                    }

                    default: {

                    }

                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        rcv.setHasFixedSize(true);


        list = new ArrayList<>();


        clearall();
        Query query =varun;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clearall();
                for(DataSnapshot snapshot11 :snapshot.getChildren()){
                    sem_modal modal = new sem_modal();
                    modal.setImageurl(Objects.requireNonNull(snapshot11.child("imageurl").getValue()).toString());
                    modal.setSemno(Objects.requireNonNull(snapshot11.child("semno").getValue()).toString());
                    list.add(modal);
                }

                recyclerAdapter= new RecyclerAdapter(getApplicationContext(),list);

                rcv.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            //

        });



    }






    private void clearall(){
        if(list !=null){
            list.clear();

            if(recyclerAdapter !=null){
                recyclerAdapter.notifyDataSetChanged();
            }






        }
        list =new ArrayList<>();
    }


    public void onDestroy() {

        super.onDestroy();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}




