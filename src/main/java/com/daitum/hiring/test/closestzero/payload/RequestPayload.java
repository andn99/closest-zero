package com.daitum.hiring.test.closestzero.payload;

import java.util.List;

/**
 * Request Payload Object
 * 
 * @author An Do
 */
public class RequestPayload {
  private List<Integer> list;
  private Integer n;

  public void setList(List<Integer> list) {
    this.list = list;
  }

  public List<Integer> getList() {
    return this.list;
  }

  public void setN(Integer n) {
    this.n = n;
  }

  public Integer getN() {
    return this.n;
  }
}
