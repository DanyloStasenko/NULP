//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gauss;

import java.util.ArrayList;
import java.util.List;

public class LinearSystem<N extends Number, T extends Gauss<N, T>> {
    private List<T> list = new ArrayList();

    public LinearSystem() {
    }

    public T get(int index) {
        return (Gauss)this.list.get(index);
    }

    public void push(T elem) {
        this.list.add(elem);
    }

    public int size() {
        return this.list.size();
    }

    public N itemAt(int i, int j) {
        return ((Gauss)this.list.get(i)).at(j);
    }
}
