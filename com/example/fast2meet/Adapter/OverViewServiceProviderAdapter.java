package com.example.fast2meet.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fast2meet.Detailed_Ele;
import com.example.fast2meet.Model.OverviewServiceProvider;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ListOverviewServiceProviderBinding;

import java.util.ArrayList;

public class OverViewServiceProviderAdapter extends RecyclerView.Adapter<OverViewServiceProviderAdapter.ViewHolder> {


    ArrayList<OverviewServiceProvider> overviewServiceProviders;
    ListOverviewServiceProviderBinding binding;

    public OverViewServiceProviderAdapter(ArrayList<OverviewServiceProvider> overViewServiceProviderArrayList)
    {
        this.overviewServiceProviders = overViewServiceProviderArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_overview_service_provider,parent,false);

        ViewHolder viewHolder = new ViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        OverviewServiceProvider overviewServiceProvider = overviewServiceProviders.get(position);

        holder.binding.imgShop.setImageResource(overviewServiceProvider.getImageView());
        holder.binding.tvStorename.setText(overviewServiceProvider.getShopName());
        holder.binding.tvDsc.setText(overviewServiceProvider.getServiceDec());



    }

    @Override
    public int getItemCount() {
        return overviewServiceProviders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        ListOverviewServiceProviderBinding binding;

        public ViewHolder(@NonNull ListOverviewServiceProviderBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

            itemView.getRoot().setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Detailed_Ele.class);
                    v.getContext().startActivity(intent);
                }
            });


        }
    }
}
