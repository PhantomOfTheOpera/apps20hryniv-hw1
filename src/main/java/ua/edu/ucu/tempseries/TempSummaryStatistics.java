package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    double avgTemp;
    double devTemp;
    double minTemp;
    double maxTemp;

    public TempSummaryStatistics(double tempAvg, double tempDev, double tempMin, double tempMax){
        avgTemp = tempAvg;
        devTemp = tempDev;
        minTemp = tempMin;
        maxTemp = tempMax;
    }
}
