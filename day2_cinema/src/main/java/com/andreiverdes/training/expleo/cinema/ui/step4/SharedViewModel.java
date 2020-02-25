package com.andreiverdes.training.expleo.cinema.ui.step4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<Integer> seekBarValue = new MutableLiveData<>();

    public LiveData<Integer> getSeekBarValue() {
        return seekBarValue;
    }

    public void setSeekBarValue(Integer seekBarValue) {
        this.seekBarValue.postValue(seekBarValue);
    }
}
