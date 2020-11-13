package ua.edu.ucu.tempseries;


import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    public final static double ABSOLUTE_ZERO = -273.15;
    double[] defaultArray = {0.0};
    double[] tempSeries;
    int numberOfElements;

    public TemperatureSeriesAnalysis() {
        tempSeries = defaultArray;
        numberOfElements = tempSeries.length;
    }

    public void checkValidTemperatures(double[] temperatureSeries) {
        for (double temperature : temperatureSeries) {
            if (temperature < ABSOLUTE_ZERO) {
                throw new InputMismatchException("Error!!! The temperature is below absolute zero!!!");
            }
        }
        this.tempSeries = temperatureSeries;
        numberOfElements = tempSeries.length;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        checkValidTemperatures(temperatureSeries);
    }

    private void checkLen() {
        if (numberOfElements == 0) {
            throw new IllegalArgumentException("Error!!! There are no elements in the array");
        }
    }

    public double average() {
        checkLen();
        double temp_sum = 0;
        for (double temp : tempSeries) {
            temp_sum += temp;
        }
        double average = temp_sum / numberOfElements;
        return average;
    }

    public double deviation() {
        checkLen();
        if (tempSeries.length == 1) {
            throw new IllegalArgumentException("Deviation is not defined on the 1-element set");
        }
        double temperatureAverage = average();
        double oscilation = 0;
        for (double temp : tempSeries) {
            oscilation += Math.pow((temperatureAverage - temp), 2);
        }
        double deviation = Math.pow((oscilation / numberOfElements), 0.5);
        return deviation;
    }

    private double find_limiting(int multiplier) {
        checkLen();
        double limiting = tempSeries[0];
        for (double temp : tempSeries) {
            if (temp * multiplier > tempSeries[0] * multiplier) {
                limiting = temp;
            }
        }
        return limiting;
    }

    public double min() {
        return find_limiting(-1);

    }

    public double max() {
        return find_limiting(1);
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkLen();
        double closest = tempSeries[0];
        double minimum = tempValue - tempSeries[0];
        for (double temp : tempSeries) {
            if (Math.abs(tempValue - temp) < Math.abs(minimum) || (Math.abs(tempValue - temp) == Math.abs(minimum) && temp > 0)) {
                closest = temp;
                minimum = tempValue - temp;
            }
        }
        return closest;
    }

    private double[] comparison(double value, double multiplier) {
        checkLen();
        int numberOfNeqElements = 0;
        for (double temp : tempSeries) {
            if (temp * multiplier > value * multiplier) {
                numberOfNeqElements++;
            }
        }
        double[] notEqual = new double[numberOfNeqElements];
        int i = 0;
        for (double temp : tempSeries) {
            if (temp * multiplier > value * multiplier) {
                notEqual[i] = temp;
                i++;
            }

        }
        return notEqual;
    }

    public double[] findTempsLessThen(double tempValue) {

        return comparison(tempValue, -1);
    }

    public double[] findTempsGreaterThen(double tempValue) {

        return comparison(tempValue, 1);
    }

    public TempSummaryStatistics summaryStatistics() {
        double tempAvg = average();
        double tempDev = deviation();
        double tempMin = min();
        double tempMax = max();
        return new TempSummaryStatistics(tempAvg, tempDev, tempMin, tempMax);
    }

    public void addTemps(double[] temps) {
        int numberOfElementsAdded = temps.length;
        if (numberOfElementsAdded + numberOfElements < tempSeries.length) {
            System.arraycopy(temps, 0, tempSeries, numberOfElements, numberOfElementsAdded);
        } else {
            double[] joined = new double[2 * (numberOfElementsAdded + numberOfElements)];
            if (numberOfElements >= 0) System.arraycopy(temps, 0, joined, 0, numberOfElements);
            System.arraycopy(temps, 0, joined, numberOfElements, numberOfElementsAdded);
            tempSeries = joined;
        }
        numberOfElements += numberOfElementsAdded;
    }
}
