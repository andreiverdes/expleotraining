package com.andreiverdes.training.expleo.stackoverflow.data.model;

import com.andreiverdes.training.expleo.stackoverflow.data.FileUtils;
import com.squareup.moshi.Moshi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MoshiOwnerTest {

    FileUtils fileUtils = new FileUtils();
    private Moshi moshi;

    @Before
    public void setup() {
        moshi = new Moshi.Builder().build();
    }

    @Test
    public void testOwner() throws IOException {
        String ownerJson = fileUtils.getQuestionsJsonString("owner.json");
        Owner owner = moshi.adapter(Owner.class).fromJson(ownerJson);

        assertEquals(owner.reputation.intValue(), 31);
        assertEquals(owner.userId.intValue(), 2454911);
        assertEquals(owner.userType, "registered");
        assertEquals(owner.profileImage, "https://www.gravatar.com/avatar/b2a5f814a7161acf4bf9c02a4cb248f6?s=128&d=identicon&r=PG");
        assertEquals(owner.displayName, "Justin Hilton");
        assertEquals(owner.link, "https://stackoverflow.com/users/2454911/justin-hilton");
    }

}