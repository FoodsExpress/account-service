package com.foodexpress.accountservice.adapter.out.jwt;

public record JwtAuthentication(Long id, String accountId, String email, String nickname) {

}