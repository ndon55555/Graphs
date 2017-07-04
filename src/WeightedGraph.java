import com.sun.xml.internal.bind.v2.TODO;

import java.util.*;

/**
 * Created by Don on 7/3/2017.
 */
public class WeightedGraph<T> implements Graph<T> {
    private int size;
    private Map<T, Set<WeightedEdge<T>>> nodeToEdges;

    ////////////////
    /*Constructors*/
    ////////////////

    public WeightedGraph() {
        nodeToEdges = new HashMap<>();
        size = 0;
    }

    public WeightedGraph(WeightedGraph<T> that) {
        this(that.getMapping());
    }

    public WeightedGraph(Map<T, Set<WeightedEdge<T>>> nodeToEdges) {
        this();
        add(nodeToEdges);
    }

    ////////////
    /*Mutators*/
    ////////////

    public void add(T newNode, Set<WeightedEdge<T>> edges) {
        if (newNode == null) {
            throw new IllegalArgumentException("Node is null");
        }

        Set<T> curNodes = getNodes();

        if (curNodes.contains(newNode)) {
            nodeToEdges.get(newNode).addAll(edges);
        } else {
            nodeToEdges.put(newNode, edges);
            size++;
        }

        for (WeightedEdge<T> edge : edges) {
            T neighbor = edge.getDestination();

            if (!curNodes.contains(neighbor)) {
                nodeToEdges.put(neighbor, new HashSet<>());
                size++;
            }
        }
    }

    public void add(WeightedGraph<T> that) {
        add(that.getMapping());
    }

    public void add(Map<T, Set<WeightedEdge<T>>> thatNodeToEdges) {
        for (T thatNode : thatNodeToEdges.keySet()) {
            Set<WeightedEdge<T>> thatEdges = thatNodeToEdges.get(thatNode);
            add(thatNode, thatEdges);
        }
    }

    @Override
    public void remove(T nodeToRemove) {
        if (nodeToRemove == null) {
            throw new IllegalArgumentException("Node is null");
        }

        nodeToEdges.remove(nodeToRemove);
        size--;

        for (T node : getNodes()) {
            nodeToEdges.get(node).remove(nodeToRemove);
        }
    }

    /////////////
    /*Accessors*/
    /////////////

    public Map<T, Set<WeightedEdge<T>>> getMapping() {
        return nodeToEdges;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Set<T> getNodes() {
        return nodeToEdges.keySet();
    }

    @Override
    public List<T> bfs(T start, T end) {
        return search(start, end, true);
    }

    @Override
    public List<T> dfs(T start, T end) {
        return search(start, end, false);
    }

    private List<T> search(T start, T end, boolean isBreadthFirst) {
        LinkedList<T> nextNodes = new LinkedList<>();
        Map<T, T> childToParent = new HashMap<>();
        Map<T, Double> childToTotalWeightFromStart = new HashMap<>();
        nextNodes.add(start);
        childToParent.put(start, null);
        childToTotalWeightFromStart.put(start, 0.0);

        while (!nextNodes.isEmpty()) {
            T node;

            if (isBreadthFirst) {
                node = nextNodes.removeFirst();
            } else { //is depth first
                node = nextNodes.removeLast();
            }

            if (node == end) {
                continue;
            }

            T parent = childToParent.get(node);
            Set<WeightedEdge<T>> edges = nodeToEdges.get(node);

            for (WeightedEdge<T> edge : edges) {
                T neighbor = edge.getDestination();
                double potentialTotalWeight = childToTotalWeightFromStart.get(node) + edge.getWeight();

                if (neighbor != parent
                        && (!childToParent.keySet().contains(neighbor) || potentialTotalWeight < childToTotalWeightFromStart.get(neighbor))) {
                    childToParent.put(neighbor, node);
                    childToTotalWeightFromStart.put(neighbor, potentialTotalWeight);
                    nextNodes.add(neighbor);
                }
            }
        }

        if (!childToParent.keySet().contains(end)) {
            return null;
        }

        List<T> path = new LinkedList<>();
        T curNode = end;

        while (curNode != null) {
            path.add(0, curNode);
            curNode = childToParent.get(curNode);
        }

        return path;
    }

    @Override
    public String toString() {
        String s = "";
        Set<T> nodes = getNodes();

        for (T node : nodes) {
            s += node.toString() + " -> " + nodeToEdges.get(node).toString() + "\n";
        }

        return s;
    }
}