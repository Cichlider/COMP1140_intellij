package ws9a;

public interface DataProcessor<T> {
    T process(T data);
}

class IntegerMultiplier implements DataProcessor<Integer> {
    @Override
    public Integer process(Integer data){
        return data*2;
    }
}

class StringReverser implements DataProcessor<String>{
    @Override
    public String process(String data){
        String reversed = "";
        for(int i = data.length() - 1; i>=0 ; i--){
            reversed += data.charAt(i);
        }
        return reversed;
    }
}