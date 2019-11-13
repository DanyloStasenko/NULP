//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gauss;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class MyEquation implements Gauss<Float, MyEquation> {
    private List<Float> equation = new ArrayList();

    public MyEquation() {
    }

    public void setEquation(List<Float> equation) {
        this.equation = equation;
    }

    public List<Float> getEquation() {
        return this.equation;
    }

    public void generate(int size) {
        if (size < 2) {
            size = 2;
        }

        this.equation.clear();

        for(int i = 0; i < size; ++i) {
            Random random = new Random();
            this.equation.add((float)(random.nextInt() % 10) + 1.0F);
        }

    }

    public int size() {
        return this.equation.size();
    }

    public void addEquation(MyEquation item) {
        ListIterator<Float> i = this.equation.listIterator();
        ListIterator j = item.getEquation().listIterator();

        while(i.hasNext() && j.hasNext()) {
            Float a = (Float)i.next();
            Float b = (Float)j.next();
            i.set(a + b);
        }

    }

    public void mul(Float coefficient) {
        ListIterator i = this.equation.listIterator();

        while(i.hasNext()) {
            Float next = (Float)i.next();
            i.set(next * coefficient);
        }

    }

    public Float findCoefficient(Float a, Float b) {
        return a == 0.0F ? 1.0F : -b / a;
    }

    public Float at(int index) {
        return (Float)this.equation.get(index);
    }

    public String toString() {
        return this.equation.toString();
    }
}
