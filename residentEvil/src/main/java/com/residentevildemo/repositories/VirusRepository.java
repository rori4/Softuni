package com.residentevildemo.repositories;

import com.residentevildemo.entities.Virus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirusRepository extends JpaRepository<Virus, Long> {
    @Query(value = "SELECT v FROM Virus AS v")
    List<Virus> getAllViruses();
}
