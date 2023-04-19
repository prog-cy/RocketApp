package com.example.rocketapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.rocketapp.model.RocketInfo;
import java.util.List;

//This View-Model is for MainActivity.
public class MainActivityViewModel extends ViewModel {

    //Creating object of MutableLiveData class
   MutableLiveData<List<RocketInfo>> mutableLiveData = new MutableLiveData<>();

   //Method to get MutableLiveData of List<Info> type
    public MutableLiveData<List<RocketInfo>> getMutableLiveData() {
        return mutableLiveData;
    }

    //Method to set data in mutableLiveData.
    public void setMutableLiveData(List<RocketInfo> rocketInfoList) {
        mutableLiveData.setValue(rocketInfoList);
    }
}
