package ws8b;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class BinaryTree<T>{
    T value;
    BinaryTree<T> left;
    BinaryTree<T> right;

    BinaryTree(T value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public void setValue(T value){
        this.value = value;
    }
    public void setLeft(BinaryTree<T> left){
        this.left=left;
    }
    public void setRight(BinaryTree<T> right){
        this.right = right;
    }

}

/**
 * rosetree trarvse and find max value or target value;
 * @param <T>
 */
class RoseTree<T>{
    T value;
    List<RoseTree<T>> children;



}

public class Zigzag {

    public static <T> ArrayList<T> findPath(BinaryTree<T> tree){
        ArrayList<T> List= new ArrayList<>();
        ArrayList<T> result = helper(tree,0,List);

        return result;
    }

    public static <T> ArrayList<T> helper(BinaryTree<T> tree, int a, ArrayList<T> list){
//        return switch(tree){
//            case null -> list;
//            default -> {
//                list.add(tree.value);
//                yield (a%2 ==0 )? helper(tree.left,a+1,list) :helper(tree.right,a+1,list) ;
//            }
//        };

        if(tree == null){
            return list;
        }else{
            list.add(tree.value);
            return (a%2 ==0 )? helper(tree.left,a+1,list) :helper(tree.right,a+1,list) ;
        }
    }

    public static<T> ArrayList<T> dfs(BinaryTree<T> tree){
        ArrayList<T> result = new ArrayList<>();

        if(tree == null) return result;

        result.addAll(dfs(tree.left));    // 递归
        result.add(tree.value);
        result.addAll(dfs(tree.right));   // 递归

        return result;
    }

    public static void main(String[] args){
        BinaryTree<Integer> a = new BinaryTree<>(1);
        BinaryTree<Integer> b = new BinaryTree<>(2);
        b.setLeft(a);
        b.setRight(a);
        BinaryTree<Integer> c = new BinaryTree<>(3);

        c.setRight(b);
        c.setLeft(a);
        BinaryTree<Integer> d = new BinaryTree<>(4);
        d.setLeft(c);
        d.setRight(a);

        System.out.println(findPath(d));
     }
}
