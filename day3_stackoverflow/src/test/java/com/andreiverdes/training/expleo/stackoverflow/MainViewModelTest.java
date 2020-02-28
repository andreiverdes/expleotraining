package com.andreiverdes.training.expleo.stackoverflow;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.andreiverdes.training.expleo.utils.AwaitLiveValue.getOrAwait;

@RunWith(AndroidJUnit4.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void getItemsListLiveData() {
        //TODO implement and inject a FakeRepository that returns custom Items
        Application application = ApplicationProvider.getApplicationContext();
        MainViewModel mainViewModel = new MainViewModel(application);
        List<QuestionsAdapter.Item> questions = getOrAwait(mainViewModel.getItemsListLiveData());
        Assert.assertEquals(questions.size(), 0);
    }

    @Test
    public void getSearchString() {
        //TODO implement and inject a FakeRepository that returns custom Items
        Application application = ApplicationProvider.getApplicationContext();
        MainViewModel mainViewModel = new MainViewModel(application);
        mainViewModel.getSearchString().setValue("TODO some filter right here!");
        List<QuestionsAdapter.Item> questions = getOrAwait(mainViewModel.getItemsListLiveData());
        //TODO assert that the values are the filtered ones
    }
}