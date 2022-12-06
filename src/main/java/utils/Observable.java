package utils;

import java.util.ArrayList;

public class Observable<V> {

    private V value;
    private final ArrayList<OnValueChangedListener<V>> listeners;

    public Observable(V initValue) {
        this.value = initValue;
        listeners = new ArrayList<>();
    }

    public void setValue(V value) {
        if (this.value == value) return;
        this.value = value;
        listeners.forEach(listener -> listener.onValueChanged(value));
    }

    public V getValue() {
        return value;
    }

    public void observe(OnValueChangedListener<V> listener) {
        listeners.add(listener);
        listener.onValueChanged(value);
    }

    public interface OnValueChangedListener<V> {
        void onValueChanged(V value);
    }

}
