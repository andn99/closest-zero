package com.daitum.hiring.test.closestzero.service;

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

    // Set the initial values for minIndex and closestSum
    Integer minIndex = 0;
    Integer closestSum = Integer.MAX_VALUE;

    /**
     * Note that the performance of using for loop here is still the same as getting
     * the sub-list of the first n items, then gradually remove the fist item of the
     * sub-list and add the next item to the end of that sub-list.
     * 
     * Big O: O((list.size()-n)*n)
     */

    for (Integer i = 0; i <= list.size() - n; i++) {
      List<Integer> subList = list.subList(i, i + n);
      Integer sum = absSumList(subList);
      // If sum is 0, return i immediately as it's the index of the first element
      if (sum == 0)
        return i;

      if (sum < closestSum) {
        closestSum = sum;
        minIndex = i;
      }
    }

    return minIndex;
  }

  /**
   * Return the absolute value of the sum of the list
   * 
   * @param list
   * @return absSum
   */
  private Integer absSumList(List<Integer> list) {
    Integer sum = 0;
    for (Integer item : list) {
      sum += item;
    }
    return Math.abs(sum);
  }
}
