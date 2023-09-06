package com.market.app_online_market.payload;

public record RegoisterDTO(
        String fullName,
        String userName,
        String password,
        String phoneNumber,
        String email
) {
}
