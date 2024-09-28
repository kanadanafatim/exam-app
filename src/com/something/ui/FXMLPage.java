package com.something.ui;

import java.net.URL;


public enum FXMLPage {
    SCREEN("/com/something/ui/fxml/ecran.fxml");

    private final String location;

    FXMLPage(String location) {
        this.location = location;
    }

    public URL getPage() {
        return  getClass().getResource(location) ;
    }
}
