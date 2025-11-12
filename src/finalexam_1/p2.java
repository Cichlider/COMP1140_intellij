package finalexam_1;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;



class TreeNode<T>{
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;

    TreeNode(T value){
        this.left = null;
        this.value = value;
        this.right = null;
    }

    public T getValue(){
        return value;
    }

    public TreeNode<T> getLeft(){
        return left;
    }

    public TreeNode<T> getRight(){
        return right;
    }

    // Setter方法
    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}


public class p2 {

    /**
     * Final Exam - Question 2: Greedy Path Selection
     *
     * Description:
     * Given a binary tree root node and a comparator, starting from the root,
     * repeatedly compare the left and right children of the current node,
     * select the "larger" child to continue traversing downward,
     * until reaching a leaf node. Return a list of all node values on this path.
     *
     * Parameters:
     * @param root - the root node of the binary tree
     * @param comparator - a comparator for comparing node values
     *
     * Return:
     * @return a list of all node values on the greedy path from root to leaf
     *
     * Notes:
     * - If the current node has both left and right children, use the comparator
     *   to compare them and select the larger one
     * - If there is only one child, select that child directly
     * - Stop when reaching a leaf node (no children)
     * - The path should include the root node
     *
     * Example:
     * Input tree:
     *         10
     *        /  \
     *       5    15
     *      / \   / \
     *     3   7 12 20
     *
     * Using natural order comparator (selecting larger values):
     * Output: [10, 15, 20]
     * Explanation: 10 → 15 (15>5) → 20 (20>12)
     */
    public static <T> List<T> findGreedyPath(TreeNode<T> root, Comparator<T> comparator) {
        ArrayList<T> example = new ArrayList<>(asList(root.getValue()));
        ArrayList<T> lst = p2helper(root, comparator, example);
        return lst;
    }

    public static <T> ArrayList<T> p2helper (TreeNode<T> root, Comparator<T> comparator, ArrayList<T> lst){
        if(root.getLeft()==null && root.getRight() == null){
            return lst;
        }else if (root.getLeft() !=null && root.getRight() != null){
            int cmp = comparator.compare(root.getLeft().getValue(),root.getRight().getValue());
            if(cmp>0){
                lst.add(root.getLeft().getValue());
                return p2helper(root.getLeft(),comparator,lst);
            }else{
                lst.add(root.getRight().getValue());
                return p2helper(root.getRight(),comparator,lst);
            }
        }else if (root.getLeft() ==null && root.getRight() != null){
            lst.add(root.getRight().getValue());
            return p2helper(root.getRight(),comparator,lst);
        }else if (root.getLeft() !=null && root.getRight() == null){
            lst.add(root.getLeft().getValue());
            return p2helper(root.getLeft(),comparator,lst);
        }
        return null;
    }

    // ========================================
    //         MAIN METHOD FOR TESTING
    // ========================================

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║  Final Exam Q2 - Greedy Path Selection Tests      ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();

        runTest1();
        runTest2();
        runTest3();
        runTest4();
        runTest5();

