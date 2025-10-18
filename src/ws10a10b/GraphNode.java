package ws10a10b;
import java.util.*;
import static java.util.Arrays.asList;

public class GraphNode<T> {
    private final T value;
    private final List<GraphNode<T>> neighbours = new ArrayList<>();
    private final Map<GraphNode<T>, Integer> neighboursCosts = new HashMap<>();

    // 构造函数
    public GraphNode(T value, List<GraphNode<T>> neighbours, List<Integer> neighboursCosts) {
        this.value = value;
        for (int i = 0; i < neighbours.size(); i++) {
            var neighbour = neighbours.get(i);
            var cost = neighboursCosts.get(i);

            this.neighbours.add(neighbour);
            neighbour.neighbours.add(this);

            this.neighboursCosts.put(neighbour, cost);
            neighbour.neighboursCosts.put(this, cost);
        }
    }

    // Getter方法
    T getValue() {
        return value;
    }

    Collection<GraphNode<T>> getNeighbours() {
        return neighbours;
    }

    Map<GraphNode<T>, Integer> getNeighboursCosts() {
        return neighboursCosts;
    }

    @Override
    public String toString() {
        return value.toString();
    }





    /**
     * Returns a list of graph node values resulting from an iterative
     * DFS traversal of the graph starting from the node on which the method
     * is invoked. The order in which adjacent nodes of a node are explored and
     * enumerated should be based on the order in the Collection returned by
     * getNeighbours().
     */

    public List<T> iterativeDFS() {
        List<T> result = new ArrayList<>();           // 存储遍历结果
        Set<GraphNode<T>> visited = new HashSet<>();   // 记录已访问的节点
        Stack<GraphNode<T>> stack = new Stack<>();     // DFS用栈

        stack.push(this);  // 从当前节点开始

        while (!stack.isEmpty()) {
            GraphNode<T> current = stack.pop();  // 取出栈顶节点

            if (!visited.contains(current)) {
                visited.add(current);              // 标记为已访问
                result.add(current.getValue());    // 加入结果

                // 关键：逆序添加邻居到栈中
                // 这样才能保证正确的遍历顺序
                List<GraphNode<T>> neighbours = new ArrayList<>(current.getNeighbours());
                for (int i = neighbours.size() - 1; i >= 0; i--) {
                    if (!visited.contains(neighbours.get(i))) {
                        stack.push(neighbours.get(i));
                    }
                }
            }
        }

        return result;
    }
    // ========== 8个示例图生成方法 ==========

    public static GraphNode<Integer> generateExampleGraph1() {
        return new GraphNode<>(5, asList(), asList());
    }

    public static GraphNode<String> generateExampleGraph2() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(1));
        return nodeA;
    }

    public static GraphNode<Integer> generateExampleGraph3() {
        var node1 = new GraphNode<>(1, asList(), asList());
        var node2 = new GraphNode<>(2, asList(node1), asList(2));
        var node3 = new GraphNode<>(3, asList(node1), asList(1));
        return node1;
    }

    public static GraphNode<Integer> generateExampleGraph4() {
        var node1 = new GraphNode<>(1, asList(), asList());
        var node2 = new GraphNode<>(2, asList(node1), asList(2));
        var node3 = new GraphNode<>(3, asList(node1, node2), asList(4, 1));
        return node3;
    }

    public static GraphNode<String> generateExampleGraph5() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(15));
        var nodeC = new GraphNode<>("C", asList(nodeB), asList(20));
        var nodeD = new GraphNode<>("D", asList(nodeA, nodeC), asList(10, 5));
        var nodeE = new GraphNode<>("E", asList(nodeD, nodeB), asList(11, 7));
        return nodeA;
    }

    public static GraphNode<Integer> generateExampleGraph6() {
        var node111 = new GraphNode<>(111, asList(), asList());
        var node222 = new GraphNode<>(222, asList(node111), asList(4));
        var node333 = new GraphNode<>(333, asList(node111), asList(5));
        var node444 = new GraphNode<>(444, asList(node111), asList(17));
        var node555 = new GraphNode<>(555, asList(node222), asList(5));
        var node666 = new GraphNode<>(666, asList(node222), asList(12));
        var node777 = new GraphNode<>(777, asList(node222), asList(6));
        var node888 = new GraphNode<>(888, asList(node333), asList(11));
        var node999 = new GraphNode<>(999, asList(node333), asList(21));
        var node101 = new GraphNode<>(101, asList(node333), asList(19));
        return node111;
    }

    public static GraphNode<String> generateExampleGraph7() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(4));
        var nodeC = new GraphNode<>("C", asList(nodeA), asList(5));
        var nodeD = new GraphNode<>("D", asList(nodeA), asList(17));
        var nodeE = new GraphNode<>("E", asList(nodeB), asList(5));
        var nodeF = new GraphNode<>("F", asList(nodeB), asList(12));
        var nodeG = new GraphNode<>("G", asList(nodeB), asList(16));
        var nodeH = new GraphNode<>("H", asList(nodeC, nodeD), asList(11, 1));
        var nodeI = new GraphNode<>("I", asList(nodeC, nodeG), asList(21, 4));
        var nodeJ = new GraphNode<>("J", asList(nodeC, nodeH), asList(19, 3));
        return nodeA;
    }

    public static GraphNode<String> generateExampleGraph8() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(4));
        var nodeC = new GraphNode<>("C", asList(nodeB), asList(5));
        var nodeD = new GraphNode<>("D", asList(nodeC), asList(17));
        var nodeE = new GraphNode<>("E", asList(nodeD), asList(5));
        var nodeF = new GraphNode<>("F", asList(nodeC, nodeE), asList(12, 2));
        return nodeA;
    }
}


