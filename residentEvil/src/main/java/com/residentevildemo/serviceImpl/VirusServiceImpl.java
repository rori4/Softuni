package com.residentevildemo.serviceImpl;

import com.residentevildemo.entities.Capital;
import com.residentevildemo.entities.Virus;
import com.residentevildemo.models.VirusBindingModel;
import com.residentevildemo.repositories.CapitalRepository;
import com.residentevildemo.repositories.VirusRepository;
import com.residentevildemo.services.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

@Service
@Transactional
public class VirusServiceImpl implements VirusService {
    private final VirusRepository virusRepository;
    //good to call the capitalService
    private final CapitalRepository capitalRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(VirusBindingModel virusBindingModel) {
        Virus virus = this.modelMapper.map(virusBindingModel, Virus.class);
        Set<Capital> capitals = this.capitalRepository.getAllByNameIn(virusBindingModel.getCapitals());
        virus.setCapitals(capitals);
        this.virusRepository.save(virus);

    }

    @Override
    public String getGeoData() {
        List<Virus> viruses = this.virusRepository.getAllViruses();
        StringBuilder geoJson = new StringBuilder();
        String header = "{\n" +
                "    \"type\": \"FeatureCollection\",\n" +
                "    \"features\": [\n";
        String footer = "]\n" +
                "}\n";
        geoJson.append(header);
        StringJoiner joiner = new StringJoiner(",");
        for (Virus virus : viruses) {
            String color = "#f00";
            int magnitude = 0;
            switch (virus.getMagnitude()) {
                case LOW:
                    magnitude = 4;
                    break;
                case MEDIUM:
                    magnitude = 5;
                    break;
                case HIGH:
                    magnitude = 6;
                    break;
            }
            for (Capital capital : virus.getCapitals()) {
                String body = String.format("{\n" +
                        "        \"type\": \"Feature\",\n" +
                        "        \"properties\": {\n" +
                        "            \"mag\": %d,\n" +
                        "            \"color\": \"%s\"\n" +
                        "        },\n" +
                        "        \"geometry\": {\n" +
                        "            \"type\": \"Point\",\n" +
                        "            \"coordinates\": [\n" +
                        "                %f,\n" +
                        "                %f\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    }\n", magnitude, color, capital.getLatitude(), capital.getLongitude());
                joiner.add(body);
            }

        }
        geoJson.append(joiner);
        geoJson.append(footer);
        return geoJson.toString();
    }
}
