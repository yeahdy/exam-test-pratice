package com.example.examservice.utils;

import com.example.examservice.exception.ZeroDivisionException;
import lombok.Getter;

@Getter
public class MyCalculator {

  private Double result;

  public MyCalculator() {
    this.result = 0.0;
  }

  public MyCalculator(Double result) {
    this.result = result;
  }

  public MyCalculator add(Double number) {
    this.result += number;
    return this;
  }

  public MyCalculator minus(Double number) {
    this.result -= number;
    return this;
  }

  public MyCalculator multiply(Double number) {
    this.result *= number;
    return this;
  }

  public MyCalculator divid(Double number) {
    if (number == 0.0) {
      throw new ZeroDivisionException();
    }

    this.result /= number;
    return this;
  }
}
