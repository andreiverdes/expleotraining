package com.andreiverdes.training.expleo.stackoverflow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static com.andreiverdes.training.expleo.utils.AwaitLiveValue.getOrAwait;

@RunWith(AndroidJUnit4.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private FakeRepository fakeRepository;
    private MainViewModel mainViewModel;

    @Before
    public void setup() {
        fakeRepository = new FakeRepository();
        mainViewModel = new MainViewModel(fakeRepository);
    }
    @Test
    public void getItemsListLiveData() {
        List<QuestionsAdapter.Item> questions = getOrAwait(mainViewModel.getItemsListLiveData());
        Assert.assertEquals(questions.size(), 0);
        //TODO assert that the values are the ones built in the FakeRepo
    }

    @Test
    public void getSearchString() {

        List<QuestionsAdapter.Item> questions = getOrAwait(mainViewModel.getItemsListLiveData());
        //TODO assert that the values are the filtered ones
    }
}