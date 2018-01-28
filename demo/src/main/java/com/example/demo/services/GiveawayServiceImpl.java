package com.example.demo.services;

import com.example.demo.entities.Giveaway;
import com.example.demo.models.AddGiveawayModel;
import com.example.demo.repositories.GiveawayRepository;
import com.example.demo.submitter.WebsiteSubmitter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GiveawayServiceImpl implements GiveawayService {
    private final GiveawayRepository giveawayRepository;

    @Autowired
    public GiveawayServiceImpl(GiveawayRepository giveawayRepository) {
        this.giveawayRepository = giveawayRepository;
    }

    @Override
    public void save(AddGiveawayModel addGiveawayModel) {
        ModelMapper modelMapper = new ModelMapper();
        Giveaway giveaway = modelMapper.map(addGiveawayModel, Giveaway.class);
        WebsiteSubmitter websiteSubmitter = new WebsiteSubmitter();
        websiteSubmitter.invokeSession(giveaway);
        this.giveawayRepository.saveAndFlush(giveaway);
    }
}
