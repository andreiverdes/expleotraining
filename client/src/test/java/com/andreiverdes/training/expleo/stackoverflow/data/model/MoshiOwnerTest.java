package com.andreiverdes.training.expleo.stackoverflow.data.model;

import com.squareup.moshi.Moshi;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MoshiOwnerTest {

    @Before
    public void setup() {
        Moshi moshi = new Moshi.Builder().build();
        Owner owner = moshi.adapter(Owner.class).fromJson();
    }

}