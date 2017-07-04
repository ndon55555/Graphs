import java.util.*;

/**
 * Created by Don on 7/3/2017.
 */
public class UnweightedGraphTest {
    public static void main(String[] args) {
        Map<Integer, Set<Integer>> nodeToNeighbors1 = new HashMap<>();
        addEntryToIntegerMap(nodeToNeighbors1, 1, 2, 5, 6, 7, 8, 0);
        addEntryToIntegerMap(nodeToNeighbors1, 2, 4, 5, 7, 1, 9);
        addEntryToIntegerMap(nodeToNeighbors1, 3, 1, 2, 9);
        addEntryToIntegerMap(nodeToNeighbors1, 4, 3, 2, 1, 8);
        addEntryToIntegerMap(nodeToNeighbors1, 5, 3, 6, 7, 9);
        addEntryToIntegerMap(nodeToNeighbors1, 6, 3, 1, 7);
        addEntryToIntegerMap(nodeToNeighbors1, 7, 5, 6, 7);
        addEntryToIntegerMap(nodeToNeighbors1, 8, 2);
        addEntryToIntegerMap(nodeToNeighbors1, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        UnweightedGraph<Integer> g1 = new UnweightedGraph<>(nodeToNeighbors1);
        System.out.println(g1);
        System.out.println("size: " + g1.getSize());
        System.out.println(g1.bfs(8, 3));
        System.out.println(g1.bfs(10, 0));
        System.out.println(g1.bfs(0, 9));
        System.out.println(g1.dfs(8, 3));
        System.out.println();

        Map<Integer, Set<Integer>> nodeToNeighbors2 = new HashMap<>();
        addEntryToIntegerMap(nodeToNeighbors2, 1, 21, 22);
        addEntryToIntegerMap(nodeToNeighbors2, 20, 21, 22);
        addEntryToIntegerMap(nodeToNeighbors2, 21, 25, 27);
        addEntryToIntegerMap(nodeToNeighbors2, 23, 25, 26);
        addEntryToIntegerMap(nodeToNeighbors2, 25, 20, 22, 26);
        addEntryToIntegerMap(nodeToNeighbors2, 27, 20, 21, 22, 23, 24, 25, 26, 1);
        addEntryToIntegerMap(nodeToNeighbors2, 28);

        UnweightedGraph<Integer> g2 = new UnweightedGraph<>(nodeToNeighbors2);
        System.out.println(g2);
        System.out.println("size: " + g2.getSize());
        System.out.println(g2.bfs(1, 25));
        System.out.println(g2.bfs(1, 26));
        System.out.println(g2.dfs(1, 26));
        System.out.println();

        UnweightedGraph<Integer> g3 = new UnweightedGraph<>(g1);
        g3.add(g2);
        System.out.println(g3);
        g3.remove(1);
        System.out.println(g3);
    }

    public static void addEntryToIntegerMap(Map<Integer, Set<Integer>> nodeToNeighbors, int node, int... neighbors) {
        Set<Integer> neighborsSet = new HashSet<>();

        for (int neighbor : neighbors) {
            neighborsSet.add(neighbor);
        }

        nodeToNeighbors.put(node, neighborsSet);
    }
}
