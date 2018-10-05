package com.apap.tutorial4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apap.tutorial4.model.FlightModel;

/*
 * 
 * FlightDB*/

@Repository
public interface FlightDB extends JpaRepository<FlightModel, Long> {
	
}
