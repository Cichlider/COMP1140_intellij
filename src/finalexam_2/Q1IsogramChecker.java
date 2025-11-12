package finalexam_2;

import java.util.HashMap;
import java.util.HashSet;

public class Q1IsogramChecker {
    /**
     * Check if a string is an isogram (no repeating letters, case-insensitive).
     * Null and empty strings are considered isograms.
     *
     * Examples:
     * isIsogram("aaa") -> false
     * isIsogram("abc") -> true
     * isIsogram("Abc") -> true
     * isIsogram("ABBA") -> false
     * isIsogram("") -> true
     *
     * @param str the string to check
     * @return true if isogram, false otherwise
     */
    public static boolean isIsogram(String str) {

        if (str=="" || str ==null){
            return true;
        }

        HashSet<Character> set = new HashSet<>();
        for (char i : str.toCharArray()){
            set.add(i);
        }

        return (str.length() == set.size());
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isIsogram("aaa"));      // false
        System.out.println(isIsogram("abc"));      // true
        System.out.println(isIsogram("Abc"));      // true
        System.out.println(isIsogram("ABBA"));     // false
        System.out.println(isIsogram(""));         // true
        System.out.println(isIsogram("abcdefg"));  // true
        System.out.println(isIsogram(null));       // true
    }

}


/**
 * // 1. Create HashSet
 * HashSet<Character> set = new HashSet<>();
 * Set<Character> set2 = new HashSet<>();  // using interface
 *
 * // 2. Add element
 * set.add('a');      // returns true if added
 * set.add('b');
 * set.add('a');      // returns false (duplicate)
 *
 * // 3. Check if contains
 * set.contains('a'); // returns true
 * set.contains('z'); // returns false
 *
 * // 4. Remove element
 * set.remove('a');   // returns true if removed
 *
 * // 5. Get size
 * set.size();        // returns number of elements
 *
 * // 6. Check if empty
 * set.isEmpty();     // returns boolean
 *
 * // 7. Clear all
 * set.clear();
 */


/**
 * // 1. Create HashMap
 * HashMap<String, Integer> map = new HashMap<>();
 * Map<String, Integer> map2 = new HashMap<>();  // using interface
 *
 * // 2. Put (add/update) key-value pair
 * map.put("apple", 5);      // returns null if new key
 * map.put("banana", 3);
 * map.put("apple", 10);     // updates value, returns old value (5)
 *
 * // 3. Get value by key
 * map.get("apple");         // returns 10
 * map.get("orange");        // returns null (key not found)
 *
 * // 4. Check if key exists
 * map.containsKey("apple"); // returns true
 * map.containsValue(10);    // returns true
 *
 * // 5. Remove key-value pair
 * map.remove("apple");      // returns value (10)
 *
 * // 6. Get size
 * map.size();               // returns number of entries
 *
 * // 7. Check if empty
 * map.isEmpty();            // returns boolean
 *
 * // 8. Get default value if key not found
 * map.getOrDefault("orange", 0);  // returns 0
 *
 * // 9. Iterate
 * for (String key : map.keySet()) { }
 * for (Integer value : map.values()) { }
 * for (Map.Entry<String, Integer> entry : map.entrySet()) {
 *     entry.getKey();
 *     entry.getValue();
 * }
 *
 * // 10. Clear all
 * map.clear();
 */