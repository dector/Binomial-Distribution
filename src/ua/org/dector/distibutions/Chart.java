package ua.org.dector.distibutions;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * It is only demonstation
 *
 * @author dector
 * @version 16.11.10 0:37
 */
public class Chart extends ApplicationFrame {
    private JFreeChart chart;

    public Chart(double n, double[] values) {
        super("Binomial Distribution");

        String title = "";
        String xAxisLabel = "Value";
        String yAxisLabel = "Number";

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("", values, values.length, 0, n);
        PlotOrientation orientation = PlotOrientation.VERTICAL;

        boolean legend = false;
        boolean tooltips = false;
        boolean urls = false;

        chart = ChartFactory.createHistogram(title, xAxisLabel, yAxisLabel,
                dataset, orientation, legend, tooltips, urls);

        XYPlot localXYPlot = (XYPlot)chart.getPlot();

        NumberAxis numberAxis = (NumberAxis)localXYPlot.getDomainAxis();
        numberAxis.setAutoRangeIncludesZero(false);
        numberAxis.setNegativeArrowVisible(false);
//        numberAxis.setVisible(false);

        XYBarRenderer barRenderer = (XYBarRenderer)localXYPlot.getRenderer();
        barRenderer.setMargin(0.5);
        barRenderer.setBarAlignmentFactor(0);
        barRenderer.setShadowVisible(false);
        barRenderer.setBarPainter(new StandardXYBarPainter());

        JPanel localJPanel = createPanel();
        localJPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(localJPanel);
    }

    public JPanel createPanel () {
        ChartPanel localChartPanel = new ChartPanel(chart);
        localChartPanel.setMouseWheelEnabled(true);
        return localChartPanel;
    }

    public void display() {
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }
}
