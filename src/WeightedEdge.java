/**
 * Created by Don on 7/3/2017.
 */
public class WeightedEdge<T> implements Comparable<WeightedEdge<T>> {
    public static final int SIGNIFICANT_DECIMAL_PLACES = 2;
    private T u;
    private T v;
    private double weight;

    ///////////////
    /*Constructor*/
    ///////////////

    public WeightedEdge(T u, T v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    /////////////
    /*Accessors*/
    /////////////

    public T getOrigin() {
        return u;
    }

    public T getDestination() {
        return v;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(WeightedEdge<T> that) {
        return (int) (Math.pow(10, SIGNIFICANT_DECIMAL_PLACES) * (this.weight - that.weight));
    }

    @Override
    public String toString() {
        return "[" + u + "," + v + ":" + weight + "]";
    }
}
