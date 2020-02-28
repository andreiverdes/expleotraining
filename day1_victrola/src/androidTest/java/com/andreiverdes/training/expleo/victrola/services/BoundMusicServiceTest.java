package com.andreiverdes.training.expleo.victrola.services;

import android.content.Intent;
import android.os.IBinder;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ServiceTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

@RunWith(AndroidJUnit4.class)
public class BoundMusicServiceTest {

    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Test
    public void testWithBoundService() throws TimeoutException {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), BoundMusicService.class);

        IBinder binder = mServiceRule.bindService(intent);

        BoundMusicService service = ((BoundMusicService.Linker) binder).getService();

        //write your assertions
    }

}