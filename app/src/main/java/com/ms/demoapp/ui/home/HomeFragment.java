package com.ms.demoapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.ms.demoapp.R;
import com.ms.demoapp.adapter.MsBestDealAdapter;
import com.ms.demoapp.adapter.MsPopularCategoriesAdapter;
import com.ms.demoapp.model.MsBestDealModel;
import com.ms.demoapp.model.MsPopularCategoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    Unbinder unbinder;

    @BindView(R.id.ms_recycler_popular)
    RecyclerView recycler_popular;
    @BindView(R.id.ms_viewpager)
    LoopingViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, root);
        init();
        homeViewModel.getPopularList().observe(getViewLifecycleOwner(), new Observer<List<MsPopularCategoryModel>>() {
            @Override
            public void onChanged(List<MsPopularCategoryModel> popularCategoryModels) {
                //create adatper
                MsPopularCategoriesAdapter adatper = new MsPopularCategoriesAdapter(HomeFragment.this.getContext(), popularCategoryModels);
                recycler_popular.setAdapter(adatper);
            }
        });

        homeViewModel.getBestDealList().observe(getViewLifecycleOwner(), new Observer<List<MsBestDealModel>>() {
            @Override
            public void onChanged(List<MsBestDealModel> bestDealModels) {
                //create adatper
                MsBestDealAdapter adatper = new MsBestDealAdapter(HomeFragment.this.getContext(), bestDealModels, true);
                viewPager.setAdapter(adatper);
            }
        });

        return root;
    }

    private void init() {
        recycler_popular.setHasFixedSize(true);
        recycler_popular.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.resumeAutoScroll();
    }

    @Override
    public void onPause() {
        viewPager.pauseAutoScroll();
        super.onPause();
    }
}
