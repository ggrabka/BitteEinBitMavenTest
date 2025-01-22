module com.bitteEinBit {

    //TODO UNCOMMENT

    requires org.kordamp.bootstrapfx.core;

    requires com.google.gson;
    requires javafx.controls;
    requires java.desktop;
    opens com.bitteEinBit to com.google.gson;

    exports com.bitteEinBit;
}