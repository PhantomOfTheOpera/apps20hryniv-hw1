package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    public void testAverage(double[]  temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test
    public void testAverage() {
    double[] temperatureSeries = {-20.0, 20.0, 10.0, -10.0};
    testAverage(temperatureSeries, 0);
    double[] temperatureSeries2 = {1.0, 1.0, 1.0, 1.0, 1.0};
    testAverage(temperatureSeries2, 1);
}

    public void testDeviation(double[] temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {10, 20, 30, 40, -50};
        testDeviation(temperatureSeries, 31.62277);

        double[] temperatureSeries2 = {1.0, 5.0, -4.5, 6.8, 10.1};
        testDeviation(temperatureSeries2, 5.03563);
    }

    public void testMin(double[] temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void IllegalLengthTest() {
        double[] arr = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(arr);
        seriesAnalysis.deviation();
    }
    @Test(expected = IllegalArgumentException.class)
    public void IllegalArgumentTest() {
        double[] arr = {100};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(arr);
        seriesAnalysis.deviation();
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {11, 12, 13, 14};
        testMin(temperatureSeries, 11);
        double[] temperatureSeries2 = {100.0, 100.0, 100.0, 100.0, 100.0};
        testMin(temperatureSeries2, 100.0);
        double[] temperatureSeries3 = {-44, 15.0, -100.0, 50.0, 80.0};
        testMin(temperatureSeries3, -100.0);
    }
    @Test(expected = InputMismatchException.class)
    public void TempBelowZero() {
        double[] arr = {-274};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(arr);
        seriesAnalysis.deviation();
    }
    public void testMax(double[] temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {11, 12, 13, 14};
        testMax(temperatureSeries, 14);
        double[] temperatureSeries2 = {100.0, 100.0, 100.0, 100.0, 100.0};
        testMax(temperatureSeries2, 100.0);
        double[] temperatureSeries3 = {-44, 15.0, -100.0, 50.0, 80.0};
        testMax(temperatureSeries3, 80.0);
        double[] temperatureSeries4 = {-44, -15.0, -100.0, -50.0, -80.0};
        testMax(temperatureSeries4, -15.0);
    }
    public void testClosestToValue(double[] temperatureSeries, double expResult, double value) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToValue(value);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testClosestToValue() {
        double[] temperatureSeries = {-100.0, 300, 0, 1.0, 2.5};
        testClosestToValue(temperatureSeries, 300, 1488);
        double[] temperatureSeries2 = {-210.0, -2.0, -1.0, -40.0, -50};
        testClosestToValue(temperatureSeries2, -210.0, -270.0);
        double[] temperatureSeries3 = {0.0, -2.0, -0.5, 1.0, 2.0, 100.0};
        testClosestToValue(temperatureSeries3, 0.0, 0);
        double[] temperatureSeries4 = {-110.0, -2.0, -0.5, 0.5, 2.0, 100.0};
        testClosestToValue(temperatureSeries4, 100, 500.0);
    }

    public void testClosestToZero(double[] temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testClosestToZero() {
        double[] temperatureSeries = {100.0, 2000.0, 2000.0, 3000.0, 110.0};
        testClosestToZero(temperatureSeries, 100.0);
        double[] temperatureSeries2 = {-105.0, -20.0, -1.0, -60, -70};
        testClosestToZero(temperatureSeries2, -1.0);
        double[] temperatureSeries3 = {148.8, 1500, -148.8, 300};
        testClosestToZero(temperatureSeries3, 148.8);
    }
    public void testFindTempsLessThen(double[] temperatureSeries, double[] expResult, double value) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsLessThen(value);
        for (int i = 0; i < expResult.length; i += 1) {
            assertEquals(expResult[i], actualResult[i], 0.00001);
        }
        assertEquals(actualResult.length, expResult.length);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {-100.0, -200.0, -250.0, 100.0, 2000.0};
        double[] expResult = {-100.0, -200.0, -250.0};
        testFindTempsLessThen(temperatureSeries, expResult, 0.0);
        double[] temperatureSeries2 = {-1.0, -2.0, 0, 1.0, 2.0, 100.0};
        double[] expResult2 = {};
        testFindTempsLessThen(temperatureSeries2, expResult2, -273);
        double[] temperatureSeries3 = {100.0, 200.0, 700.0, 800.0, 5700.0};
        double[] expResult3 = {100.0, 200.0};
        testFindTempsLessThen(temperatureSeries3, expResult3, 500.0);
        double[] temperatureSeries4 = {100.0, 200.0, 700.0, 800.0, 5700.0};
        double[] expResult4 = {};
        testFindTempsLessThen(temperatureSeries4, expResult4, 0.0);
    }

    public void testFindTempsGreaterThen(double[] temperatureSeries, double[] expResult, double value) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(value);
        for (int i = 0; i < expResult.length; i += 1) {
            assertEquals(expResult[i], actualResult[i], 0.00001);
        }
        assertEquals(actualResult.length, expResult.length);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {-100.0, -200.0, -250.0, 2000.0, 250.0, 380.0};
        double[] expResult = {2000.0, 250.0, 380.0};
        testFindTempsGreaterThen(temperatureSeries, expResult, 0.0);
        double[] temperatureSeries2 = {-1.0, -2.0, 0, 1.0, 2.0, 100.0};
        double[] expResult2 = {-1.0, -2.0, 0, 1.0, 2.0, 100.0};
        testFindTempsGreaterThen(temperatureSeries2, expResult2, -273);
        double[] temperatureSeries3 = {100.0, 200.0, 700.0, 800.0, 5700.0};
        double[] expResult3 = {800.0, 5700.0};
        testFindTempsGreaterThen(temperatureSeries3, expResult3, 700.0);
        double[] temperatureSeries4 = {100.0, 200.0, 700.0, 800.0, 5700.0};
        double[] expResult4 = {};
        testFindTempsGreaterThen(temperatureSeries4, expResult4, 6000.0);
    }
    public void testAddElement(ArrayList<Double> arr, TemperatureSeriesAnalysis seriesAnalysis) {
        for (int i = 0; i < seriesAnalysis.numberOfElements; i += 1)
            assertEquals(arr.get(i), seriesAnalysis.tempSeries[i], 0.00001);
    }

    @Test
    public void testAddElement() {
        Double[] lst = {0.0};
        ArrayList<Double> series = new ArrayList<>(Arrays.asList(lst));
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis();
        testAddElement(series, seriesAnalysis2);
        Double[] temperatureSeries = {100.0, 200.0, 700.0, 800.0, 5700.0};
        double[] temperatureSeriesCopy = {100.0, 200.0, 700.0, 800.0, 5700.0};
        ArrayList<Double> list = new ArrayList<>(Arrays.asList(temperatureSeries));
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeriesCopy);
        seriesAnalysis.addTemps(temperatureSeriesCopy);
        list.addAll(Arrays.asList(temperatureSeries));
        Double[] temperatureSeries2 = {-100.0, -200.0, -250.0, 2000.0, 250.0, 380.0};
        double[] temperatureSeriesCopy2 = {-100.0, -200.0, -250.0, 2000.0, 250.0, 380.0};

        seriesAnalysis.addTemps(temperatureSeriesCopy2);
        Collections.addAll(list, temperatureSeries2);
        testAddElement(list, seriesAnalysis);

    }
    public void testTempSummaryStatistics(double[] temperatureSeries) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics stats = seriesAnalysis.summaryStatistics();
        assertEquals(stats.avgTemp, seriesAnalysis.average(), 0.000001);
        assertEquals(stats.devTemp, seriesAnalysis.deviation(), 0.000001);
        assertEquals(stats.maxTemp, seriesAnalysis.max(), 0.000001);
        assertEquals(stats.minTemp, seriesAnalysis.min(), 0.000001);

    }
    @Test
    public void testTempSummaryStatistics() {
        double[] temperatureSeries = {-1.0, -2.0, 0, 1.0, 2.0};
        testTempSummaryStatistics(temperatureSeries);
    }




}
