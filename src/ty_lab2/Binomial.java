package ty_lab2;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author dector
 * @version 15.11.10 22:34
 */
public class Binomial {
    private int n;
    private double p;
    private double[] pm;

    public Binomial(int n, double p) {
        if (n < 1)
            n = 1;
        if (p < 0)
            p = 0;
        else if (p > 1)
            p = 1;

        pm = new double[n+1];

        this.n = n;
        this.p = p;

        fill();
    }

    public double getDencity(int x) {
        if (x < 0 | x > n)
            return 0;
        if (p == 0){
            if (x == 0)
                return 1;
            else
                return 0;
        }
        else if (p == 1){
            if (x == n)
                return 1;
            else
                return 0;
        }
//        else if (x < 120) {
//            return comb(n, x) * Math.pow(p, x) * Math.pow(1 - p, n - x);
//        }
        else {  //Normal Approximation to Binomial density
            if (x>=0 && x<=n) {
                double c = x - n*p;
                return Math.exp(- Math.pow(c, 2) / (2*n*p*(1-p))) / Math.sqrt(2*Math.PI*n*p*(1-p));
//                   NormalDistribution ND = new NormalDistribution(getMean(), getSD());
//                   return ND.getDensity(x);
            }
            else return 0;
        }
    }

    public double comb(int n, int m) {
//        if (n == m | m == 0)
//            return 1;
//        else {
            return (pFact(m+1, n).divide(pFact(1, n-m))).doubleValue();
//        }
    }

    public BigInteger pFact(int first, int second) {
        BigInteger sum = BigInteger.ONE;
        if (first == 1)
            first = 2;

        for (int i = first; i <= second; i++) {
            sum = sum.multiply(BigInteger.valueOf(i));
        }

        return sum;
    }

    public void fill() {
        for (int i = 0; i <= n; i++) {
            pm[i] = getDencity(i);
        }
    }

//    public Map<Integer, Integer> getBinomial(int trials) {
//        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
//
//        for (int i = 0; i < n; i++) {
//            result.put(i+1, 0);
//        }
//
//        int Rj;
//        double rj;
//        double sum;
//        Random random = new Random();
//
//        for (int i = 0; i < trials; i++) {
//            rj = random.nextDouble();
//            Rj = 0;
//            sum = 0;
//            while (Rj < n-1 && pm[Rj+1] != 0 && sum < rj) {
//                Rj++;
//                sum += pm[Rj-1];
//            }
//
//            if (Rj >= 0) {
//                result.put(Rj, result.get(Rj) + 1);
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            System.out.println("P[" + (i+1) + "] = " + pm[i]);
//        }
//
//        return result;
//    }

    public int[] getSimulation(int trials) {
        int[] result = new int[n+1];

        int Rj;
        double rj;
        double sum;
        Random random = new Random();

        for (int i = 0; i < trials; i++) {
            rj = random.nextDouble();
            Rj = -1;
            sum = 0;
            while (sum < rj & Rj < n) {
                Rj++;
                sum += pm[Rj];
            }

            if (Rj >= 0)
                result[Rj]++;
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println("P[" + (i+1) + "] = " + pm[i]);
//        }

        return result;
    }

    public void printParams() {
        System.out.println();
        System.out.println("mx = " + getMx());
        System.out.println("D = " + getD());
        System.out.println("r = " + getSig());
    }

    public double getMx() {
        return n*p;
    }

    public double getD() {
        return n*p*(1-p);
    }

    public double getSig() {
        return Math.sqrt(getD());
    }

    public double countP(int xFrom, int xTo, int trials) {
        int[] binSet = getSimulation(trials);
        int sum = 0;

        for (int i = 0; i <= n; i++) {
            if (i >= xFrom & i <= xTo) {
                sum += binSet[i];
            }
        }

        return ((double)sum)/trials;
    }
}
