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
    private TextField CountyName;

    @FXML
    private TextField CurrentReport;

    @FXML
    private TextField FMRNumber;

    @FXML
    private TextField FiscalYear;

    @FXML
    private TextField NumBedrooms;

    @FXML
    private TextField ReportPath;

    @FXML
    private TextField TotalReports;

    @FXML
    private TextField ManualEnterReport;

    @FXML //copy of OpenReport method with a preset file path used for testing
    void TestLoad(ActionEvent event) throws ParserConfigurationException, IOException, SAXException {
        GUI1.setFilePath("C:\\\\Python\\\\221Project\\\\TestingFMRReport.xml");

        GUI1.openXMLReport();

        updateCurrentReport();
    }

    @FXML
    void OpenReport(ActionEvent event) throws ParserConfigurationException, IOException, SAXException {
        GUI1.setFilePath(this.ReportPath.getText());

        GUI1.openXMLReport();

        updateCurrentReport();
    }

    @FXML
    void ClearReports(ActionEvent event) {
        GUI1 = new FMRGUI();

        updateCurrentReportClear();
    }

    @FXML
    void DecreaseCurrentReport(ActionEvent event) {
        GUI1.decreaseCurrentReport();

        updateCurrentReport();
    }

    @FXML
    void IncreaseCurrentReport(ActionEvent event) {
        GUI1.increaseCurrentReport();

        updateCurrentReport();
    }

    @FXML
    void GetManualReport(ActionEvent event) {
        int tempReportID = Integer.parseInt(this.ManualEnterReport.getText());

        if (tempReportID > 0 && tempReportID < GUI1.getNumOfReports()+1){
            GUI1.setCurrentReport(tempReportID-1);
            updateCurrentReport();
        }
    }

    private void updateCurrentReport(){
        CurrentReport.setText(String.format("%d",GUI1.getCurrentReport()+1));
        TotalReports.setText(String.format("%d",GUI1.getNumOfReports()));
        FiscalYear.setText(String.format(GUI1.getCurrentReportFiscalYear()));
        CountyName.setText(String.format(GUI1.getCurrentReportCountyName()));
        NumBedrooms.setText(String.format(GUI1.getCurrentReportNumOfBedrooms()));
        FMRNumber.setText(String.format(GUI1.getCurrentReportFMRNumber()));
    }

    private void updateCurrentReportClear(){
        CurrentReport.setText("1");
        TotalReports.setText("0");
        FiscalYear.setText("");
        CountyName.setText("");
        NumBedrooms.setText("");
        FMRNumber.setText("");
    }

}
