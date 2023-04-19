package com.example.rocketapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rocketapp.model.DetailRocketInfo;

public class DetailActivityViewModel extends ViewModel {

    MutableLiveData<DetailRocketInfo> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<DetailRocketInfo> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(DetailRocketInfo detailRocketInfo) {
        mutableLiveData.setValue(detailRocketInfo);
    }
}
