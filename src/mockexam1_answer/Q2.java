package mockexam1_answer;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * part a
 * write a difination for rose tree
 */
class RoseTree<T>{
    T value;
    ArrayList<RoseTree<T>> children = null;

    RoseTree(T value){
        this.value = value;
    }


    public void setValue(T value) {
        this.value = value;
    }

    public void setChildren(ArrayList<RoseTree<T>> children){
        this.children = children;
    }

}



public class Q2 {
    /**
     * write a function that take a rose tree
     * and return the path from the root node to the
     * deepest node.
     * @param tree
     * @return
     * @param <T>
     */
    public static <T> ArrayList<T> Q2findDeepestPath(RoseTree<T> tree){
        ArrayList<T> list = new ArrayList<>();
        return helper(tree,list);
    }

    public static<T> ArrayList<T> helper(RoseTree<T> tree, ArrayList<T> list){
        if(tree == null){
            return list;
        }

        list.add(tree.value);

        if(tree.children == null || tree.children.isEmpty()){
            return list;
        }

        RoseTree<T> maxtree = tree.children.get(0);
        for (RoseTree<T> child : tree.children){
            if(depth(child) > depth(maxtree)){
                maxtree = child;
            }
        }

        return helper(maxtree, list);
    }

    public static <T> int depth(RoseTree<T> tree){
        if(tree == null || tree.children == null || tree.children.isEmpty()){
            return 0 ;
        }else{
            return 1 + childrenMaxDepth(tree.children);
        }
    }

    public static <T> int childrenMaxDepth(ArrayList<RoseTree<T>> children){
        if(children == null || children.isEmpty())
            return 0;
        int result = depth(children.get(0));

        for ( RoseTree<T> i : children){
            if(depth(i) >= result){
                result = depth(i);
            }
        }

        return result;
    }

    public static void main(String[] args){
        // 构建树：
        //      1
        //    / | \
        //   2  3  4
        //  /
        // 5

        RoseTree<Integer> node5 = new RoseTree<>(5);
        RoseTree<Integer> node2 = new RoseTree<>(2);
        node2.setChildren(new ArrayList<>(Arrays.asList(node5)));

        RoseTree<Integer> node3 = new RoseTree<>(3);
        RoseTree<Integer> node4 = new RoseTree<>(4);

        RoseTree<Integer> root = new RoseTree<>(1);
        root.setChildren(new ArrayList<>(Arrays.asList(node2, node3, node4)));

        System.out.println(Q2findDeepestPath(root));
    }
}
