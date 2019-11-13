//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gauss;

public interface Gauss<N extends Number, T extends Gauss<N, T>> {
    void addEquation(T var1);

    void mul(N var1);

    N findCoefficient(N var1, N var2);

    N at(int var1);

    int size();
}
