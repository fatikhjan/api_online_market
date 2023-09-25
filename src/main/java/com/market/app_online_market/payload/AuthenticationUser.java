package com.market.app_online_market.payload;

import com.market.app_online_market.domain.Users;

public record AuthenticationUser(
        String token, Users user
) {

}
