package com.example.fast2meet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fast2meet.Adapter.CategoryListAdapter;
import com.example.fast2meet.Adapter.RecommnedServiceAdapter;
import com.example.fast2meet.Adapter.TopServiceAdapter;
import com.example.fast2meet.Model.CategoryModel;
import com.example.fast2meet.Model.RecommendServiceModel;
import com.example.fast2meet.Model.TopServiceModel;
import com.example.fast2meet.databinding.ActivityHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Home extends AppCompatActivity {


    ActivityHomeBinding binding;
    private  SharedPreferanceConfig  sharedPreferanceConfig;
    ProgressDialog progressDialog;
    //String[] city = {"Select City", "Ahmedabad", "Surat", "Vadadora", "Gandhinagar", "Rajkot"};

    ArrayList<RecommendServiceModel> recommendServiceModelArrayList;
    ArrayList<TopServiceModel> topServiceModelArrayList;
    String phoneNumber;

    CategoryListAdapter categoryListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        sharedPreferanceConfig =  new SharedPreferanceConfig(getApplicationContext());

        init();
        //setSpinnerAdapter();

        //progressDialog.dismiss();


    }


//    private void setSpinnerAdapter() {
//        ArrayAdapter<String> splocation = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, city);
//       // binding.splocation.setAdapter(splocation);
//    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }
    private void init() {

        if (AppStatus.getInstance(this).isOnline(this))
        {
            //Toast.makeText(this, "Internet Connection Available", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "No Internet Connection !", Toast.LENGTH_LONG).show();
        }


        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("mobile");
        Log.e("Home","Home Page Number"+phoneNumber);

//        recommendServiceModelArrayList = new ArrayList<>();
        topServiceModelArrayList = new ArrayList<>();
//
//        recommendServiceModelArrayList.add(new RecommendServiceModel(R.drawable.photomen, "Photographer"));
//        recommendServiceModelArrayList.add(new RecommendServiceModel(R.drawable.cardrenovation, "Rennovation"));
//        recommendServiceModelArrayList.add(new RecommendServiceModel(R.drawable.cardtailor, "Tailor"));
//        recommendServiceModelArrayList.add(new RecommendServiceModel(R.drawable.cardplumber, "Plumber"));
//        recommendServiceModelArrayList.add(new RecommendServiceModel(R.drawable.electrican, "Electrician"));


//        RecommnedServiceAdapter recommnedServiceAdapter = new RecommnedServiceAdapter(recommendServiceModelArrayList);
//        binding.rvRecommnedService.setAdapter(recommnedServiceAdapter);


        topServiceModelArrayList.add(new TopServiceModel(R.drawable.electrican));
        topServiceModelArrayList.add(new TopServiceModel(R.drawable.carpenter));
        topServiceModelArrayList.add(new TopServiceModel(R.drawable.painter));
        topServiceModelArrayList.add(new TopServiceModel(R.drawable.slidersolar));
        topServiceModelArrayList.add(new TopServiceModel(R.drawable.photomen));


        TopServiceAdapter topServiceAdapter = new TopServiceAdapter(topServiceModelArrayList);
        binding.rvTopService.setAdapter(topServiceAdapter);



        binding.bottomNavigation.setSelectedItemId(R.id.Homenavi);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.booking :
//
//                        Intent intent = new Intent(getApplicationContext(),AddService.class);
//                        startActivity(intent);
//                        break;

                    case R.id.profile:
                        Intent intent1 = new Intent(getApplicationContext(), Profile.class);
                        intent1.putExtra("mobile",phoneNumber);
                        startActivity(intent1);
                        break;

//                    default:
//                        return false;
                }


                return true;
            }
        });



        try {
            FirebaseRecyclerOptions<CategoryModel> options =
                    new FirebaseRecyclerOptions.Builder<CategoryModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Category"), CategoryModel.class)
                            .build();

            categoryListAdapter = new CategoryListAdapter(options);
            binding.rvServiceList.setAdapter(categoryListAdapter);
        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();

//        if(categoryListAdapter != null)
//        {
            categoryListAdapter.startListening();
//        }

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        if(categoryListAdapter != null)
//        {
//            categoryListAdapter.stopListening();
//        }
//        //categoryListAdapter.stopListening();
//    }

}