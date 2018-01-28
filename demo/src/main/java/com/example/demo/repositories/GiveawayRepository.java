package com.example.demo.repositories;

import com.example.demo.entities.Giveaway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiveawayRepository extends JpaRepository<Giveaway,Long> {

}
