package com.residentevildemo.serviceImpl;

import com.residentevildemo.repositories.CapitalRepository;
import com.residentevildemo.services.CapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CapitalServiceImpl implements CapitalService {

    private final CapitalRepository capitalRepository;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository) {
        this.capitalRepository = capitalRepository;
    }

    @Override
    public List<String> getCapitals() {
        return capitalRepository.getCapitalNames();
    }
}
