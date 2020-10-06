module br.com.leonardopn {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;
	requires javafx.swing;

    opens br.com.leonardopn to javafx.fxml;
    opens br.com.leonardopn.controller to javafx.fxml;
    exports br.com.leonardopn;
}