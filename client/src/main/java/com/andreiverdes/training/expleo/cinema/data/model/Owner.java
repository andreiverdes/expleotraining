
package com.andreiverdes.training.expleo.cinema.data.model;

import com.squareup.moshi.Json;

public class Owner {

    @Json(name = "reputation") public Integer reputation;
    @Json(name = "user_id") public Integer userId;
    @Json(name = "user_type") public String userType;
    @Json(name = "profile_image") public String profileImage;
    @Json(name = "display_name") public String displayName;
    @Json(name = "link") public String link;

}
