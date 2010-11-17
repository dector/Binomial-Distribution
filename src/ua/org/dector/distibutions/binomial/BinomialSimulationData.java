package ua.org.dector.distibutions.binomial;

/**
 * @author dector
 * @version 17.11.10 3:40
 */
public class BinomialSimulationData {
    /**
     * Array for simulation results
     */
    int[] result;

    /**
     * Success probability for <b>m</b> trials one time (m = 0,1,2,...)
     */
    double[] pm;

    /**
     * Number of values
     */
    int length;

    BinomialSimulationData(int n) {
        length = n;
        pm = new double[n+1];
        result = new int[n+1];
    }
}
