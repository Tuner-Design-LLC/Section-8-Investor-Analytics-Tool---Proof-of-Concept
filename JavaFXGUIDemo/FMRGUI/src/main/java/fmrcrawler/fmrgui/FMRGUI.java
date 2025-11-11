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
    private int currentReport;
    private ArrayList<FMRReport> reports = new ArrayList<>();

    public FMRGUI(){
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

    public int getNumOfReports(){
        return reports.size();
    }

    public int getCurrentReport(){
        return currentReport;
    }

    public void setCurrentReport(int report){
        currentReport = report;
    }

    public void increaseCurrentReport(){
        if (currentReport+1 < getNumOfReports()){
            currentReport++;
        }
    }

    public void decreaseCurrentReport(){
        if (currentReport > 0){
            currentReport--;
        }
    }

    public double getAverageFMRNumber(){
        double temp=0;
        for(FMRReport report: reports){
            temp+= Double.parseDouble(report.getFairMarketRent());
        }
        return (temp/reports.size());
    }

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
}
