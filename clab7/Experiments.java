import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        List<Integer> xValues = new ArrayList<>();
        List<Double> y1Values = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        BST<Integer> b = new BST<>();
        int N = 5000;
        for (int i = 1; i <= N; i++) {
            b.add(RandomGenerator.getRandomInt(10 * N));
            xValues.add(i);
            y1Values.add(b.averageDepth());
            y2Values.add(ExperimentHelper.optimalAverageDepth(i));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("random", xValues, y1Values);
        chart.addSeries("optimal", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        int N = 5000;
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < N; i++) {
            bst.add(RandomGenerator.getRandomInt(10 * N));
        }
        for (int i = 0; i < N; i++) {
            bst.deleteTakingSuccessor();
        }
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();
    }
}
