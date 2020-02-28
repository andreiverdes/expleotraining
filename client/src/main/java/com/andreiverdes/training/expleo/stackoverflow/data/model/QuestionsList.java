
package com.andreiverdes.training.expleo.stackoverflow.data.model;

import com.squareup.moshi.Json;

import java.util.List;

public class QuestionsList {

    @Json(name = "items") public List<Item> items = null;

}
