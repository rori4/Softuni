package com.residentevildemo.services;

import com.residentevildemo.models.VirusBindingModel;

public interface VirusService {
    void create(VirusBindingModel virusBindingModel);

    String getGeoData();
}
