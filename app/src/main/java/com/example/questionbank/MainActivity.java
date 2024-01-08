package com.example.questionbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.contentcapture.DataShareWriteAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    Button button, button2;
    FirebaseAuth auth;
    String branch=null;
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
    public static DatabaseReference databaseReference;

    private DatabaseReference mDatabase;
    DatabaseReference df = FirebaseDatabase.getInstance().getReference("sem");
    DatabaseReference dref;

    public ArrayList<sem_modal> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        Intent intent = new Intent(MainActivity.this, alldisplaycouses.class);
        tvbranch=findViewById(R.id.branch_display_tx);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        mDatabase =database.getReference("user").child(auth.getUid());

        // Read the value of the "data" child in the database

            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String branch =String.valueOf(dataSnapshot.child("branch").getValue());
                    tvbranch.setText(branch);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    intent.putExtra("branchname", branch);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.println(databaseError);
                }
            });





        Toolbar toolbar = (Toolbar) findViewById(R.id.a);
        setSupportActionBar(toolbar);

//        tvbranch=findViewById(R.id.branch_display_tx);
        nav =(NavigationView) findViewById(R.id.navimumbai);
        drawerLayout=(DrawerLayout) findViewById(R.id.my_drawer_layout);
        Toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();
//
        View headerView = nav.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.navigation_name);
////        navUsername.setText(getIntent().getStringExtra("name"));
//String s = getIntent().getStringExtra("name");
//navUsername.setText(s);
        TextView navUseremail = (TextView) headerView.findViewById(R.id.navigation_email);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String name = user.getDisplayName();
        navUsername.setText(name);
        String email=user.getEmail();

        navUseremail.setText(email);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if(auth.getCurrentUser()!=null) {
            final FirebaseDatabase database11 = FirebaseDatabase.getInstance();
            DatabaseReference myref = database11.getReference("user").child(auth.getUid());
            myref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    account_modal userModel = dataSnapshot.getValue(account_modal.class);
                    assert userModel != null;
                    branch = (userModel.getBranch());
//                    tvbranch.setText(branch);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, "" + databaseError.getCode(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        dref= FirebaseDatabase.getInstance().getReference("user").child(auth.getUid());

       rcv=findViewById(R.id. recyclerview);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter= new RecyclerAdapter(getApplicationContext(),list);
        rcv.addOnItemTouchListener(new RecyclerItemClickListener(
                this, rcv, new RecyclerItemClickListener.OnItemClickListener() {
            String databaseReferencePath;


            @Override
            public void onItemClick(View view, int position) {
               switch (position){
                   case 0: {
                       intent.putExtra("year","year1");

//                       Intent intent = new Intent(MainActivity.this, cd.class);



                       startActivity(intent);

                       break;
                   }
                   case 1 :

                       intent.putExtra("year","year2");



                       startActivity(intent);
                       break;

                   case 2: {
                       intent.putExtra("year","year3");

                       startActivity(intent);
                       break;
                   }





                   default:
               }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }
        ));
        rcv.setHasFixedSize(true);


        list = new ArrayList<>();


clearall();
        Query query =df;
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





        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

          switch (menuitem .getItemId())
          {
              case  R.id.menu_home:
//                  Toast.makeText(MainActivity.this, "home ", Toast.LENGTH_SHORT).show();
                  drawerLayout.closeDrawer(GravityCompat.START);

                  startActivity(new Intent(MainActivity.this,allbranch.class));
                  break;


              case  R.id.menu_call:


                  Intent i = new Intent(Intent.ACTION_SEND);
                  i.setType("message/rfc822");
                  i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"questooteam@gmail.com"});
                  i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                  i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                  try {
                      startActivity(Intent.createChooser(i, "Send mail..."));
                  } catch (android.content.ActivityNotFoundException ex) {
                      Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                  }





                  drawerLayout.closeDrawer(GravityCompat.START);
                  break;

              case  R.id.menu_support:



                  Toast.makeText(MainActivity.this, "setting ", Toast.LENGTH_SHORT).show();
                  drawerLayout.closeDrawer(GravityCompat.START);
                  break;
              case  R.id.menu_logout:
                  auth.signOut();
                  drawerLayout.closeDrawer(GravityCompat.START);
                  auth.signOut();
                  startActivity(new Intent(MainActivity.this,login.class));
                  break;
          }

          return true;
      }
  });









    }

//
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
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


//    @Override
//    public void onnoteclick(int position) {
//        list.get(position);
//        Intent intent= new Intent(MainActivity.this,courses_display.class);
//        intent.putExtra("branch",branch);
//        startActivity(intent);
//    }

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