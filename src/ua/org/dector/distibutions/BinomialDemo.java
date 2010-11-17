package ua.org.dector.distibutions;

import ua.org.dector.distibutions.binomial.Binomial;

import java.io.*;

import static ua.org.dector.distibutions.Config.SELECTION_LENGTH;

/**
 * @author dector
 * @version 15.11.10 23:21
 */
public class BinomialDemo {

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out), true);

        // Init variables
        int n = 0;
        double p = 0;
        boolean correct = false;
        boolean print = false;

        // Secure input
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

        // Initialization of new distriution
        Binomial binomial = new Binomial(n, p);
        int[] binomialSet = binomial.getSimulation(SELECTION_LENGTH);

        // Printing distribution params
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
            // Print number of each value
            for (int i = 0; i <= n; i++) {
                out.println(i + " -> " + binomialSet[i]);
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
