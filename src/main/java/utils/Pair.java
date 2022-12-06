package utils;

public class Pair<F, S> {
    public F first;
    public S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public boolean compareWith(Pair<F, S> anotherPair) {
        return this.first == anotherPair.first && this.second == anotherPair.second;
    }

    public Pair<F, S> copy() {
        return new Pair<>(first, second);
    }


}
