module fi.swdesign1harj {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;

    opens fi.swdesign1harj to javafx.fxml;
    exports fi.swdesign1harj;
}
