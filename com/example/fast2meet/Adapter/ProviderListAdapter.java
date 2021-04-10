package com.example.fast2meet.Adapter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fast2meet.Detailed_Ele;
import com.example.fast2meet.MainActivity;
import com.example.fast2meet.Model.ProviderListModel;
import com.example.fast2meet.OverView_Ele;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ListOverviewServiceProviderBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProviderListAdapter  extends FirebaseRecyclerAdapter<ProviderListModel,ProviderListAdapter.ViewHolder> {


    ListOverviewServiceProviderBinding binding;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProviderListAdapter(@NonNull FirebaseRecyclerOptions<ProviderListModel> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ProviderListModel model)
    {

        Log.e("sName","sName :"+model.getsname());
        holder.binding.tvStorename.setText(model.getsname());
        holder.binding.tvDsc.setText(model.getServiceDSC());
        holder.binding.tvServiceCate.setText(model.getServiceName());

        Log.e("Image","RV Image :"+model.getImage());
        Log.e("DSC","DSC :"+model.getServiceDSC());

        Glide.with(holder.binding.imgShop.getContext()).load(model.getImage()).into(holder.binding.imgShop);

        holder.binding.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(v.getContext(), Detailed_Ele.class);
                intent.putExtra("sname",model.getsname());
                intent.putExtra("contactNo",model.getContactNo());
                intent.putExtra("serviceDSC",model.getServiceDSC());
                intent.putExtra("website",model.getWebsite());
                intent.putExtra("beemail",model.getBemail());
                intent.putExtra("address",model.getAddress());
                intent.putExtra("Image",model.getImage());

                v.getContext().startActivity(intent);

            }
        });
        holder.binding.btnCallNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+model.getContactNo()));

                if (ActivityCompat.checkSelfPermission(v.getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                v.getContext().startActivity(callIntent);
            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
               R.layout.list_overview_service_provider,parent,false);


       return new ViewHolder(binding);
    }

    class  ViewHolder extends RecyclerView.ViewHolder
    {
        ListOverviewServiceProviderBinding binding;

        public ViewHolder(@NonNull ListOverviewServiceProviderBinding itemView)
        {
            super(itemView.getRoot());
            this.binding=itemView;
        }
    }
}
