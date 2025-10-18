package past_final_exam;

import java.util.HashMap;

/**
 * 第一题是boolean两个字符串里出现的字母
 * 的数量是否相等，比如spot和stop
 */
public class p1 {
    public static HashMap<Character,Integer> helper(String str){
        HashMap<Character,Integer> lst = new HashMap<>();
        for (char c : str.toCharArray()){
            if(lst.containsKey(c)){
                lst.put(c,lst.get(c)+1);
            }else{
                lst.put(c,1);
            }
        }
        return lst;
    }


    public static boolean checkIfEqual(String str1, String str2){
        return (helper(str1).equals(helper(str2)));
    }

    public static void main(String[] args){
        System.out.println(checkIfEqual("spoot","stoop"));
    }
}
