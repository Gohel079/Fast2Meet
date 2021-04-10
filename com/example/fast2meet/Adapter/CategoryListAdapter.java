package com.example.fast2meet.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fast2meet.Detailed_Ele;
import com.example.fast2meet.Model.CategoryModel;
import com.example.fast2meet.OverView_Ele;
import com.example.fast2meet.R;
import com.example.fast2meet.databinding.ItemServiceListBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CategoryListAdapter extends FirebaseRecyclerAdapter<CategoryModel,CategoryListAdapter.ViewHolder>
{

    ItemServiceListBinding binding;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoryListAdapter(@NonNull FirebaseRecyclerOptions<CategoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull CategoryModel model)
    {
            holder.binding.tvServiceName.setText(model.getName());
            holder.binding.cardCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), OverView_Ele.class);
                        intent.putExtra("serviceName",model.getName());
                    v.getContext().startActivity(intent);
                }
            });
        Glide.with(holder.binding.imgService.getContext()).load(model.getImage()).into(holder.binding.imgService);


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_service_list,parent,false);
        return new ViewHolder(binding);
    }


    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        ItemServiceListBinding binding;

        public ViewHolder(@NonNull  ItemServiceListBinding itemView) {
            super(itemView.getRoot());
            this.binding=itemView;
        }
    }
}
