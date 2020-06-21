package com.ms.demoapp.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ms.demoapp.callback.MsIBestDealCallbackListner;
import com.ms.demoapp.callback.MsIPopularCallbackListner;
import com.ms.demoapp.model.MsBestDealModel;
import com.ms.demoapp.model.MsPopularCategoryModel;
import com.ms.demoapp.utility.MsCommon;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel implements MsIPopularCallbackListner, MsIBestDealCallbackListner {

    private MutableLiveData<List<MsPopularCategoryModel>> popularList;
    private MutableLiveData<List<MsBestDealModel>> bestDealList;
    private MutableLiveData<String> messageError;
    private MsIPopularCallbackListner popularCallbackListner;
    private MsIBestDealCallbackListner bestDealCallbackListner;

    public HomeViewModel() {
        popularCallbackListner = this;
        bestDealCallbackListner = this;
    }

    public MutableLiveData<List<MsBestDealModel>> getBestDealList() {
        if (bestDealList == null) {
            bestDealList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadBestDealList();
        }
        return bestDealList;
    }

    private void loadBestDealList() {
        final List<MsBestDealModel> tmpList = new ArrayList<>();
        DatabaseReference bestDealsRef = FirebaseDatabase.getInstance()
                .getReference(MsCommon.BEST_DEALS_REF);

        bestDealsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapShot:dataSnapshot.getChildren()){
                    MsBestDealModel model = itemSnapShot.getValue(MsBestDealModel.class);
                    tmpList.add(model);
                }
                bestDealCallbackListner.onBestDealLoadSuccess(tmpList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                bestDealCallbackListner.onBestDealLoadFailed(databaseError.getMessage());
            }
        });

    }

    public MutableLiveData<List<MsPopularCategoryModel>> getPopularList() {
        if (popularList == null) {
            popularList = new MutableLiveData<>();
            messageError = new MutableLiveData<>();
            loadPopularList();
        }
        return popularList;
    }

    private void loadPopularList() {
        final List<MsPopularCategoryModel> tmpList = new ArrayList<>();
        DatabaseReference popularRef = FirebaseDatabase.getInstance()
                .getReference(MsCommon.POPLULAR_CATEGORY_REF);

        popularRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapShot:dataSnapshot.getChildren()){
                    MsPopularCategoryModel model = itemSnapShot.getValue(MsPopularCategoryModel.class);
                    tmpList.add(model);
                }
                popularCallbackListner.onPopularLoadSuccess(tmpList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                popularCallbackListner.onPopularLoadFailed(databaseError.getMessage());
            }
        });
    }

    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    @Override
    public void onPopularLoadSuccess(List<MsPopularCategoryModel> popularCategoryModels) {
        popularList.setValue(popularCategoryModels);
    }

    @Override
    public void onPopularLoadFailed(String message) {
        messageError.setValue(message);
    }

    @Override
    public void onBestDealLoadSuccess(List<MsBestDealModel> bestDealModels) {
        bestDealList.setValue(bestDealModels);
    }

    @Override
    public void onBestDealLoadFailed(String message) {
        messageError.setValue(message);
    }
}