        System.out.println("════════════════════════════════════════════════════");
        System.out.println("All tests completed!");
        System.out.println("════════════════════════════════════════════════════");
    }


    /**
     * Test 1: Integer tree - select larger values
     */
    private static void runTest1() {
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│ Test 1: Integer Tree - Natural Order (max)    │");
        System.out.println("└────────────────────────────────────────────────┘");

        // Build tree
        TreeNode<Integer> root = new TreeNode<>(10);
        root.setLeft(new TreeNode<>(5));
        root.setRight(new TreeNode<>(15));
        root.getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(7));
        root.getRight().setLeft(new TreeNode<>(12));
        root.getRight().setRight(new TreeNode<>(20));

        System.out.println("Tree structure:");
        System.out.println("        10");
        System.out.println("       /  \\");
        System.out.println("      5    15");
        System.out.println("     / \\   / \\");
        System.out.println("    3   7 12 20");
        System.out.println();

        List<Integer> result = findGreedyPath(root, Comparator.naturalOrder());
        List<Integer> expected = Arrays.asList(10, 15, 20);

        printResult(result, expected);
        System.out.println();
    }


    /**
     * Test 2: Integer tree - select smaller values
     */
    private static void runTest2() {
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│ Test 2: Integer Tree - Reverse Order (min)    │");
        System.out.println("└────────────────────────────────────────────────┘");

        TreeNode<Integer> root = new TreeNode<>(10);
        root.setLeft(new TreeNode<>(5));
        root.setRight(new TreeNode<>(15));
        root.getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().setRight(new TreeNode<>(7));
        root.getRight().setLeft(new TreeNode<>(12));
        root.getRight().setRight(new TreeNode<>(20));

        System.out.println("Tree structure: (same as Test 1)");
        System.out.println("        10");
        System.out.println("       /  \\");
        System.out.println("      5    15");
        System.out.println("     / \\   / \\");
        System.out.println("    3   7 12 20");
        System.out.println();

        List<Integer> result = findGreedyPath(root, Comparator.reverseOrder());
        List<Integer> expected = Arrays.asList(10, 5, 3);

        printResult(result, expected);
        System.out.println();
    }


    /**
     * Test 3: String tree - lexicographic order
     */
    private static void runTest3() {
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│ Test 3: String Tree - Lexicographic Order     │");
        System.out.println("└────────────────────────────────────────────────┘");

        TreeNode<String> root = new TreeNode<>("M");
        root.setLeft(new TreeNode<>("D"));
        root.setRight(new TreeNode<>("T"));
        root.getLeft().setLeft(new TreeNode<>("A"));
        root.getLeft().setRight(new TreeNode<>("H"));
        root.getRight().setLeft(new TreeNode<>("P"));
        root.getRight().setRight(new TreeNode<>("Z"));

        System.out.println("Tree structure:");
        System.out.println("        M");
        System.out.println("       /  \\");
        System.out.println("      D    T");
        System.out.println("     / \\   / \\");
        System.out.println("    A   H P   Z");
        System.out.println();

        List<String> result = findGreedyPath(root, Comparator.naturalOrder());
        List<String> expected = Arrays.asList("M", "T", "Z");

        printResult(result, expected);
        System.out.println();
    }


    /**
     * Test 4: Chain tree (only left children)
     */
    private static void runTest4() {
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│ Test 4: Chain Tree (Edge Case)                │");
        System.out.println("└────────────────────────────────────────────────┘");

        TreeNode<Integer> root = new TreeNode<>(10);
        root.setLeft(new TreeNode<>(5));
        root.getLeft().setLeft(new TreeNode<>(3));
        root.getLeft().getLeft().setLeft(new TreeNode<>(1));

        System.out.println("Tree structure:");
        System.out.println("      10");
        System.out.println("     /");
        System.out.println("    5");
        System.out.println("   /");
        System.out.println("  3");
        System.out.println(" /");
        System.out.println("1");
        System.out.println();

        List<Integer> result = findGreedyPath(root, Comparator.naturalOrder());
        List<Integer> expected = Arrays.asList(10, 5, 3, 1);

        printResult(result, expected);
        System.out.println();
    }


    /**
     * Test 5: Single node (edge case)
     */
    private static void runTest5() {
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│ Test 5: Single Node (Edge Case)               │");
        System.out.println("└────────────────────────────────────────────────┘");

        TreeNode<Integer> root = new TreeNode<>(42);

        System.out.println("Tree structure:");
        System.out.println("      42");
        System.out.println();

        List<Integer> result = findGreedyPath(root, Comparator.naturalOrder());
        List<Integer> expected = Arrays.asList(42);

        printResult(result, expected);
        System.out.println();
    }


    /**
     * Helper method to print test results
     */
    private static <T> void printResult(List<T> result, List<T> expected) {
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + result);

        if (result.equals(expected)) {
            System.out.println("Status:   ✅ PASS");
        } else {
            System.out.println("Status:   ❌ FAIL");
        }
    }
}

