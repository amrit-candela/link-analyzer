package com.linkanalyzer.linkanalyzer.ui.wifiDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WiFiDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WiFiDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Wifi Details fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}