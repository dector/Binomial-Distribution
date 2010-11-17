package ua.org.dector.distributions;

/**
 * Probability mass function interface
 *
 * @author dector
 * @version 17.11.10 3:03
 */
public interface Distribution {
    public double getMean();
    public double getVariance();
    public double getDeviation();
    public double getDencity(double value);
    public String toString();
}
