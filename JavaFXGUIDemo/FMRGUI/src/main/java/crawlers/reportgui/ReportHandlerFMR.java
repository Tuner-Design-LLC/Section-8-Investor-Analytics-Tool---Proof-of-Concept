package crawlers.reportgui;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class ReportHandlerFMR extends DefaultHandler {

    private ArrayList<FMRReport> reports = new ArrayList<>();
    private FMRReport currentReport;
    private StringBuilder content = new StringBuilder();

    public ArrayList<FMRReport> getReports() {
        return reports;
    }

    @Override//create each report and get its id
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("Report")) {
            currentReport = new FMRReport();
            currentReport.setReportID(attributes.getValue(0));
        }
        content.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        content.append(ch, start, length);
    }

    @Override//function for adding data to the report class
    public void endElement(String uri, String localName, String qName) {

        if (currentReport != null) {
            switch (qName) {
                case "FiscalYear":
                    currentReport.setFiscalYear(content.toString());
                    break;
                case "StateName":
                    currentReport.setStateName(content.toString());
                    break;
                case "StateCode":
                    currentReport.setStateCode(content.toString());
                    break;

                case "CountyName":
                    currentReport.setCountyName(content.toString());
                    break;
                case "FIPSCode":
                    currentReport.setFipsCode(content.toString());
                    break;
                case "HUDGeoID":
                    currentReport.setHudGeoId(content.toString());
                    break;
                case "MSACode":
                    currentReport.setMsaCode(content.toString());
                    break;
                case "AreaType":
                    currentReport.setAreaType(content.toString());
                    break;
                case "HUDRegionCode":
                    currentReport.setHudRegionCode(content.toString());
                    break;
                case "ZipCode":
                    currentReport.setZipCode(content.toString());
                    break;
                case "NumBedrooms":
                    currentReport.setNumBedrooms(content.toString());
                    break;
                case "FairMarketRent":
                    currentReport.setFairMarketRent(content.toString());
                    break;
                case "PercentileType":
                    currentReport.setPercentileType(content.toString());
                    break;
                case "BedroomDistSource":
                    currentReport.setBedroomDistSource(content.toString());
                    break;
                case "SurveySourceYear":
                    currentReport.setSurveySourceYear(content.toString());
                    break;
                case "AdjustmentFactor":
                    currentReport.setAdjustmentFactor(content.toString());
                    break;
                case "IsSmallAreaFMR":
                    currentReport.setSmallAreaFmr(content.toString());
                    break;
                case "MedianHouseholdIncome":
                    currentReport.setMedianHouseholdIncome(content.toString());
                    break;
                case "SourceURL":
                    currentReport.setSourceUrl(content.toString());
                    break;
                case "ScrapeDate":
                    currentReport.setScrapeDate(content.toString());
                    break;
                case "VersionHash":
                    currentReport.setVersionHash(content.toString());
                    break;
                case "UpdateFrequency":
                    currentReport.setUpdateFreq(content.toString());
                    break;
                case "CrawlerRunData":
                    currentReport.setCrawlerRunData(content.toString());
                    break;

                case "Report":
                    reports.add(currentReport);
                    break;
            }
        }
    }
}