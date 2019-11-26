import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static void main(String[] args) {
        double a[][] = new double[3][3];
        double l[][] = new double[3][3];
        double b[][] = new double[3][1];

//        b[0][0] = 4;
//        b[1][0] = -6;
//        b[2][0] = 7;


        b[0][0] = -0.72;
        b[1][0] = 1.24;
        b[2][0] = 2.15;

//        a[0][0] = 1;
//        a[0][1] = 4;
//        a[0][2] = -3;
//
//        a[1][0] = -2;
//        a[1][1] = 8;
//        a[1][2] = 5;
//
//        a[2][0] = 3;
//        a[2][1] = 4;
//        a[2][2] = 7;


//        a[0][0] = 1;
//        a[0][1] = 1;
//        a[0][2] = -1;
//
//        a[1][0] = 1;
//        a[1][1] = -2;
//        a[1][2] = 3;
//
//        a[2][0] = 2;
//        a[2][1] = 3;
//        a[2][2] = 1;



        a[0][0] = 0.65;
        a[0][1] = -0.93;
        a[0][2] = 0.45;

        a[1][0] = 1.15;
        a[1][1] = 0.43;
        a[1][2] = -0.72;

        a[2][0] = 0.56;
        a[2][1] = -0.18;
        a[2][2] = 1.03;

        double aCopy[][] = new double[3][3];
        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j++){
                aCopy[i][j] = a[i][j];
            }
        }
        System.out.println("Matrix A:");
        printMatix(a);

        System.out.println("L template:");
        fillTemplateL(l);
        printMatix(l);
        System.out.println();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (i == 1 && j == 0) {
                    perform(a, i, j, l);
                }

                if (i == 2 && j == 0) {
                    perform(a, i, j, l);
                }

                if (i == 2 && j == 1) {
                    perform(a, i, j, l);
                }
            }
        }

        System.out.println();

        double [][] u = a;
        a = aCopy;

        System.out.println("A: ");
        printMatix(a);

        System.out.println("U: ");
        printMatix(u);

        System.out.println("L: ");
        printMatix(l);


        System.out.println("-------------------- Part 2: -------------------- ");
        System.out.println("1) LY = B. B:");
        System.out.println(b[0][0]);
        System.out.println(b[1][0]);
        System.out.println(b[2][0]);

        System.out.println();
        System.out.println(l[0][0] + " * y1 = " + b[0][0]);
        double y1 = b[0][0] / l[0][0];

        System.out.println(l[1][0] + " * y1 + " + l[1][1] + " y2 = " + b[1][0]);
        double y2 = (b[1][0] - (l[1][0] * y1)) / l[1][1];

        System.out.println(l[2][0] + " y1 + " + l[2][1] + " y2 " + l[2][2] + " y3 = " + b[2][0]);
        double y3 = (b[2][0] - (l[2][0] * y1) - (l[2][1] * y2)) / l[2][2];

        System.out.println("Y1 = " + y1);
        System.out.println("Y2 = " + y2);
        System.out.println("Y3 = " + y3);

        System.out.println();
        System.out.println("2) UX = Y");

        double x3 = u[2][2] / y3;
        double x2 = (y2 - u[1][2] * x3) / u[1][1];
        double x1 = (y1 - u[0][2] * x3 - u[0][1] * x2) / u[0][0];
        System.out.println("X3 = " + x3);
        System.out.println("X2 = " + x2);
        System.out.println("X1 = " + x1);

    }

    static void perform(double a[][], int i, int j, double l [][]){
        double thisNum;
        double anotherNum;
        double coefficient;

        thisNum = a[i][j];
        anotherNum = a[j][j];

        System.out.println(" --- Step " + (i + j) + ": Trying to get 0 at A[" + i + ", " + j + "]: " + thisNum + " --- ");
        coefficient = findCoefficient(anotherNum, thisNum);
        System.out.println("Finding num to multiply ROW_1. This: " + thisNum + ", Another: " + anotherNum + ", Coefficient: " + coefficient);
        System.out.println("Action: " + coefficient + " R1 + R2");

        List<Double> anotherRow = new ArrayList<>();
        anotherRow.add(a[j][0]);
        anotherRow.add(a[j][1]);
        anotherRow.add(a[j][2]);
        System.out.println("Another row: " + anotherRow);
        multiplyRowByCoedficient(anotherRow, coefficient);
        System.out.println("Multiplied anotherRow: " + anotherRow);
        List<Double> thisRow = new ArrayList<>();
        thisRow.add(a[i][0]);
        thisRow.add(a[i][1]);
        thisRow.add(a[i][2]);
        System.out.println("Adding this Row: " + thisRow);

        List<Double> resultingRow = addRows(thisRow, anotherRow);

        System.out.println("Resulting row: " + resultingRow);
        System.out.println();

        System.out.println("Replacing matrix with resulting row: " + resultingRow);

        a[i][0] = resultingRow.get(0);
        a[i][1] = resultingRow.get(1);
        a[i][2] = resultingRow.get(2);

        System.out.println("A [replaced row] matrix");
        printMatix(a);

        coefficient = coefficient * -1;
        System.out.println("Storing (-) coefficient in L matrix: " + coefficient);
        l[i][j] = coefficient;
        printMatix(l);
    }

    static List<Double> addRows(List<Double> anotherRow, List<Double> thisRow){
        List<Double> result = new ArrayList<>();
        result.add(anotherRow.get(0) + thisRow.get(0));
        result.add(anotherRow.get(1) + thisRow.get(1));
        result.add(anotherRow.get(2) + thisRow.get(2));
        return result;
    }

    static void multiplyRowByCoedficient(List<Double> row, double coefficient){
        for (int i = 0; i < row.size(); i++){
            double multiplied = row.get(i) * coefficient;
            row.set(i, multiplied);
        }
    }

    public static double findCoefficient(double anotherNum, double thisNum) {
        if (anotherNum == 0.0f) {
            return 1.0f;
        }
        else {
            return -thisNum / anotherNum;
        }
    }

    static void fillTemplateL(double[][] l){
        l[0][0] = 1;
        l[1][1] = 1;
        l[2][2] = 1;
    }

    static void printMatix(double m[][]){
        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j++) {
                System.out.print(df2.format(m[i][j]));
                System.out.print("        ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
