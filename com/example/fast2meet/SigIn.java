package com.example.fast2meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fast2meet.databinding.ActivitySigInBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SigIn extends AppCompatActivity implements View.OnClickListener {

    ActivitySigInBinding binding;
    private SharedPreferanceConfig sharedPreferanceConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_in);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sig_in);

        setListnear();

        init();
    }

    private void init()
    {

        sharedPreferanceConfig = new  SharedPreferanceConfig(SigIn.this);

        Dexter.withContext(SigIn.this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA, Manifest.permission.INTERNET)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {


                        permissionToken.continuePermissionRequest();
                    }
                }).check();

        if(sharedPreferanceConfig.read_login_status())
        {
            startActivity(new Intent(this,Home.class));
        }
        else if(sharedPreferanceConfig.sp_read_login_status())
        {
            startActivity(new Intent(this,AddServiceDetailed.class));
            Log.e("TAG","READ SP LOGIN STATUS ");
        }



        if (AppStatus.getInstance(this).isOnline(this))
        {
            //Toast.makeText(this, "Internet Connection Available", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "No Internet Connection !", Toast.LENGTH_LONG).show();
        }
    }

    private void setListnear() {
        binding.loginbtn.setOnClickListener(this);
        binding.btnNewUSer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.loginbtn)
        {

            int checked = binding.rdtGroup.getCheckedRadioButtonId();

            if (!validateNumber() | !validatePassword()) {
                //binding.progressbar1.setVisibility(View.INVISIBLE);
                return;
            }
            if (checked == R.id.rdt1)
            {
                isUser();

            }
            if (checked == R.id.rdt2)
            {

                isServiceProvider();
            }
        }
        if (id == R.id.btnNewUSer) {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            binding.progressbar1.setVisibility(View.VISIBLE);
        }
    }

    private void isUser() {

        binding.progressbar1.setVisibility(View.VISIBLE);
        final String userEnteredphone = binding.etnumber.getEditText().getText().toString();
        final String userEnteredpassword = binding.etpassword.getEditText().getText().toString();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");

        Query checkuser = databaseReference.orderByChild("phoneNumber").equalTo(userEnteredphone);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    binding.etnumber.setError(null);
                    binding.etnumber.setErrorEnabled(false);


                    Toast.makeText(getApplicationContext(), "SIGN IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    String passwordFromDatabase = snapshot.child(userEnteredphone).child("password").getValue(String.class);


                    if (passwordFromDatabase.equals(userEnteredpassword)) {
                        binding.etnumber.setError(null);
                        binding.etnumber.setErrorEnabled(false);

                        //Toast.makeText(getApplicationContext(), "SIGN SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("value", binding.etnumber.getEditText().getText().toString());
                        editor.apply();

                        sharedPreferanceConfig.login_status(true);
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);

                    }
                    else
                        {
                            binding.progressbar1.setVisibility(View.INVISIBLE);
                        binding.etpassword.setError("Wrong Password");
                        binding.etpassword.requestFocus();
                    }
                } else {
                    binding.progressbar1.setVisibility(View.INVISIBLE);
                    binding.etnumber.setError("No Such User Exist");
                    binding.etnumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void isServiceProvider()
    {

        binding.progressbar1.setVisibility(View.VISIBLE);
        final String spEnteredNumber = binding.etnumber.getEditText().getText().toString();
        final String spEnteredPassword = binding.etpassword.getEditText().getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Service Provider");

        Query checkSp = databaseReference.orderByChild("phoneNumber").equalTo(spEnteredNumber);

        checkSp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.exists()) {
                    binding.etnumber.setError(null);
                    binding.etnumber.setErrorEnabled(false);

                    Toast.makeText(SigIn.this, "SIGN SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                    String passwordFromDatabase = snapshot.child(spEnteredNumber).child("password").getValue().toString();

                    if (passwordFromDatabase.equals(spEnteredPassword)) {
                        binding.etnumber.setError(null);
                        binding.etnumber.setErrorEnabled(false);

                        //Toast.makeText(getApplicationContext(),"SIGN SUCCESSFULLY",Toast.LENGTH_SHORT).show();



                        SharedPreferences sharedPref = getSharedPreferences("MyKey", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("value1", binding.etnumber.getEditText().getText().toString());
                        editor.apply();


                        sharedPreferanceConfig.sp_login_status(true);
                        Log.e("TAG","SHREPRE LOGIN STATUS :"+sharedPreferanceConfig.sp_read_login_status() );

                        Toast.makeText(SigIn.this, "Login Serivce Provider", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SigIn.this, AddServiceDetailed.class);
                        intent.putExtra("mobile",binding.etnumber.getEditText().getText().toString());
                        startActivity(intent);
                        finish();




                    } else {
                        binding.etpassword.setError("Wrong Password");
                        binding.etpassword.requestFocus();
                    }
                } else {
                    binding.etnumber.setError("No Such User Exist");
                    binding.etnumber.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private Boolean validateNumber() {
        String val = binding.etnumber.getEditText().getText().toString();

        if (val.isEmpty())
        {
            binding.etnumber.setError("Field Cannot be Empty");
            return false;
        }
        else if (val.length() < 10 | val.length() > 10)
        {
            binding.etnumber.setError("Please Enter 10 Digit Number");
            return false;

        } else
            {
            binding.etnumber.setError(null);
            binding.etnumber.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword() {
        String val = binding.etpassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            binding.etpassword.setError("Field Cannot be Empty");
            return false;
        } else if (val.length() < 4) {
            binding.etpassword.setError("Password is too Short (4 Digits/Text)");
            return false;
        } else {
            binding.etpassword.setError(null);
            binding.etpassword.setErrorEnabled(false);
            return true;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}