package com.linkanalyzer.linkanalyzer.ui.cellularInfo;

import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

public class CellularDetailsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CellularDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Phone Info fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}