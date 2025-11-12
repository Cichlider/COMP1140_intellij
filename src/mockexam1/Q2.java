package mockexam1;

import java.util.ArrayList;

/**
 * Part A
 * Complete the definition for a Rose Tree data structure.
 * A Rose Tree is a tree where each node can have any number of children.
 * Each node contains a value of type T and a list of child nodes.
 */
class RoseTree<T> {
    T value;
    ArrayList<RoseTree<T>> children;

    RoseTree(T value) {
        // FIXME complete constructor
    }

    public void setValue(T value) {
        // FIXME complete method
    }

    public void setChildren(ArrayList<RoseTree<T>> children) {
        // FIXME complete method
    }
}

public class Q2 {
    /**
     * Part B
     * Given a Rose Tree, finds and returns the path from the root node
     * to the deepest node in the tree. The path is represented as an
     * ArrayList containing the values of nodes from root to the deepest node.
     * If there are multiple paths with the same maximum depth, return any one.
     *
     * Examples:
     *   Given tree:
     *        1
     *      / | \
     *     2  3  4
     *    /
     *   5
     *   Expect: [1, 2, 5] (path from root to deepest node)
     *
     *   Given tree:
     *        1
     *       / \
     *      2   3
     *   Expect: [1, 2] or [1, 3] (both are valid)
     *
     * @param tree the root of the Rose Tree
     * @return ArrayList containing the path from root to deepest node
     * @param <T> the type of values stored in the tree
     */
    public static <T> ArrayList<T> Q2findDeepestPath(RoseTree<T> tree) {
        return null; // FIXME complete method
    }
}