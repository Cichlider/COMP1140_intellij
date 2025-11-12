package mockexam1_answer;

import java.util.HashMap;

public class Q1 {

    /**
     * write a function that take a string and return a boolean
     * where:
     * true if and only if there exist a char appear twice in this string
     * false else case
     * example:
     * - aabce - true
     * - abced - false
     * - null - false
     * @param str
     * @return boolean
     */
    public static boolean Q1checktwo(String str){
        HashMap<Character,Integer> dic = new HashMap<>();

        for (char i : str.toCharArray()){
            if(dic.containsKey(i)){
                dic.put(i,dic.get(i)+1);
            }else{
                dic.put(i,1);
            }
        }
        return dic.containsValue(2);
    }

    public static void main(String[] args){
        System.out.println(Q1checktwo("aabce"));
        System.out.println(Q1checktwo("abce"));
        System.out.println(Q1checktwo(""));
    }
}
