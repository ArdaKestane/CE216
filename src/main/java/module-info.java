module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
}