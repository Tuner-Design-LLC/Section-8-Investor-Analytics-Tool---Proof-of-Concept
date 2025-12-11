package crawlers.reportgui;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

// ReportHandlerPHA class to parse PHA report XML data.
public class ReportHandlerPHA extends DefaultHandler {

    private ArrayList<PHAReport> reports = new ArrayList<>();
    private PHAReport currentReport;
    private StringBuilder content = new StringBuilder();

    public ArrayList<PHAReport> getReports() {
        return reports;
    }

    @Override // Create each report and get its id
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("PHA")) {
            currentReport = new PHAReport();
            currentReport.setReportID(attributes.getValue(0));
        }
        content.setLength(0);
    }

    // Function for reading characters between tags
    @Override
    public void characters(char[] ch, int start, int length) {
        content.append(ch, start, length);
    }

    // Function for adding data to the report class
    @Override
    public void endElement(String uri, String localName, String qName) {

        if (currentReport != null) {
            switch (qName) {
                case "pha_code":
                    currentReport.setPhaCode(content.toString());
                    break;
                case "name":
                    currentReport.setName(content.toString());
                    break;
                case "hud_regional_code":
                    currentReport.setHudRegionalCode(content.toString());
                    break;
                case "jurisdiction":
                    currentReport.setJurisdiction(content.toString());
                    break;
                case "address":
                    currentReport.setAddress(content.toString());
                    break;
                case "city":
                    currentReport.setCity(content.toString());
                    break;
                case "state_code":
                    currentReport.setStateCode(content.toString());
                    break;
                case "state_name":
                    currentReport.setStateName(content.toString());
                    break;
                case "zip_code":
                    currentReport.setZipCode(content.toString());
                    break;
                case "county_name":
                    currentReport.setCountyName(content.toString());
                    break;
                case "fips_code":
                    currentReport.setFipsCode(content.toString());
                    break;
                case "program_type":
                    currentReport.setProgramType(content.toString());
                    break;
                case "num_hcv_units":
                    currentReport.setNumHcvUnits(content.toString());
                    break;
                case "num_total_assisted":
                    currentReport.setNumTotalAssisted(content.toString());
                    break;
                case "waiting_list_status":
                    currentReport.setWaitingListStatus(content.toString());
                    break;
                case "occupancy_rate":
                    currentReport.setOccupancyRate(content.toString());
                    break;
                case "hcv_util_rate":
                    currentReport.setHcvUtilRate(content.toString());
                    break;
                case "longitude":
                    currentReport.setLongitude(content.toString());
                    break;
                case "latitude":
                    currentReport.setLatitude(content.toString());
                    break;
                case "msa_code":
                    currentReport.setMsaCode(content.toString());
                    break;
                case "rural_indicator":
                    currentReport.setRuralIndicator(content.toString());
                    break;
                case "annual_budget":
                    currentReport.setAnnualBudget(content.toString());
                    break;
                case "capital_fund_allocation":
                    currentReport.setCapitalFundAllocation(content.toString());
                    break;
                case "financial_report_year":
                    currentReport.setFinancialReportYear(content.toString());
                    break;
                case "avg_tenant_income":
                    currentReport.setAvgTenantIncome(content.toString());
                    break;
                case "median_rent_dist":
                    currentReport.setMedianRentDist(content.toString());
                    break;
                case "household_type_dist":
                    currentReport.setHouseholdTypeDist(content.toString());
                    break;
                case "avg_tenant_rent_share":
                    currentReport.setAvgTenantRentShare(content.toString());
                    break;
                case "avg_subsidy_amount":
                    currentReport.setAvgSubsidyAmount(content.toString());
                    break;
                case "semap_score":
                    currentReport.setSemapScore(content.toString());
                    break;
                case "last_hud_audit_date":
                    currentReport.setLastHudAuditDate(content.toString());
                    break;
                case "inspection_compliance_rate":
                    currentReport.setInspectionComplianceRate(content.toString());
                    break;
                case "performance_category":
                    currentReport.setPerformanceCategory(content.toString());
                    break;
                case "last_updated":
                    currentReport.setLastUpdated(content.toString());
                    break;
                case "source_url":
                    currentReport.setSourceUrl(content.toString());
                    break;
                case "scrape_date":
                    currentReport.setScrapeDate(content.toString());
                    break;
                case "version_hash":
                    currentReport.setVersionHash(content.toString());
                    break;
                case "dataset_last_update":
                    currentReport.setDatasetLastUpdate(content.toString());
                    break;
                case "update_freq":
                    currentReport.setUpdateFreq(content.toString());
                    break;
                case "data_license":
                    currentReport.setDataLicense(content.toString());
                    break;
                case "crawler_run_data":
                    currentReport.setCrawlerRunData(content.toString());
                    break;
                case "phone_number":
                    currentReport.setPhoneNumber(content.toString());
                    break;
                case "fax_line":
                    currentReport.setFaxLine(content.toString());
                    break;
                case "email":
                    currentReport.setEmail(content.toString());
                    break;
                case "executive_director":
                    currentReport.setExecutiveDirector(content.toString());
                    break;
                case "contact_last_verified":
                    currentReport.setContactLastVerified(content.toString());
                    break;

                case "PHA":
                    reports.add(currentReport);
                    break;
            }
        }
    }
}