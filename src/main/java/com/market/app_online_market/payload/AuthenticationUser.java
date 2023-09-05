package com.market.app_online_market.payload;

import com.market.app_online_market.domain.User;

public record AuthenticationUser(
        String token,
        User user
) {

}
