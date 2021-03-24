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

    // The temporary variable for holding the index of the first element of the
    // sublist whose sum is closest to zero
    Integer minIndex = 0;
    // The current index used for the while loop
    Integer currentIndex = 0;

    /**
     * The first sub-list of n items. Create a new ArrayList so that it can be
     * mutable for removing and adding item. If we use List<Integer> subList =
     * list.subList(0, n) and the list is a fixed size list param (ie, created by
     * Arrays.asList(1,2,3)). Then the subList might become immutable and we'd get
     * error when using subList.remove(), subList.add()
     */

    List<Integer> subList = new ArrayList<>();
    subList.addAll(list.subList(0, n));

    // The sum of the current subList
    Long currentSum = sumList(subList);
    // If current sum is 0, the first sublist is the one whose sum is closest to 0
    if (currentSum == 0)
      return 0;

    Long closestSum = Math.abs(currentSum);

    while (currentIndex < list.size() - n) {
      currentIndex++;
      // Remove the first item in the sublist
      Integer firstItem = subList.remove(0);

      // Add the next item to the sublist
      Integer nextItem = list.get(currentIndex + n - 1);
      subList.add(nextItem);

      // Re-calculate the sum of current subList by adding the next item and
      // substracting the removed item
      currentSum = currentSum + nextItem - firstItem;

      // Return current index if sum is 0, don't need to consider the next sub-lists
      if (currentSum == 0)
        return currentIndex;

      if (Math.abs(currentSum) < closestSum) {
        closestSum = Math.abs(currentSum);
        minIndex = currentIndex;
      }
    }

    return minIndex;
  }

  /**
   * Return the the sum of all items in the list
   * 
   * @param list
   * @return sum
   */
  private Long sumList(List<Integer> list) {
    Long sum = 0L;
    for (Integer item : list) {
      sum += item;
    }
    return sum;
  }
}
