package gauss;

public class Algorithm<N extends Number, T extends Gauss<N, T>> {
    LinearSystem<N, T> list = null;
    public Algorithm(LinearSystem<N, T> system){
            list = system;
    }

    public void calculate() throws NullPointerException, ArithmeticException{
        if (list == null){
            throw new NullPointerException("LinearSystem<N, T> instance equal null");
        }
        if (!checkSystem(list)){
            throw new ArithmeticException("Incorrect system for Gauss Method");
        }

        for(int i = 0; i < list.size() - 1; i++){
            for(int j = i + 1; j < list.size(); j++){
                N k = list.get(j).findCoefficient(list.get(j).at(i), list.get(i).at(i));

                System.out.println("Step " + (i+1) + "." + j + ", K = " + k);
                System.out.println("Working with " + list.get(j));
                list.get(j).mul(k);
                System.out.println("+ Multiplied:" + list.get(j));
                list.get(j).addEquation(list.get(i));
                System.out.println("+ Adding: " + list.get(i));
                System.out.println("= Res: " + list.get(j));
                System.out.println();


                for (int iter = 0; iter < list.size(); iter++){
                    System.out.println("\t " + list.get(iter));
                }
                System.out.println();
            }
        }
    }

    private boolean checkSystem(LinearSystem<N, T> system){
        if (system.size() < 2) return false;
        for(int i = 0; i < system.size(); i++){
            if (system.get(i).size() != (system.size() + 1)){
                return false;
            }
        }
        return true;
    }
}
