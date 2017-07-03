import java.util.*;

/**
 * Created by Don on 7/3/2017.
 */
public class UnweightedGraph<T> implements Graph<T> {
    private int size;
    private Map<T, Set<T>> nodeToNeighbors;

    ////////////////
    /*Constructors*/
    ////////////////

    public UnweightedGraph() {
        nodeToNeighbors = new HashMap<>();
        size = 0;
    }

    public UnweightedGraph(UnweightedGraph<T> that) {
        this(that.getMapping());
    }

    public UnweightedGraph(Map<T, Set<T>> nodeToNeighbors) {
        this();
        add(nodeToNeighbors);
    }

    ////////////
    /*Mutators*/
    ////////////

    @Override
    public void add(T node, Set<T> neighbors) {
        if (node == null) {
            throw new IllegalArgumentException("Node is null");
        }

        nodeToNeighbors.put(node, neighbors);
        size++;
    }

    public void add(UnweightedGraph<T> that) {
        add(that.getMapping());
    }

    public void add(Map<T, Set<T>> thatNodeToNeighbors) {
        for (T thatNode : thatNodeToNeighbors.keySet()) {
            Set<T> nodes = getNodes();
            Set<T> thatNeighbors = thatNodeToNeighbors.get(thatNode);

            if (nodes.contains(thatNode)) {
                nodeToNeighbors.get(thatNode).addAll(thatNeighbors);
            } else {
                nodeToNeighbors.put(thatNode, thatNeighbors);
                size++;
            }

            for (T thatNeighbor : thatNeighbors) {
                if (!nodes.contains(thatNeighbor)) {
                    nodeToNeighbors.put(thatNeighbor, new HashSet<>());
                    size++;
                }
            }
        }
    }

    @Override
    public void remove(T nodeToRemove) {
        if (nodeToRemove == null) {
            throw new IllegalArgumentException("Node is null");
        }

        nodeToNeighbors.remove(nodeToRemove);
        size--;

        for (T node : getNodes()) {
            nodeToNeighbors.get(node).remove(nodeToRemove);
        }
    }

    /////////////
    /*Accessors*/
    /////////////

    public Map<T, Set<T>> getMapping() {
        return nodeToNeighbors;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Set<T> getNodes() {
        return nodeToNeighbors.keySet();
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
        nextNodes.add(start);
        childToParent.put(start, null);

        while (!nextNodes.isEmpty()) {
            T node;

            if (isBreadthFirst) {
                node = nextNodes.removeFirst();
            } else { //is depth first
                node = nextNodes.removeLast();
            }

            if (node == end) {
                break;
            }

            T parent = childToParent.get(node);
            Set<T> neighbors = nodeToNeighbors.get(node);

            for (T neighbor : neighbors) {
                if (neighbor != parent && !childToParent.keySet().contains(neighbor)) {
                    childToParent.put(neighbor, node);
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
            s += node.toString() + " -> " + nodeToNeighbors.get(node).toString() + "\n";
        }

        return s;
    }
}