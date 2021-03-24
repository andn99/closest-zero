package com.daitum.hiring.test.closestzero.controller;

import com.daitum.hiring.test.closestzero.payload.RequestPayload;
import com.daitum.hiring.test.closestzero.service.ClosestZeroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClosestZeroController - a controller class to the incoming requests, call the
 * approriate service method to handle the logic, and send the response to the
 * client
 * 
 * @author An Do
 */
@RestController
public class ClosestZeroController {

  @Autowired
  private ClosestZeroService service;

  /**
   * POST method to find the index of the first item of closest zero sub-list. The
   * reason for using the POST method instead of the GET method here is that it is
   * not recommended to send the request params in the body of the GET request. So
   * if using the GET method, we have to send the request params as parts of the
   * URL. If the list param is too large, the length of the URL can exceed the
   * limit of ~2000 characters
   * 
   * @param payload
   * @return index
   */
  @PostMapping("/api/closest_zero")
  public ResponseEntity<Integer> findIndexOfClosestZeroSubListSum(@RequestBody RequestPayload payload) {
    Integer index = service.findFirstIndexOfClosestZeroSubList(payload.getList(), payload.getN());
    return ResponseEntity.status(HttpStatus.OK).body(index);
  }
}
