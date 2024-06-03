package com.example.apigatewayservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Value("${jwt.token.secret}")
    private String SECRET_KEY;

    public AuthorizationHeaderFilter() {
        super(AuthorizationHeaderFilter.Config.class);
    }
    // 필터의 부가옵션을 추가할 수 있는데, 이것을 부모 클래스에 전달해야함.
    // 내부 코드를 보면 AbstractGatewayFilterFactory도 Object.class 를 상위부모에게 전달하고 있음
    public static class Config { }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                return onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
            }
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);    //key-value: AUTHORIZATION-토큰값
            String jwt = authorizationHeader.replace("Bearer ","");  //원본:
            
            if(!isJwtValid(jwt)){
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.setStatusCode(httpStatus);

        log.error(error);
        return serverHttpResponse.setComplete();
    }

    private boolean isJwtValid(String token) {
        byte[] secretKeyBytes = Base64.getEncoder().encode(SECRET_KEY.getBytes());
        SecretKey signingKey = Keys.hmacShaKeyFor(secretKeyBytes);

        boolean isValue = true;
        String subject = null;
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            subject = claims.getSubject();
        } catch (Exception ex) {
            isValue = false;
        }

        if (subject == null || subject.isEmpty()) {
            isValue = false;
        }

        return isValue;
    }

}
