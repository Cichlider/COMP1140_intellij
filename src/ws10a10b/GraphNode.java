package ws10a10b;
import java.util.*;
import static java.util.Arrays.asList;

public class GraphNode<T>{
    private final T value;
    private final List<GraphNode<T>> neighbours = new ArrayList<>();
    private final Map<GraphNode<T>,Integer> neighboursCosts =new HashMap<>();


    GraphNode(T value, List<GraphNode<T>> listnode, List<Integer> listvalue){
        this.value = value;
        for (int i =0 ; i<listnode.size(); i++){
            var a = listnode.get(i);
            var b = listvalue.get(i);

            this.neighbours.add(a);
            a.neighbours.add(this);

            this.neighboursCosts.put(a,b);
            a.neighboursCosts.put(this,b);
        }
    }

    T getValue(){
        return this.value;
    }

    Collection<GraphNode<T>> getNeighbours(){
        return this.neighbours;
    }

    Map<GraphNode<T>,Integer> getNeighboursCosts(){
        return this.neighboursCosts;
    }

    @Override
    public String toString(){
        return value.toString();
    }

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
        var node3 = new GraphNode<>(3, asList(node1,node2), asList(4, 1));
        return node3;
    }

    public static GraphNode<String> generateExampleGraph5() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(15));
        var nodeC = new GraphNode<>("C", asList(nodeB), asList(20));
        var nodeD = new GraphNode<>("D", asList(nodeA,nodeC), asList(10,5));
        var nodeE = new GraphNode<>("E", asList(nodeD,nodeB), asList(11,7));
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
        var nodeH = new GraphNode<>("H", asList(nodeC,nodeD), asList(11,1));
        var nodeI = new GraphNode<>("I", asList(nodeC,nodeG), asList(21,4));
        var nodeJ = new GraphNode<>("J", asList(nodeC,nodeH), asList(19,3));
        return nodeA;
    }

    public static GraphNode<String> generateExampleGraph8() {
        var nodeA = new GraphNode<>("A", asList(), asList());
        var nodeB = new GraphNode<>("B", asList(nodeA), asList(4));
        var nodeC = new GraphNode<>("C", asList(nodeB), asList(5));
        var nodeD = new GraphNode<>("D", asList(nodeC), asList(17));
        var nodeE = new GraphNode<>("E", asList(nodeD), asList(5));
        var nodeF = new GraphNode<>("F", asList(nodeC,nodeE), asList(12,2));
        return nodeA;
    }

    public List<T> iterativeDFS(){
        List<T> result = new ArrayList<>();
        Set<GraphNode<T>> visited = new HashSet<>();
        Stack<GraphNode<T>> stack = new Stack<>();

        stack.push(this);

        while (!stack.isEmpty()){

            GraphNode<T> current = stack.pop();

            if(!visited.contains(current)){
                visited.add(current);
                result.add(current.getValue());

                List<GraphNode<T>> neighbours = new ArrayList<>(current.getNeighbours());
                for(int i = neighbours.size() - 1; i >= 0; i--){
                    stack.push(neighbours.get(i));
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        Stack<Integer> a = new Stack<>();

    }

}


