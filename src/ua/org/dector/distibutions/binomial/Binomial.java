package ua.org.dector.distibutions.binomial;

import ua.org.dector.distibutions.Distribution;

import java.util.Random;

/**
 * Realization and simulation of binomial distribution
 *
 * @author dector
 * @version 15.11.10 22:34
 */
public class Binomial implements Distribution {
    /** Number of trials */
    private int n;

    /** Success probability of each trial */
    private double p;

    /** Failure probability of each trial */
    private double q;

    /** Data, stored during simulation */
    private BinomialSimulationData simData;

    /**
     * Init binomial distribution with defined params
     *
     * @param n number of trials
     * @param p success probability of each trial
     */
    public Binomial(int n, double p) {
        setTrials(n);
        setProbability(p);
    }

    /**
     * Set each trial success probability value
     *
     * @param p success probability of each trial
     */
    public void setProbability(double p) {
        if (p < 0) {
            p = 0;
        }
        else if (p > 1) {
            p = 1;
        }

        this.p = p;

        q = 1 - p;
    }

    /**
     * Set number of trials
     *
     * @param n number of trials
     */
    public void setTrials(int n) {
        if (n < 1) {
            n = 1;
        }

        this.n = n;
    }

    /**
     * Returns success dencity for m tries.<br>
     * <b>ATTENTION</b>: Uses only even value
     *
     * @param m tries value
     * @return success dencity
     */
    public double getDencity(double m) {
        return getDencity((int)Math.floor(m));
    }

    /**
     * Returns success dencity for m tries
     *
     * @param m number of tries
     * @return success dencity
     */
    public double getDencity(int m) {
        if (m < 0 | m > n) {
            return 0;
        }
        if (p == 0){
            return (m == 0) ? 1 : 0;
        }
        else if (p == 1){
            return (m == n) ? 1 : 0;
        }
        else {
            if (m >=0 && m <=n) {
                double x = m - n*p;
                double dVar = 2 * getVariance();
                return Math.exp(-Math.pow(x, 2) / dVar) / Math.sqrt(Math.PI*dVar);
            }
            else return 0;
        }
    }

    /**
     * Fill array of probability for <b>m</b> trials one time (m = 0,1,2,...)
     */
    private void fill() {
        if (simData != null) {
            for (int i = 0; i <= n; i++) {
                simData.pm[i] = getDencity(i);
            }
        }
    }

    /**
     * Returns result of experiment simulation
     *
     * @param trials how much times make experiments
     * @return array of numbers amount
     */
    public int[] getSimulation(int trials) {
        simulate(trials);
        return simData.result;
    }

    /**
     * Generate numbers, wich are distributed with binomial principles
     *
     * @param trials number of numbers
     * @param force <b>true</b> - start new simulation, <b>false</b> - using exist (if is any)
     */
    public void simulate(int trials, boolean force) {
        if (simData ==  null | force) {
            simData = new BinomialSimulationData(n);
            fill();

            int Rj;
            double rj;
            double sum;
            Random random = new Random();

            for (int i = 0; i < trials; i++) {
                rj = random.nextDouble();
                Rj = -1;
                sum = 0;

                while (sum < rj & Rj < n) {
                    sum += simData.pm[++Rj];
                }

                if (Rj >= 0)
                    simData.result[Rj]++;
            }
        }
    }

    /**
     * Generate numbers, wich are distributed with binomial principles,<br>
     * using existing experiment data (is exists any)
     *
     * @param trials number of numbers
     */
    public void simulate(int trials) {
        simulate(trials, false);
    }

    /**
     * Delete all data from previous experiment simulation
     */
    public void clear() {
        simData = null;
    }

    /**
     * Print params of binomial distribution<br>
     * (Mean, Variance, Deviation)
     */
    public void printParams() {
        System.out.println();
        System.out.println("mx = " + getMean());
        System.out.println("D = " + getVariance());
        System.out.println("r = " + getDeviation());
    }

    /**
     * Returns mean of binomial distribution
     *
     * @return mean
     */
    public double getMean() {
        return n*p;
    }

    /**
     * Returns variance of binomial distribution
     *
     * @return variance
     */
    public double getVariance() {
        return n*p*q;
    }

    /**
     * Returns deviation of binomial distribution
     *
     * @return deviation
     */
    public double getDeviation() {
        return Math.sqrt(getVariance());
    }

    /**
     * Returns probability, that random generated value
     * distributed by binomial principles will lie in
     * pointed bounds (including them)
     *
     * @param xFrom left bound
     * @param xTo right bound
     * @param trials number of numbers to generate in simulation
     * @return probability P (A<=x<=B)
     */
    public double getToucheProbability(int xFrom, int xTo, int trials) {
        int[] binSet = getSimulation(trials);
        int sum = 0;

        for (int i = 0; i <= n; i++) {
            if (i >= xFrom & i <= xTo) {
                sum += binSet[i];
            }
        }

        return (double)sum/trials;
    }

    /**
     * Returns information about distribution
     *
     * @return line with init information
     */
    public String toString() {
        return "Binomial distribution for N=" + n + " trials with probability P=" + p + " for each.";
    }
}
