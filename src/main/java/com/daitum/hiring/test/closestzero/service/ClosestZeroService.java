package com.daitum.hiring.test.closestzero.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * ClosestZeroService - a service class to handle the application's logic
 * 
 * @author An Do
 */
@Service
public class ClosestZeroService {
  /**
   * Find the index of the first element of the sub-list whose sum is closest to
   * zero. Return -1 if the given list or the length is invalid
   * 
   * @param list
   * @param n
   * @return the index of the first element
   */
  public Integer findFirstIndexOfClosestZeroSubList(List<Integer> list, Integer n) {
    // Check input parameters, return -1 if the params are invalid
    if (list == null || list.isEmpty() || n <= 0 || n > list.size())
      return -1;

    List<Integer> subList = new ArrayList<>();
    Long sum = 0L;
    Long closestSum = Long.MAX_VALUE;
    Integer minIndex = 0;

    for (int i = 0; i < list.size(); i++) {
      Integer item = list.get(i);

      if (subList.size() < n) {
        // If the subList size is still < n, adding item to sublist
        subList.add(item);
        sum += item;
        closestSum = Math.abs(sum);
      } else {
        // If the current closest sum is 0, return minIndex as the current sub-list is
        // the one whose sum is closest to 0, we don't need to consider the next
        // sub-lists
        if (closestSum == 0)
          return minIndex;

        // Remove the first item in the sublist and add the current item to the sublist
        Integer firstItem = subList.remove(0);
        subList.add(item);

        // Re-calculate sum by adding current item and substracting the removed item
        sum = sum + item - firstItem;

        // Compare current sum with closest sum and re-assign the minIndex if current
        // sum is closer to 0
        if (Math.abs(sum) < closestSum) {
          closestSum = Math.abs(sum);
          minIndex = i - n + 1;
        }
      }
    }

    return minIndex;

  }
}
