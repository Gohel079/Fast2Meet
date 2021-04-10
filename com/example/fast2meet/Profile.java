package com.example.fast2meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fast2meet.Model.UserDataModel;
import com.example.fast2meet.databinding.ActivityProfileBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    ActivityProfileBinding binding;
    String phoneNumber;
    String mobileNo;
    private  SharedPreferanceConfig  sharedPreferanceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);


        init();
        setListnear();
        getProfileData();
        //getServiceProviderData();


    }

    private void init()
    {
        sharedPreferanceConfig =  new SharedPreferanceConfig(getApplicationContext());

        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("mobile");

//        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        String s1 = sh.getString("name", "Data Not Found");

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        mobileNo = sharedPreferences.getString("value","");

        Log.e("TAG PROGFILE","PROFILE DATA : "+mobileNo);

    }

    private void setListnear() {
        binding.imgback.setOnClickListener(this);
        binding.imglogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.imgback)
        {
            onBackPressed();
        }
        if(id == R.id.imglogout)
        {
            Toast.makeText(this, "Logout...", Toast.LENGTH_SHORT).show();
            sharedPreferanceConfig.login_status(false);
            new AlertDialog.Builder(this)
                    .setMessage("Are  you  Sure you  Want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Profile.this,SigIn.class));

                            finish();
                        }
                    })
                    .setNegativeButton("No",null).show();
           // startActivity(new Intent(Profile.this,SigIn.class));
        }

    }



    private  void  getProfileData()
    {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("User");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

               String name = snapshot.child(mobileNo).child("name").getValue(String.class);
               String email = snapshot.child(mobileNo).child("email").getValue(String.class);

                Log.e("Profile name","P N :"+name);
                Log.e("Profile name","P N :"+email);
                Log.e("SNAMPSHOOT","snapshoot"+snapshot);


                binding.tvName.setText(name);
                binding.tvprofileemail.setText(email);
                binding.tvprofilemobile.setText(mobileNo);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}