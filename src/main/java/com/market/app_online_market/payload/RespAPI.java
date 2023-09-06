package com.market.app_online_market.payload;

public record RespAPI(
        String message,

        boolean success,

        Integer status
) {
}
