package com.example.fast2meet.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fast2meet.Model.ElectricSeriveModel;
import com.example.fast2meet.Model.PainterServiceModel;
import com.example.fast2meet.OverView_Ele;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ListElectricianWorkBinding;

import java.util.ArrayList;

public class PainterServideAdapter extends RecyclerView.Adapter<PainterServideAdapter.ViewHolder> {

    ArrayList<PainterServiceModel> painterServiceModelArrayList;
    ListElectricianWorkBinding binding;
    public  PainterServideAdapter(ArrayList<PainterServiceModel> painterServiceModelArrayList)
    {
        this.painterServiceModelArrayList = painterServiceModelArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.list_electrician_work,parent,false);

        ViewHolder viewHolder = new ViewHolder(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        PainterServiceModel painterServiceModel = painterServiceModelArrayList.get(position);

        holder.binding.imgEle1.setImageResource(painterServiceModel.getImgId());
        holder.binding.tvElectricServiceName.setText(painterServiceModel.getServiceName());

    }

    @Override
    public int getItemCount() {
        return painterServiceModelArrayList.size();
    }


    public class  ViewHolder extends  RecyclerView.ViewHolder
    {

        ListElectricianWorkBinding binding;

        public ViewHolder(@NonNull ListElectricianWorkBinding itemView)
        {
            super(itemView.getRoot());
            this.binding=itemView;

            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), OverView_Ele.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
