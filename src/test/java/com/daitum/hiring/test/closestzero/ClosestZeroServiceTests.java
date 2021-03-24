package com.daitum.hiring.test.closestzero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import com.daitum.hiring.test.closestzero.service.ClosestZeroService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClosestZeroServiceTests {
  @Autowired
  ClosestZeroService closestZeroService;

  @Test
  public void contextLoads() throws Exception {
    assertNotNull(closestZeroService);
  }

  @Test
  void testServiceWithInvalidNParams() {
    Integer index = closestZeroService.findFirstIndexOfClosestZeroSubList(Arrays.asList(1, 2, 3, 4), -5);
    assertEquals(-1, index, "findFirstIndexOfClosestZeroSubList should return -1 when n is invalid");
  }

  @Test
  void testServiceWithNGreaterThanListSize() {
    Integer index = closestZeroService.findFirstIndexOfClosestZeroSubList(Arrays.asList(1, 2, -1, 1), 5);
    assertEquals(-1, index, "findFirstIndexOfClosestZeroSubList should return -1 when n is greater than list size");
  }

  @Test
  void testServiceWithEmptyList() {
    Integer index = closestZeroService.findFirstIndexOfClosestZeroSubList(new ArrayList<Integer>(), 5);
    assertEquals(-1, index, "findFirstIndexOfClosestZeroSubList should return -1 when list is empty");
  }

  @Test
  void testServiceWithNullList() {
    Integer index = closestZeroService.findFirstIndexOfClosestZeroSubList(null, 5);
    assertEquals(-1, index, "findFirstIndexOfClosestZeroSubList should return -1 when list is null");
  }

  @Test
  void testServiceWithValidParams() {
    Integer index = closestZeroService.findFirstIndexOfClosestZeroSubList(Arrays.asList(2, 2, -3, 0, 2), 2);
    assertEquals(1, index, "findFirstIndexOfClosestZeroSubList with list=[2,2,-3,0,2] and n = 2 should return 1");
  }

  @Test
  void testServiceWithMultipleClosestZeroSubList() {
    Integer index = closestZeroService.findFirstIndexOfClosestZeroSubList(Arrays.asList(-4, 2, 2, -3, 0, 2, 5, 0, 1),
        2);
    assertEquals(2, index,
        "findFirstIndexOfClosestZeroSubList should return the first index of the closest zero sub-list");
  }

  @Test
  void testServiceWithSubListSumIsZero() {
    // There are 3 zero sum sub-list: -4,2,2 and -3,3,0 and 0,1,-1. It should return
    // 0 as the first index
    Integer index = closestZeroService
        .findFirstIndexOfClosestZeroSubList(Arrays.asList(-4, 2, 2, -3, 3, 0, 2, 5, 0, 1, -1), 3);
    assertEquals(0, index, "findFirstIndexOfClosestZeroSubList should return the first index of the zero sum sub-list");
  }
}
