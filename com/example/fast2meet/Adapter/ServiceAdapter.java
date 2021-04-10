package com.example.fast2meet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fast2meet.AddService;
import com.example.fast2meet.Model.ServiceListModel;
import com.example.fast2meet.OverView_Ele;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ItemServiceListBinding;

public class ServiceAdapter  extends RecyclerView.Adapter<ServiceAdapter.ViewHolder>
{


    private ServiceListModel[] serviceList;
    ItemServiceListBinding binding;

    public  Context context;

    public ServiceAdapter(ServiceListModel[] serviceListModels)
    {
        this.serviceList=serviceListModels;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        binding  = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_service_list,parent,false);

        //context = parent.getContext();

        ViewHolder viewHolder = new ViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
            ServiceListModel serviceListModel =  serviceList[position];

            holder.binding.imgService.setImageResource(serviceListModel.serviceImage);
            holder.binding.tvServiceName.setText(""+serviceListModel.getServiceName());



    }

    @Override
    public int getItemCount() {
        return serviceList.length;
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder
    {

        ItemServiceListBinding binding;

        public ViewHolder(@NonNull ItemServiceListBinding itemView)
        {
            super(itemView.getRoot());
            this.binding=itemView;


            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    context = v.getContext();

                    Log.e("TAG","Hello :"+getAdapterPosition());
                    switch (getAdapterPosition())
                    {
                        case 0:
                            Intent intent =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent);
                            break;
                        case 1:
                            Intent intent1 =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent1);
                            break;
                        case 2:
                            Intent intent2 =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent2);
                            break;
                        case 3:
                            Intent intent3 =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent3);
                            break;
                        case 4:
                            Intent intent4 =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent4);
                            break;
                        case 5:
                            Intent intent5 =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent5);
                            break;
                        case 6:
                            Intent intent6 =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent6);
                            break;
                        case 7:
                            Intent intent7 =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent7);
                            break;
                        case 8:
                            Intent intent8 =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent8);
                            break;
                        case 9:
                            Intent intent9 =new Intent(v.getContext(), OverView_Ele.class);
                            context.startActivity(intent9);
                            break;
                        case 10:
                            Intent intent10 =new Intent(v.getContext(), AddService.class);
                            context.startActivity(intent10);
                            break;
                        case 11:
                            Intent intent11 = new Intent(v.getContext(), AddService.class);
                            context.startActivity(intent11);
                            break;

                        default:
                            throw new IllegalStateException("Unexpected value :"+getAdapterPosition());
                    }



                }
            });

        }

    }
}
