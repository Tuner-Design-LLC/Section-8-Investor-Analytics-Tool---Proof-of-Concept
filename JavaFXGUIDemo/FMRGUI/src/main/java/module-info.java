module fmrcrawler.fmrgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.xml;

    opens fmrcrawler.fmrgui to javafx.fxml;
    exports fmrcrawler.fmrgui;
}