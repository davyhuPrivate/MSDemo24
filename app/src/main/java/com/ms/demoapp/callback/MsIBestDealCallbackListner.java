package com.ms.demoapp.callback;

import com.ms.demoapp.model.MsBestDealModel;

import java.util.List;

public interface MsIBestDealCallbackListner {
    void onBestDealLoadSuccess(List<MsBestDealModel> bestDealModels);
    void onBestDealLoadFailed(String message);
}
