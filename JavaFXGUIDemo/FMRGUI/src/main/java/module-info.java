module fmrcrawler.fmrgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.xml;

    opens crawlers.reportgui to javafx.fxml;
    exports crawlers.reportgui;
}