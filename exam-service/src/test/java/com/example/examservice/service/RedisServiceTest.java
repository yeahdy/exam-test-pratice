package com.example.examservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.examservice.IntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisServiceTest extends IntegrationTest {

  @Autowired private RedisService redisService;

  @Test
  @DisplayName("Redis get-set 테스트")
  void redis_get_set_test() {
    // given
    String expectValue = "hello";
    String key = "hi";

    // when
    redisService.set(key, expectValue);

    // then
    String actualValue = redisService.get(key);
    assertThat(actualValue).isEqualTo(expectValue);
  }
}
