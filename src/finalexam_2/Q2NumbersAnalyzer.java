package finalexam_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2NumbersAnalyzer {
    /**
     * For each sublist: calculate sum and max.
     * If max <= sum, add [max, sum] to result.
     * Otherwise, add [sum, max] to result.
     * Skip null or empty sublists.
     *
     * Examples:
     * [[1,2,3], [5,1], [10]] -> [3,6, 5,6, 10,10]
     *   [1,2,3]: sum=6, max=3, 3<=6 -> [3,6]
     *   [5,1]: sum=6, max=5, 5<=6 -> [5,6]
     *   [10]: sum=10, max=10, 10<=10 -> [10,10]
     *
     * @param numbers 2D list of integers
     * @return ArrayList with analysis results
     */
    public static ArrayList<Integer> analyzeNumbers(List<List<Integer>> numbers) {
        if(numbers == null || numbers.size()==0){
            return new ArrayList<>();
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (List<Integer> i : numbers){
            if(i != null && i.size() !=0){
                list.add(helper(i).get(0));
                list.add(helper(i).get(1));
            }
        }

        return list;
    }

    public static ArrayList<Integer> helper(List<Integer> list){
        ArrayList<Integer> a = new ArrayList<>();
        if(max(list) <= sum(list)){
            a.add(max(list));
            a.add(sum(list));
        }else{
            a.add(sum(list));
            a.add(max(list));
        }
        return a;
    }

    public static Integer sum(List<Integer> list){
        int sum = 0;
        if (list == null || list.size()==0){
            return 0;
        }
        for (int i : list){
            sum+=i;
        }
        return sum;
    }

    public static Integer max(List<Integer> list){
        if(list == null || list.size()==0){
            return 0;
        }
        int a = list.get(0);

        for (int i : list){
            if(i>=a){
                a=i;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        // Test 1
        List<List<Integer>> test1 = new ArrayList<>();
        test1.add(Arrays.asList(1, 2, 3));
        test1.add(Arrays.asList(5, 1));
        test1.add(Arrays.asList(10));
        System.out.println(analyzeNumbers(test1));  // [3,6, 5,6, 10,10]

        // Test 2
        List<List<Integer>> test2 = new ArrayList<>();
        test2.add(Arrays.asList(2, 8, 3));
        test2.add(Arrays.asList(4, 4));
        System.out.println(analyzeNumbers(test2));  // [8,13, 4,8]

        // Test 3 - with empty list
        List<List<Integer>> test3 = new ArrayList<>();
        test3.add(Arrays.asList(5, 5, 5));
        test3.add(new ArrayList<>());
        test3.add(Arrays.asList(1));
        System.out.println(analyzeNumbers(test3));  // [5,15, 1,1]
    }
}

/**
 * // 1. Create
 * ArrayList<Integer> list = new ArrayList<>();
 * List<Integer> list2 = new ArrayList<>();  // using interface
 *
 * // 2. Add elements
 * list.add(10);           // add to end
 * list.add(0, 5);         // add at index 0
 * list.addAll(list2);     // add all from another list
 *
 * // 3. Get element
 * list.get(0);            // returns element at index 0
 *
 * // 4. Set/Update element
 * list.set(0, 20);        // replace element at index 0
 *
 * // 5. Remove element
 * list.remove(0);         // remove by index, returns removed element
 * list.remove(Integer.valueOf(10));  // remove by value (for Integer)
 *
 * // 6. Check
 * list.contains(10);      // returns boolean
 * list.isEmpty();         // returns boolean
 * list.size();            // returns number of elements
 *
 * // 7. Get index
 * list.indexOf(10);       // returns first index, or -1 if not found
 * list.lastIndexOf(10);   // returns last index
 *
 * // 8. Clear
 * list.clear();           // remove all elements
 *
 * // 9. Loop
 * for (int i = 0; i < list.size(); i++) {
 *     list.get(i);
 * }
 * for (int num : list) {
 *     // use num
 * }
 *
 * // 10. Convert to array
 * Integer[] arr = list.toArray(new Integer[0]);
 */