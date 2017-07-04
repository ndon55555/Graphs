import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Don on 7/3/2017.
 */
public class WeightedGraphTest {
    public static void main(String[] args) {
        Map<Integer, Set<WeightedEdge<Integer>>> nodeToEdges = new HashMap<>();
        addEntryToIntegerMap(nodeToEdges, 1, 2, 1);
        addEntryToIntegerMap(nodeToEdges, 1, 5, 9);
        addEntryToIntegerMap(nodeToEdges, 2, 1, 1);
        addEntryToIntegerMap(nodeToEdges, 2, 5, 9);
        addEntryToIntegerMap(nodeToEdges, 2, 3, 1);
        addEntryToIntegerMap(nodeToEdges, 3, 5, 8.7);
        addEntryToIntegerMap(nodeToEdges, 3, 4, 0.5);
        addEntryToIntegerMap(nodeToEdges, 4, 3, 1);
        addEntryToIntegerMap(nodeToEdges, 4, 5, 1);
        addEntryToIntegerMap(nodeToEdges, 5, 1, 9);
        addEntryToIntegerMap(nodeToEdges, 5, 3, 8.8);

        System.out.println(nodeToEdges.size());
        WeightedGraph<Integer> g1 = new WeightedGraph<>(nodeToEdges);
        System.out.println(g1);
        System.out.println(g1.bfs(1, 5));
        System.out.println(g1.dfs(1, 5));
        System.out.println(g1.bfs(5, 3));
        System.out.println(g1.dfs(5, 3));
    }

    public static void addEntryToIntegerMap(Map<Integer, Set<WeightedEdge<Integer>>> nodeToEdges, int start, int end, double weight) {
        WeightedEdge<Integer> edge = new WeightedEdge<>(start, end, weight);

        if (nodeToEdges.keySet().contains(start)) {
            nodeToEdges.get(start).add(edge);
        } else {
            Set<WeightedEdge<Integer>> edgesSet = new HashSet<>();
            edgesSet.add(edge);
            nodeToEdges.put(start, edgesSet);
        }
    }
}
