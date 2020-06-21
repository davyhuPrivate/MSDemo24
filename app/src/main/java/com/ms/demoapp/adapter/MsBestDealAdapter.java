package com.ms.demoapp.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.bumptech.glide.Glide;
import com.ms.demoapp.R;
import com.ms.demoapp.model.MsBestDealModel;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MsBestDealAdapter extends LoopingPagerAdapter<MsBestDealModel> {

    @BindView(R.id.ms_img_best_deal)
    ImageView img_best_deal;
    @BindView(R.id.ms_txt_best_deal)
    TextView text_best_deal;

    Unbinder unbinder;

    public MsBestDealAdapter(Context context, List<MsBestDealModel> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected void bindView(@NonNull View convertView, int listPosition, int viewType) {
        unbinder = ButterKnife.bind(this, convertView);
        // set data
        Glide.with(convertView).load(Objects.requireNonNull(getItemList()).get(listPosition).getImage())
                .into(img_best_deal);
        text_best_deal.setText(getItemList().get(listPosition).getName());
    }

    @NonNull
    @Override
    protected View inflateView(int viewType, @NonNull ViewGroup container, int listPosition) {
        return LayoutInflater.from(getContext()).inflate(R.layout.ms_layout_best_deal_items, container, false);
    }
}
