package fmrcrawler.fmrgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class FMRGUIController {
    private FMRGUI GUI1 = new FMRGUI();

    @FXML
    private TextField BedroomAverage;

    @FXML
    private TextField CountyName;

    @FXML
    private TextField CurrentReport;

    @FXML
    private TextField FMRAverage;

    @FXML
    private TextField FMRNumber;

    @FXML
    private TextField FiscalYear;

    @FXML
    private TextField HouseholdIncome;

    @FXML
    private TextField IncomeAverage;

    @FXML
    private TextField ManualEnterReport;

    @FXML
    private TextField MarketType;

    @FXML
    private TextField NumBedrooms;

    @FXML
    private TextField ReportPath;

    @FXML
    private TextField State;

    @FXML
    private TextField TotalReports;

    @FXML
    private TextField ZipCode;

    @FXML
    private TextField FilterState;

    @FXML
    private TextField TotalFilteredReports;


    @FXML //copy of OpenReport method with a preset file path used for testing
    void TestLoad(ActionEvent event) throws ParserConfigurationException, IOException, SAXException {
        GUI1.setFilePath("C:\\\\Python\\\\221Project\\\\TestReport.xml");

        GUI1.openXMLReport();

        updateReportGUI();
    }

    @FXML //opens the selected report path and update GUI
    void OpenReport(ActionEvent event) throws ParserConfigurationException, IOException, SAXException {
        GUI1.setFilePath(this.ReportPath.getText());

        GUI1.openXMLReport();

        updateReportGUI();
    }

    @FXML //clear the report lists and update GUI
    void ClearReports(ActionEvent event) {
        GUI1 = new FMRGUI();

        updateReportGUIClear();
    }

    @FXML //decrease the currently selected report by one and update GUI
    void DecreaseCurrentReport(ActionEvent event) {
        GUI1.decreaseCurrentReport();

        updateReportGUI();
    }

    @FXML //increase the currently selected report by one and update GUI
    void IncreaseCurrentReport(ActionEvent event) {
        GUI1.increaseCurrentReport();

        updateReportGUI();
    }

    @FXML //get the ID that the user manually entered and go to it
    void GetManualReport(ActionEvent event) {
        int tempReportID = Integer.parseInt(this.ManualEnterReport.getText());

        if (tempReportID > 0 && tempReportID < GUI1.getNumOfReports()+1){
            GUI1.setCurrentReport(tempReportID-1);
            updateReportGUI();
        }
    }

    @FXML //apply the filters and update the GUI
    void FilterButton(ActionEvent event) {
        GUI1.resetFilterReportList();
        GUI1.filterReportsByState(this.FilterState.getText());
        TotalFilteredReports.setText(String.format("%d",GUI1.getNumOfFilteredReports()));
        updateReportGUI();
    }

    @FXML//toggle the filter on/off and update GUI
    void ToggleFilters(ActionEvent event) {
        GUI1.toggleFilter();
        updateReportGUI();
    }

    //update all text fields
    private void updateReportGUI(){
        CurrentReport.setText(String.format("%d",GUI1.getCurrentReport()+1));
        TotalReports.setText(String.format("%d",GUI1.getNumOfReports()));

        FiscalYear.setText(String.format(GUI1.getCurrentReportFiscalYear()));
        CountyName.setText(String.format(GUI1.getCurrentReportCountyName()));
        NumBedrooms.setText(String.format(GUI1.getCurrentReportNumOfBedrooms()));
        FMRNumber.setText(String.format("$%s",GUI1.getCurrentReportFMRNumber()));
        HouseholdIncome.setText(String.format("$%s",GUI1.getCurrentReportHouseholdIncome()));
        MarketType.setText(String.format(GUI1.getCurrentReportMarketType()));
        ZipCode.setText(String.format(GUI1.getCurrentReportZipCode()));
        State.setText(String.format(GUI1.getCurrentReportState()));

        FMRAverage.setText(String.format("$%.2f",GUI1.getAverageFMRNumber()));
        IncomeAverage.setText(String.format("$%.2f",GUI1.getAverageIncome()));
        BedroomAverage.setText(String.format("%.2f",GUI1.getAverageBedrooms()));

        //case where user enables filters with no reports in the filters list
        if(Double.isNaN(GUI1.getAverageFMRNumber()))
            FMRAverage.setText("");
        if(Double.isNaN(GUI1.getAverageIncome()))
            IncomeAverage.setText("");
        if(Double.isNaN(GUI1.getAverageBedrooms()))
            BedroomAverage.setText("");
    }

    //clear all text fields to base values
    private void updateReportGUIClear(){
        TotalReports.setText("0");
        TotalFilteredReports.setText("0");
        CurrentReport.setText("");
        FiscalYear.setText("");
        CountyName.setText("");
        NumBedrooms.setText("");
        FMRNumber.setText("");
        HouseholdIncome.setText("");
        MarketType.setText("");
        ZipCode.setText("");
        State.setText("");
        FMRAverage.setText("");
        IncomeAverage.setText("");
        BedroomAverage.setText("");
    }

}
