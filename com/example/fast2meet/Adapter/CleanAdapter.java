package com.example.fast2meet.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fast2meet.Detailed_Ele;
import com.example.fast2meet.Model.CleanServiceModel;
import com.example.fast2meet.Model.ElectricSeriveModel;
import com.example.fast2meet.OverView_Ele;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ListElectricianWorkBinding;

import java.util.ArrayList;

public class   CleanAdapter  extends RecyclerView.Adapter<CleanAdapter.ViewHolder> {

    ArrayList<CleanServiceModel> cleanServiceModelArrayList;
    ListElectricianWorkBinding binding;
    public  CleanAdapter(ArrayList<CleanServiceModel> cleanServiceModelArrayList)
    {
        this.cleanServiceModelArrayList = cleanServiceModelArrayList;
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

        CleanServiceModel cleanServiceModel = cleanServiceModelArrayList.get(position);


        holder.binding.imgEle1.setImageResource(cleanServiceModel.getImgId());
        holder.binding.tvElectricServiceName.setText(cleanServiceModel.getServiceName());
    }

    @Override
    public int getItemCount() {
        return cleanServiceModelArrayList.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder
    {

        ListElectricianWorkBinding binding;
        public ViewHolder(@NonNull ListElectricianWorkBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;

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
