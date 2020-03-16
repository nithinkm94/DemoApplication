package com.example.homescreen.adapter;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homescreen.module.homescreen.MainActivityViewModel;
import com.example.homescreen.R;
import com.example.homescreen.databinding.LayoutItemBinding;
import com.example.homescreen.model.Items;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.DataViewHolder> {
    private static final String TAG = "ProductAdapter";
    private List<Items> data;
    Context context;
    private long mLastClickTime = 0;
    MainActivityViewModel mainActivityViewModel;

    public ListAdapter(Context context, MainActivityViewModel mainActivityViewModel) {
        this.context = context;
        this.data = new ArrayList<>();
        this.mainActivityViewModel = mainActivityViewModel;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,
                new FrameLayout(parent.getContext()), false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        Items dataModel = data.get(position);
//        if(dataModel.getTitle()!=null && !dataModel.getTitle().isEmpty()){
            holder.setViewModel(new Items(dataModel));
            Picasso.get()
                    .load(data.get(position).getImageHref())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.ivItemsImage);
//        }else{
//            holder.itemView.setVisibility(View.GONE);
//            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//        }



    }


    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public void onViewAttachedToWindow(DataViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(DataViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<Items> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setItems(List<Items> items) {

    }

    class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LayoutItemBinding binding;
        ImageView ivItemsImage;

        DataViewHolder(View itemView) {
            super(itemView);
            ivItemsImage = itemView.findViewById(R.id.iv_item);
            bind();

            itemView.setOnClickListener(this);
        }

        void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (binding != null) {
                binding.unbind(); // Don't forget to unbind
            }
        }

        void setViewModel(Items viewModel) {
            if (binding != null) {
                binding.setItems(viewModel);
            }
        }

        @Override
        public void onClick(View view) {


            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            mainActivityViewModel.onItemClicked(getAdapterPosition(), data.get(getAdapterPosition()));
        }
    }

}
