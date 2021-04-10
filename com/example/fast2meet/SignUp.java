package com.example.fast2meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fast2meet.Model.UserDataModel;
import com.example.fast2meet.databinding.ActivitySigInBinding;
import com.example.fast2meet.databinding.ActivitySignUpBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity implements View.OnClickListener {


    ActivitySignUpBinding binding;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    int checked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);


        setListenar();

        init();


//        binding.rdtgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                if(checkedId == R.id.rdt1)
//                {
//                    Toast.makeText(SignUp.this, "User", Toast.LENGTH_SHORT).show();
//                }
//                if(checkedId == R.id.rdt2)
//                {
//                    binding.progressbar.setVisibility(View.VISIBLE);
//                    Intent intent = new Intent(getApplicationContext(),AddService.class);
//                    startActivity(intent);
//                }
//            }
//        });

    }

    private void init()
    {
        if (AppStatus.getInstance(this).isOnline(this))
        {
            //Toast.makeText(this, "Internet Connection Available", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "No Internet Connection !", Toast.LENGTH_LONG).show();
        }
    }


    private void setListenar() {
        binding.registerbtn.setOnClickListener(this);
        binding.alreadyhaveaccountbtn.setOnClickListener(this);
        //binding.rdtgroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.registerbtn) {
            checked = binding.rdtgroup.getCheckedRadioButtonId();
            // checked = 1;
            Log.e("RDT ", "After Register button click" + checked);

            if (!validateName() | !validateEmail() | !validateNumber() | !validatePassword()) {
                return;
            }

            if (checked == R.id.rdt1)
            {
                signupUser();
            }

            if (checked == R.id.rdt2)
            {
                signupServiceProvider();
            }

        }
        if (id == R.id.alreadyhaveaccountbtn) {
            binding.progressbar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
            binding.progressbar.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, SigIn.class);
            startActivity(intent);

            binding.progressbar.setVisibility(View.VISIBLE);

        }


    }

    private void signupServiceProvider() {

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("Service Provider");
        binding.progressbar.setVisibility(View.VISIBLE);

        String name = binding.fullname.getEditText().getText().toString();
        String email = binding.email.getEditText().getText().toString();
        String phoneNumber = binding.phoneNumer.getEditText().getText().toString();
        String password = binding.password.getEditText().getText().toString();

//                UserDataModel userDataModel = new UserDataModel(name,email,phoneNumber,password);
//
//                reference.child(phoneNumber).setValue(userDataModel);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Service Provider");

        Query checkSp = databaseReference.orderByChild("phoneNumber").equalTo(phoneNumber);

        checkSp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Toast.makeText(SignUp.this, "Sorry ! Already Registered", Toast.LENGTH_SHORT).show();
                    binding.progressbar.setVisibility(View.INVISIBLE);
                } else
                    {

                    SharedPreferences sharedPref = getSharedPreferences("MyKey", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("value1", binding.etNumber.getText().toString());
                    editor.apply();

                    Toast.makeText(SignUp.this, "SignUp SuccessFully added ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), OtpVerified.class);

                    Log.e("ADD SERVICE", "SIGN Up:" + name);
                    Log.e("ADD SERVICE", "SIGN Up :" + email);
                    Log.e("ADD SERVICE", "SIGN Up :" + phoneNumber);
                    Log.e("ADD SERVICE", "SIGN Up :" + password);

                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("mobile", phoneNumber);
                    intent.putExtra("password", password);

                    intent.putExtra("checked", binding.rdtgroup.getCheckedRadioButtonId());
                    intent.putExtra("check", "" + 2);
                    startActivity(intent);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void signupUser() {
        Log.e("CHECKED", "1");
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("User");

         binding.progressbar.setVisibility(View.VISIBLE);

        String name = binding.fullname.getEditText().getText().toString();
        String email = binding.email.getEditText().getText().toString();
        String phoneNumber = binding.phoneNumer.getEditText().getText().toString();
        String password = binding.password.getEditText().getText().toString();

        Query checkSp = reference.orderByChild("phoneNumber").equalTo(phoneNumber);
        checkSp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    Toast.makeText(SignUp.this, "Sorry ! Already Registered", Toast.LENGTH_LONG).show();
                    binding.progressbar.setVisibility(View.INVISIBLE);
                } else {
                    UserDataModel userDataModel = new UserDataModel(name, email, phoneNumber, password);
                    reference.child(phoneNumber).setValue(userDataModel);

                    Toast.makeText(SignUp.this, "SIGN UP SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUp.this, OtpVerified.class);

                    intent.putExtra("name", name);
                    intent.putExtra("check", "" + 1);
                    intent.putExtra("email", email);
                    intent.putExtra("mobile", phoneNumber.trim());
                    intent.putExtra("password", password);

                    intent.putExtra("checked", binding.rdtgroup.getCheckedRadioButtonId());
                    SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("value", phoneNumber);
                    editor.apply();

                    Log.e("mhv", " Hello :" + phoneNumber);
                   // Toast.makeText(SignUp.this, "p :" + phoneNumber, Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                    binding.progressbar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private Boolean validateName() {
        String val = binding.etfname.getText().toString();

        if (val.isEmpty()) {
            binding.fullname.setError("Fields cannot be Empty");
            return false;
        } else {
            binding.fullname.setError(null);
            binding.fullname.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateEmail() {
        String val = binding.email.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            binding.email.setError("Field cannot be Empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            binding.email.setError("Invalid Email Address");
            return false;
        } else {

            binding.email.setError(null);
            binding.email.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateNumber() {
        String val = binding.phoneNumer.getEditText().getText().toString();

        if (val.isEmpty()) {
            binding.phoneNumer.setError("Field Cannot be Empty");
            return false;
        } else if (val.length() < 10 | val.length() > 10) {
            binding.phoneNumer.setError("Please Enter 10 Digit Number ");
            return false;
        } else {
            binding.phoneNumer.setError(null);
            binding.phoneNumer.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword() {
        String val = binding.password.getEditText().getText().toString();

        if (val.isEmpty()) {
            binding.password.setError("Field Cannot be Empty");
            return false;
        } else if (val.length() < 4) {
            binding.password.setError("Password is too Short (4 Digits/Text)");
            return false;
        } else {
            binding.password.setError(null);
            binding.password.setErrorEnabled(false);
            return true;
        }

    }

}