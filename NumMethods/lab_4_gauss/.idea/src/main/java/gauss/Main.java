package main.java.gauss;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int DEFAULT_EQUATIONS_NUMBER = 3;
    private static final int DEFAULT_VARIABLES_NUMBER = 3;

    public Main() {
    }

    public static void main(String[] args) {
        LinearSystem<Float, MyEquation> list = generateSystem();
        System.out.println("Input: ");
        printSystem(list);
        Algorithm alg = new Algorithm(list);

        try {
            alg.calculate();
        } catch (NullPointerException var7) {
            System.out.println(var7.getMessage());
            System.exit(0);
        } catch (ArithmeticException var8) {
            System.out.println(var8.getMessage());
            System.exit(0);
        }

        Float[] x = new Float[3];

        for(int i = list.size() - 1; i >= 0; --i) {
            Float sum = 0.0F;

            int j;
            for(j = list.size() - 1; j > i; --j) {
                sum = sum + (Float)list.itemAt(i, j) * x[j];
            }

            x[i] = ((Float)list.itemAt(i, list.size()) - sum) / (Float)list.itemAt(i, j);
        }

        System.out.println("Results:");
        printVector(x);
    }

    public static LinearSystem<Float, MyEquation> generateSystem() {
        LinearSystem<Float, MyEquation> list = new LinearSystem();
        MyEquation firstEq = new MyEquation();
        List<Float> first = new ArrayList();
        first.add(0.65F);
        first.add(-0.93F);
        first.add(0.45F);
        first.add(-0.72F);
        firstEq.setEquation(first);
        list.push(firstEq);
        MyEquation secondEq = new MyEquation();
        List<Float> second = new ArrayList();
        second.add(1.15F);
        second.add(0.43F);
        second.add(-0.72F);
        second.add(1.24F);
        secondEq.setEquation(second);
        list.push(secondEq);
        MyEquation thirdEq = new MyEquation();
        List<Float> third = new ArrayList();
        third.add(0.56F);
        third.add(-0.18F);
        third.add(1.03F);
        third.add(2.15F);
        thirdEq.setEquation(third);
        list.push(thirdEq);
        return list;
    }

    public static void printSystem(LinearSystem<Float, MyEquation> system) {
        for(int i = 0; i < system.size(); ++i) {
            MyEquation temp = (MyEquation)system.get(i);
            String s = "";

            for(int j = 0; j < temp.size(); ++j) {
                s = s + String.format("%f; %s", system.itemAt(i, j), "\t");
            }

            System.out.println(s);
        }

        System.out.println("");
    }

    public static void printVector(Float[] x) {
        String s = "";

        for(int i = 0; i < x.length; ++i) {
            s = s + String.format("x%d = %f; ", i, x[i]);
        }

        System.out.println(s);
    }
}
