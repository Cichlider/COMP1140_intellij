package ws8b;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ListExamples {

    public static int sumGeneralFor(List<Integer> list){
        int sum =0;
        for(int i =0; i<list.size();i++){
            sum+=list.get(i);
        }
        return sum;
    }

    public static int sumEnhancedFor(List<Integer> list){
        int sum = 0;
        for (Integer num: list){
            sum+=num;
        }
        return sum;
    }


}
