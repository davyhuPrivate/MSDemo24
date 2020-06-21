package com.ms.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ms.demoapp.R;
import com.ms.demoapp.model.MsPopularCategoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
//import butterknife.Unbinder;

public class MsPopularCategoriesAdapter extends RecyclerView.Adapter<MsPopularCategoriesAdapter.MsMyViewHolder> {

    Context context;
    List<MsPopularCategoryModel> popularCategoryModelList;

    public MsPopularCategoriesAdapter(Context context, List<MsPopularCategoryModel> popularCategoryModelList) {
        this.context = context;
        this.popularCategoryModelList = popularCategoryModelList;
    }

    @NonNull
    @Override
    public MsMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MsMyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.ms_layout_popular_category_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MsMyViewHolder holder, int position) {
        Glide.with(context).load(popularCategoryModelList.get(position).getImage())
                .into(holder.img_circle_category);
        holder.txt_category_name.setText(popularCategoryModelList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return popularCategoryModelList.size();
    }

    public class MsMyViewHolder extends RecyclerView.ViewHolder {
        Unbinder unbinder;
        @BindView(R.id.ms_txt_category_name)
        TextView txt_category_name ;
        @BindView(R.id.ms_img_circle_category)
        CircleImageView img_circle_category ;

        public MsMyViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }

    }
}
