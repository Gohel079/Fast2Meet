package com.example.fast2meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import com.example.fast2meet.Adapter.OverViewServiceProviderAdapter;
import com.example.fast2meet.Adapter.ProviderListAdapter;
import com.example.fast2meet.Model.OverviewServiceProvider;
import com.example.fast2meet.Model.ProviderListModel;
import com.example.fast2meet.databinding.ActivityOverViewEleBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class OverView_Ele extends AppCompatActivity implements View.OnClickListener {

    ActivityOverViewEleBinding binding;

    ProviderListAdapter providerListAdapter;
    ArrayList<OverviewServiceProvider>  overviewServiceProviderArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_view__ele);
        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_over_view__ele);

        setListnear();
        init();


    }

    private void init()
    {
        overviewServiceProviderArrayList = new ArrayList<>();

        overviewServiceProviderArrayList.add(new OverviewServiceProvider(R.drawable.electrican,
                "Shipher Electro","All Types of Work"));

        Intent intent = getIntent();
        String sname = intent.getStringExtra("serviceName");

        Log.e("OverView Snmae","Servicename :"+sname);

        Query  query = FirebaseDatabase.getInstance().getReference().child("Service Provider")
                .orderByChild("serviceName").equalTo(sname);



        // FireBase RecycerView

        FirebaseRecyclerOptions<ProviderListModel> options =
                new FirebaseRecyclerOptions.Builder<ProviderListModel>()
                        .setQuery(query, ProviderListModel.class)
                        .build();


        providerListAdapter =  new ProviderListAdapter(options);
        binding.rvOverview.setAdapter(providerListAdapter);
        //OverViewServiceProviderAdapter overViewServiceProviderAdapter = new OverViewServiceProviderAdapter(overviewServiceProviderArrayList);

    }

    private void setListnear()
    {
        binding.imgback.setOnClickListener(this);
        //binding.cardOverview1.setOnClickListener(this);
        //binding.cardOverview2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        if(id == R.id.imgback)
        {
            onBackPressed();
        }
//        if(id == R.id.cardOverview1)
//        {
//            Intent intent = new Intent(this,Detailed_Ele.class);
//            startActivity(intent);
//        }
//        if(id == R.id.cardOverview2)
//        {
//            Intent intent = new Intent(this,Detailed_Ele.class);
//            startActivity(intent);
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        providerListAdapter.startListening();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        providerListAdapter.stopListening();
//    }
}