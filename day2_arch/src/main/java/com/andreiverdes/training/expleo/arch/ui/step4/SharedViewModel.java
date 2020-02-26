package com.andreiverdes.training.expleo.arch.ui.step4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private static final String KEY_SEEKBAR_VALUE = "key_seekbar_value";

    private MutableLiveData<Integer> seekBarValue = new MutableLiveData<>();
    private SavedStateHandle savedStateHandle;

    public SharedViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        Integer seekBarValue = this.savedStateHandle.get(KEY_SEEKBAR_VALUE);
        if (seekBarValue != null) {
            this.seekBarValue.setValue(seekBarValue);
        }
    }

    public LiveData<Integer> getSeekBarValue() {
        return seekBarValue;
    }

    public void setSeekBarValue(Integer seekBarValue) {
        this.seekBarValue.postValue(seekBarValue);
        savedStateHandle.set(KEY_SEEKBAR_VALUE, seekBarValue);
    }
}
