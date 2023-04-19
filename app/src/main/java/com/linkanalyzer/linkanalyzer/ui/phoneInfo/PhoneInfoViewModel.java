package com.linkanalyzer.linkanalyzer.ui.phoneInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PhoneInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PhoneInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Phone Info fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}