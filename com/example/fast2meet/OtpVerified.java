package com.example.fast2meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fast2meet.databinding.ActivityOtpVerifiedBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukesh.OnOtpCompletionListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

public class OtpVerified extends AppCompatActivity implements View.OnClickListener {

    ActivityOtpVerifiedBinding binding;
    String phoneNumber;
    FirebaseAuth mAuth;
    String otpid;
    int checked;
    String name, email, password;
    String spmobile;
    String c;
    String data = "1";
    String data2 = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verified);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verified);


        init();
        setListnear();
        generateOtp();


    }

    private void init()
    {
        Intent intent = getIntent();

        phoneNumber = intent.getStringExtra("mobile");

        checked = intent.getIntExtra("checked", 0);

        binding.tvresend.setEnabled(false);

        spmobile = intent.getStringExtra("spmobile");


        startTimer();

        Intent intent1 = getIntent();

        name = intent1.getStringExtra("name");
        email = intent1.getStringExtra("email");
        password = intent1.getStringExtra("password");

        Log.e("ADD SERVICE", "OTP :" + name);
        Log.e("ADD SERVICE", "OTP  :" + email);
        Log.e("ADD SERVICE", "OTP  :" + phoneNumber);
        Log.e("ADD SERVICE", "OTP  :" + password);


        Log.e("RDT", "Checked :" + checked);

        binding.tvnumberotp.setText("+91 " + phoneNumber);

        Intent intent2 = getIntent();
        c = intent2.getStringExtra("check");
        Log.e("OTP Check value", "OTV Chcek value : " + c);


    }

    private void generateOtp()
    {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            signInWithPhoneAuthCredential(phoneAuthCredential);
            // binding.tvOtp.setText(phoneAuthCredential.getSmsCode());
            Log.e("SMS CODE", "SMS CODE :" + phoneAuthCredential.getSmsCode());

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(OtpVerified.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("OTP Test ", " OTP Error :" + e.getMessage());
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            otpid = s;

            Log.e("Sent ", "CODE SENT :" + s);
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("TAG", "signInWithCredential:success");
//
//                            FirebaseUser user = task.getResult().getUser();

//


                            if (c.equals(data)) {

                                Log.e("RDT", "Usert");
                                Intent intent = new Intent(OtpVerified.this, Home.class);
                                intent.putExtra("name", name);
                                intent.putExtra("mobile",phoneNumber);
                                startActivity(intent);
                                finish();
                            }
                            if (c.equals(data2)) {
                                Log.e("RDT", " RDT 2 Service Provicer n");
                                Intent intent = new Intent(OtpVerified.this, AddService.class);

                                intent.putExtra("name", name);
                                intent.putExtra("email", email);
                                intent.putExtra("mobile", phoneNumber);
                                intent.putExtra("password", password);


                                Log.e("PUT", "PUT DATA OTP" + name);
                                Log.e("PUT", "PUT DATA OTP" + email);
                                Log.e("PUT", "PUT DATA OTP" + phoneNumber);
                                Log.e("PUT", "PUT DATA OTP" + password);


                                startActivity(intent);

                                finish();
                            }

                            // ...
                        } else {

                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid

                                Toast.makeText(OtpVerified.this, "Somthing went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void setListnear() {
        binding.imgback.setOnClickListener(this);
        binding.btnOtp.setOnClickListener(this);
        binding.tvresend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.imgback)
        {
            onBackPressed();
        }
        if (id == R.id.btnOtp) {
            if (binding.tvOtp.getText().toString().isEmpty())
            {
                Toast.makeText(OtpVerified.this, "Field Cannot be Empty", Toast.LENGTH_SHORT).show();
            } else
                {
                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otpid, binding.tvOtp.getText().toString());
                signInWithPhoneAuthCredential(phoneAuthCredential);
                binding.progressbar1.setVisibility(View.VISIBLE);
            }
        }

        if (id == R.id.tvresend) {
            generateOtp();
            Toast.makeText(this, "Resend OTP", Toast.LENGTH_SHORT).show();
            startTimer();

        }
    }

    public void startTimer()
    {
        CountDownTimer myCountDownTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
                NumberFormat formatDigit = new DecimalFormat("00");
                binding.tvTimer.setText(formatDigit.format(minutes) + ":" + formatDigit.format(seconds));
            }

            @Override
            public void onFinish() {
                binding.tvTimer.setText("");
                binding.tvresend.setEnabled(true);
            }
        };
        myCountDownTimer.start();
    }
}