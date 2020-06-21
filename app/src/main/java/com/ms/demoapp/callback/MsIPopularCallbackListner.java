package com.ms.demoapp.callback;

import com.ms.demoapp.model.MsPopularCategoryModel;

import java.util.List;

public interface MsIPopularCallbackListner {
    void onPopularLoadSuccess(List<MsPopularCategoryModel> popularCategoryModels);
    void onPopularLoadFailed(String message);
}
