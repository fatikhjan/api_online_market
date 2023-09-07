package com.market.app_online_market.payload;

public record RespAPI<T>(
        String message,

        T t,

        boolean success,

        Integer status
) {
}
