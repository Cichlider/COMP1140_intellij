package ws9b;

public class printlist {

    public static String outer(String str){
        return switch(str.length()){
            case 0 -> "";
            case 1 -> str.substring(0, 1);
            case 2 -> "" + str.charAt(1) + str.charAt(0);
            case 3 -> "" + str.charAt(1) + str.charAt(0) + str.charAt(2);
            case 4 -> "" + str.charAt(1) + str.charAt(0) + str.charAt(2) + str.charAt(3);
            default -> str;
        };
    }

    public static String takeFour(String str){
        if(str.length() > 4){
            return outer(str.substring(0,4)) + takeFour(str.substring(4));
        }else{
            return outer(str);
        }
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String helper(String str, int length, int s){
        if(str.length() == 0){
            return "";
        }
        if(str.length() <= length){
            return (s%2==0) ? reverse(str) : str;
        } else if (s % 2 == 0){
            return reverse(str.substring(0, length))
                    + helper(str.substring(length), length, s+1);
        } else {
            return str.substring(0, length)
                    + helper(str.substring(length), length, s+1);
        }
    }

    public static int fibSeqhelper(int a,int b,int n){
        if(n>0){
            return fibSeqhelper(a+b,a,n-1);
        }else{
            return a;
        }
    }

    public static int fibSeq(int n){
        return (fibSeqhelper(1,0,n));
    }

    public static void main(String[] args){
        System.out.println(fibSeq(0));
    }
}
