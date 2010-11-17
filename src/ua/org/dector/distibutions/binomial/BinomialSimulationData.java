package ua.org.dector.distibutions.binomial;

/**
 * @author dector
 * @version 17.11.10 3:40
 */
public class BinomialSimulationData {
    int[] result;

    /**
     * Success probability for <b>m</b> trials one time (m = 0,1,2,...)
     */
    double[] pm;

    BinomialSimulationData(int n) {
        result = new int[n+1];
    }
}
