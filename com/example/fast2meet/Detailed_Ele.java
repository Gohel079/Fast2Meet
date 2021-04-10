package com.example.fast2meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.fast2meet.databinding.ActivityDetailEleBinding;
import com.example.fast2meet.databinding.ActivityDetailedEleBinding;

public class Detailed_Ele extends AppCompatActivity implements View.OnClickListener {


    ActivityDetailedEleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed__ele);
        getSupportActionBar().hide();


        binding = DataBindingUtil.setContentView(Detailed_Ele.this,R.layout.activity_detailed__ele);

        init();


        setListnear();


    }

    private void init()
    {
        Intent intent = getIntent();
        Log.e("DEtailed Scre","Detailed Data "+intent.getStringExtra("sname"));
        Log.e("DEtailed Scre","Detailed Data "+intent.getStringExtra("serviceDSC"));
        binding.tvShopName.setText(intent.getStringExtra("sname"));
        binding.tvDesc.setText("Desciripition : "+intent.getStringExtra("serviceDSC"));
        binding.tvAddress.setText(intent.getStringExtra("address"));
        binding.tvEmail.setText(intent.getStringExtra("beemail"));
        binding.tvCall.setText(intent.getStringExtra("contactNo"));


        binding.tvwebsite.setText(intent.getStringExtra("website"));
        Glide.with(Detailed_Ele.this)
                .load(intent.getStringExtra("Image")).into(binding.imgFullImg);

    }

    private void setListnear()
    {
        binding.btncall.setOnClickListener(this);
        binding.imgback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btncall)
        {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+binding.tvCall.getText().toString()));

            if (ActivityCompat.checkSelfPermission(v.getContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            v.getContext().startActivity(callIntent);
        }
        if (id == R.id.imgback)
        {
            onBackPressed();
        }

    }
}