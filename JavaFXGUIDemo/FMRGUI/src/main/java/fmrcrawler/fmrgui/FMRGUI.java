package fmrcrawler.fmrgui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FMRGUI {
    private String filePath;
    private Boolean filterEnabled;
    private int currentReport;
    private ArrayList<FMRReport> reports = new ArrayList<>();
    private ArrayList<FMRReport> reportsFiltered = new ArrayList<>();

    public FMRGUI(){
        filterEnabled = false;
        currentReport=0;
    }

    //sets the file path to load from
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //opens the XML file at the given path and extracts the data into reports
    public void openXMLReport() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        ReportHandler handler = new ReportHandler();
        parser.parse(new File(filePath), handler);
        //parser.parse(new File("C:\\Python\\221Project\\TestingFMRReport.xml"), handler);


        //create a temp list of imported reports
        ArrayList<FMRReport> tempReports = new ArrayList<>(handler.getReports());
        for (FMRReport report:tempReports){
            boolean flag = true;
            for(FMRReport baseReport:reports){
                if (Integer.parseInt(report.getReportID()) == Integer.parseInt(baseReport.getReportID())) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                reports.add(report);
        }
    }

    //methods for dealing with the current number of reports and the selected report
    public int getNumOfReports(){
        return reports.size();
    }

    public int getCurrentReport(){
        return currentReport;
    }

    public void setCurrentReport(int report){
        currentReport = report;
    }

    //increase current report
    public void increaseCurrentReport(){
        if (currentReport+1 < getNumOfReports()){
            currentReport++;
        }
    }

    //decrease current report
    public void decreaseCurrentReport(){
        if (currentReport > 0){
            currentReport--;
        }
    }

    //average methods include two cases depending on if the filter is enabled or not
    public double getAverageFMRNumber(){
        double temp=0;
        if(filterEnabled){
            for(FMRReport report: reportsFiltered){
                temp+= Double.parseDouble(report.getFairMarketRent());
            }
            return (temp/reportsFiltered.size());
        }
        else{
            for(FMRReport report: reports){
                temp+= Double.parseDouble(report.getFairMarketRent());
            }
            return (temp/reports.size());
        }
    }

    public double getAverageIncome(){
        double temp=0;
        if(filterEnabled){
            for(FMRReport report: reportsFiltered){
                temp+= Double.parseDouble(report.getMedianHouseholdIncome());
            }
            return (temp/reportsFiltered.size());
        }
        else{
            for(FMRReport report: reports){
                temp+= Double.parseDouble(report.getMedianHouseholdIncome());
            }
            return (temp/reports.size());
        }
    }

    public double getAverageBedrooms(){
        double temp=0;
        if(filterEnabled){
            for(FMRReport report: reportsFiltered){
                temp+= Double.parseDouble(report.getNumBedrooms());
            }
            return (temp/reportsFiltered.size());
        }
        else{
            for(FMRReport report: reports){
                temp+= Double.parseDouble(report.getNumBedrooms());
            }
            return (temp/reports.size());
        }
    }

    //create a filtered list of reports by state
    public void filterReportsByState(String stateKey){
        for(FMRReport report: reports){
            if(Objects.equals(report.getStateName(), stateKey)){
                reportsFiltered.add(report);
            }
        }
    }

    //remove all filtered data
    public void resetFilterReportList(){
        reportsFiltered = new ArrayList<>();
    }

    //get all num of filtered reports
    public int getNumOfFilteredReports(){
        return reportsFiltered.size();
    }

    //toggle the filter on or off
    public void toggleFilter(){
        filterEnabled = !filterEnabled;
    }

    //standard get methods for the GUI
    public String getCurrentReportFiscalYear(){
        return reports.get(currentReport).getFiscalYear();
    }

    public String getCurrentReportCountyName(){
        return reports.get(currentReport).getCountyName();
    }

    public String getCurrentReportNumOfBedrooms(){
        return reports.get(currentReport).getNumBedrooms();
    }

    public String getCurrentReportFMRNumber(){
        return reports.get(currentReport).getFairMarketRent();
    }

    public String getCurrentReportState(){
        return reports.get(currentReport).getStateName();
    }

    public String getCurrentReportZipCode(){
        return reports.get(currentReport).getZipCode();
    }

    public String getCurrentReportMarketType(){
        return reports.get(currentReport).getAreaType();
    }

    public String getCurrentReportHouseholdIncome(){
        return reports.get(currentReport).getMedianHouseholdIncome();
    }
}
