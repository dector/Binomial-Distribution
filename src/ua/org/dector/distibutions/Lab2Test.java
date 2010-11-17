package ua.org.dector.distibutions;

import ua.org.dector.distibutions.binomial.Binomial;

import java.io.*;

import static ua.org.dector.distibutions.Config.SELECTION_LENGTH;

/**
 * @author dector
 * @version 15.11.10 23:21
 */
public class Lab2Test {

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out), true);

        int n = 0;
        double p = 0;
        boolean correct = false;
        boolean print = false;

        while (!correct) {
            out.println("Enter number of elements");
            correct = true;

            try {
                n = Integer.parseInt(in.readLine());
            } catch (NumberFormatException e) {
                correct = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        correct = false;
        while (!correct) {
            correct = true;
            out.println("Enter P");

            try {
                p = Double.parseDouble(in.readLine());
            } catch (NumberFormatException e) {
                correct = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Binomial binomial = new Binomial(n, p);
        int[] binomialSet = binomial.getSimulation(SELECTION_LENGTH);

//        IF P(A<=x<=B)
//        int left = 18;
//        int right = n;
//        System.out.println("P(" + left + "<=x<=" + right + ") = " + binomial.getToucheProbability(left, right, SELECTION_LENGTH));

        out.println();
        binomial.printParams();

        out.println("Show chart? y/n");
        try {
            if (in.readLine().equals("y")) {
                print = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (print) {
            for (int i = 0; i <= n; i++) {
                out.println(i + " -> " + binomialSet[i]);
//                out.println(i + "," + binomialSet[i]);
            }

            double[] values = new double[SELECTION_LENGTH];

            int k = 0;
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j < binomialSet[i]; j++) {
                    values[k] = i;
                    k++;
                }
            }

            Chart chart = new Chart(n, values);
            chart.display();
        }

    }
}
