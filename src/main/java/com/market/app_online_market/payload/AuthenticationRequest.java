package com.market.app_online_market.payload;

public record AuthenticationRequest(
        String username, String password
) {
}
