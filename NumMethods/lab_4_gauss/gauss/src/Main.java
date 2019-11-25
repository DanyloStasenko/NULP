import gauss.Algorithm;
import gauss.MyEquation;
import gauss.LinearSystem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int DEFAULT_EQUATIONS_NUMBER = 3;
    private static final int DEFAULT_VARIABLES_NUMBER = 3;

    public static void main(String args[]){
        LinearSystem<Float, MyEquation> list = generateSystem();
        System.out.println("Input: ");
        printSystem(list);
        int i, j;
        Algorithm<Float, MyEquation> alg = new Algorithm<Float, MyEquation>(list);
        try{
            alg.calculate();
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        Float [] x = new Float[DEFAULT_EQUATIONS_NUMBER];
        for(i = list.size() - 1; i >= 0; i--) {
            Float sum = 0.0f;
            for(j = list.size() - 1; j > i; j--) {
                sum += list.itemAt(i, j) * x[j];
            }
            x[i] = (list.itemAt(i, list.size()) - sum) / list.itemAt(i, j);
        }
        // System.out.println("Equivalent matrix:");
        // printSystem(list);

        System.out.println("Results:");
        printVector(x);
    }

    public static LinearSystem<Float, MyEquation> generateSystem(){
        LinearSystem<Float, MyEquation> list = new LinearSystem<Float, MyEquation>();

        // generate Randomly
//        int i;
//        for (i = 0; i < DEFAULT_EQUATIONS_NUMBER; i++){
//            MyEquation eq = new MyEquation();
//            eq.generate(DEFAULT_VARIABLES_NUMBER + 1);
//            list.push(eq);
//            System.out.println("Generating: " + eq);
//        }


        // Manual insert
        // first row
        MyEquation firstEq = new MyEquation();
        List<Float> first = new ArrayList<>();
        first.add(0.65f);
        first.add(-0.93f);
        first.add(0.45f);
        first.add(-0.72f);
        firstEq.setEquation(first);
        list.push(firstEq);

        // second row
        MyEquation secondEq = new MyEquation();
        List<Float> second = new ArrayList<>();
        second.add(1.15f);
        second.add(0.43f);
        second.add(-0.72f);
        second.add(1.24f);
        secondEq.setEquation(second);
        list.push(secondEq);

        // third row
        MyEquation thirdEq = new MyEquation();
        List<Float> third = new ArrayList<>();
        third.add(0.56f);
        third.add(-0.18f);
        third.add(1.03f);
        third.add(2.15f);
        thirdEq.setEquation(third);
        list.push(thirdEq);

        return list;
    }

    public static void printSystem(LinearSystem<Float, MyEquation> system){
        for (int i = 0; i < system.size(); i++){
            MyEquation temp = system.get(i);
            String s = "";
            for (int j = 0; j < temp.size(); j++){
                s += String.format("%f; %s", system.itemAt(i, j), "\t");
            }
            System.out.println(s);
        }System.out.println("");
    }

    public static void printVector(Float [] x){
        String s = "";
        for (int i = 0; i < x.length; i++){
            s += String.format("x%d = %f; ", i, x[i]);
        }System.out.println(s);
    }
}
