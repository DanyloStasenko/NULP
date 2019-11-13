//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


public class Algorithm<N extends Number, T extends Gauss<N, T>> {
    LinearSystem<N, T> list = null;

    public Algorithm(LinearSystem<N, T> system) {
        this.list = system;
    }

    public void calculate() throws NullPointerException, ArithmeticException {
        if (this.list == null) {
            throw new NullPointerException("LinearSystem<N, T> instance equal null");
        } else if (!this.checkSystem(this.list)) {
            throw new ArithmeticException("Incorrect system for Gauss Method");
        } else {
            for(int i = 0; i < this.list.size() - 1; ++i) {
                for(int j = i + 1; j < this.list.size(); ++j) {
                    N k = this.list.get(j).findCoefficient(this.list.get(j).at(i), this.list.get(i).at(i));
                    System.out.println("Step " + (i + 1) + "." + j + ", K = " + k);
                    System.out.println("Working with " + this.list.get(j));
                    this.list.get(j).mul(k);
                    System.out.println("+ Multiplied:" + this.list.get(j));
                    this.list.get(j).addEquation(this.list.get(i));
                    System.out.println("+ Adding: " + this.list.get(i));
                    System.out.println("= Res: " + this.list.get(j));
                    System.out.println();

                    for(int iter = 0; iter < this.list.size(); ++iter) {
                        System.out.println("\t " + this.list.get(iter));
                    }

                    System.out.println();
                }
            }

        }
    }

    private boolean checkSystem(LinearSystem<N, T> system) {
        if (system.size() < 2) {
            return false;
        } else {
            for(int i = 0; i < system.size(); ++i) {
                if (system.get(i).size() != system.size() + 1) {
                    return false;
                }
            }

            return true;
        }
    }
}
