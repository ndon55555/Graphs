import java.util.List;
import java.util.Set;

/**
 * Created by Don on 7/3/2017.
 */
public interface Graph<T> {
    void add(T node, Set<T> neighbors);
    void remove(T node);
    List<T> bfs(T start, T end);
    List<T> dfs(T start, T end);
    Set<T> getNodes();
    int getSize();
}
