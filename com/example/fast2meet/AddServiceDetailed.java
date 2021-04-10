package com.example.fast2meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fast2meet.databinding.ActivityAddServiceDetailedBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddServiceDetailed extends AppCompatActivity implements View.OnClickListener {

    ActivityAddServiceDetailedBinding binding;
    DatabaseReference databaseReference;

    String mobile,sname,contact,bemail,website,serviceName,serviceDSC,address,uri;

    SharedPreferanceConfig sharedPreferanceConfig;
    String mobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_detailed);

        getSupportActionBar().hide();

        init();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_service_detailed);

        getData();
        setListnear();

    }

    private void init()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MyKey", MODE_PRIVATE);
        mobileNo = sharedPreferences.getString("value1","");

        Log.e("TAG","ADD SERVICE DETAILED MOBILE NO :  "+mobileNo);

        sharedPreferanceConfig = new SharedPreferanceConfig(getApplicationContext());

        Intent intent = getIntent();

        //Log.e("ADD DETAILESD PhoneNu",intent.getStringExtra("mobile"));
//        Log.e("ADD DETAILESD Name ",intent.getStringExtra("sname"));
//        Log.e("ADD DETAILESD Contacr",intent.getStringExtra("contactNo"));
//        Log.e("ADD DETAILESD bemail",intent.getStringExtra("bemail"));
//        Log.e("ADD DETAILESD website",intent.getStringExtra("website"));
//        Log.e("ADD DETAILESD Servic",intent.getStringExtra("serviceName"));
//        Log.e("ADD DETAILESD serviced",intent.getStringExtra("serviceDSC"));
//        Log.e("ADD DETAILESD address",intent.getStringExtra("address"));
//        Log.e("ADD DETAILESD uri",intent.getStringExtra("Uri"));


//        mobile = intent.getStringExtra("mobile");
//        Log.e("TAG","ADD SERVICE DETAILES :"+mobile);

//        sname = intent.getStringExtra("sname");
//        contact = intent.getStringExtra("contactNo");
//        bemail = intent.getStringExtra("bemail");
//        website = intent.getStringExtra("website");
//        serviceName = intent.getStringExtra("serviceName");
//        serviceDSC = intent.getStringExtra("serviceDSC");
//        address = intent.getStringExtra("address");
//        uri = intent.getStringExtra("Uri");

        //Glide.with(AddServiceDetailed.this).load(intent.getStringExtra("Uri")).into(binding.imgFullImg);

//        binding.tvShopName.setText(""+intent.getStringExtra("sname"));
//        binding.tvAddress.setText(""+intent.getStringExtra("address"));
//        binding.tvwebsite.setText(""+intent.getStringExtra("website"));
//        binding.tvEmail.setText(""+intent.getStringExtra("bemail"));
//        binding.tvCall.setText(""+intent.getStringExtra("contactNo"));
//        binding.tvDesc.setText("Descripition : "+intent.getStringExtra("serviceDSC"));




        databaseReference = FirebaseDatabase.getInstance().getReference("Service Provider");
        // databaseReference.child("mobileNo");

        if (AppStatus.getInstance(this).isOnline(this))
        {
            //Toast.makeText(this, "Internet Connection Available", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "No Internet Connection !", Toast.LENGTH_LONG).show();
        }

    }

    private void setListnear()
    {
        binding.btnupdate.setOnClickListener(this);
        binding.imglogout.setOnClickListener(this);
    }


    private void getData()
    {
        databaseReference.addListenerForSingleValueEvent(  new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                     sname = snapshot.child(mobileNo).child("sname").getValue(String.class);
                    bemail = snapshot.child(mobileNo).child("bemail").getValue(String.class);
                    address  = snapshot.child(mobileNo).child("address").getValue(String.class);
                    website = snapshot.child(mobileNo).child("website").getValue(String.class);
                    serviceDSC = snapshot.child(mobileNo).child("serviceDSC").getValue(String.class);
                     contact = snapshot.child(mobileNo).child("contactNo").getValue(String.class);
                    uri   = snapshot.child(mobileNo).child("image").getValue(String.class);
                    //etc

                    Log.e("TAG","ADD SERVCICE DETIALEDD :"+sname);
                    Log.e("TAG","ADD SERVICE DEAILES "+bemail);



                    binding.tvShopName.setText(sname);
                    binding.tvEmail.setText(bemail);
                    binding.tvAddress.setText(address);
                    binding.tvDesc.setText(serviceDSC);
                    binding.tvCall.setText(contact);
                    binding.tvwebsite.setText(website);

                    Glide.with(getApplicationContext()).load(uri).into(binding.imgFullImg);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        if(id == R.id.btnupdate)
        {
            Intent intent = new Intent(AddServiceDetailed.this,EditService.class);

            intent.putExtra("mobile",mobileNo);
            intent.putExtra("sname",sname);
            intent.putExtra("contact",contact);
            intent.putExtra("bemail",bemail);
            intent.putExtra("website",website);
            intent.putExtra("serviceName",serviceName);
            intent.putExtra("serviceDSC",serviceDSC);
            intent.putExtra("address",address);
            intent.putExtra("Uri",uri);

            startActivity(intent);
        }

        if(id == R.id.imglogout)
        {
            Toast.makeText(this, "Logout...", Toast.LENGTH_SHORT).show();
            sharedPreferanceConfig.sp_login_status(false);
            new AlertDialog.Builder(this)
                    .setMessage("Are  you  Sure you  Want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(AddServiceDetailed.this,SigIn.class));

                            finish();
                        }
                    })
                    .setNegativeButton("No",null).show();
        }


    }
}