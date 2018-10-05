package com.apap.tutorial4.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.repository.FlightDB;

/*
 * 
 * 
 * FlightServiceImpl*/

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
	@Autowired
	private FlightDB flightDb;
	
	
	@Override
	public void addFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		flightDb.save(flight);
	}


	@Override
	public void deleteFlight(long id) {
		// TODO Auto-generated method stub
		FlightModel flight = flightDb.findById(id).get();
		flightDb.delete(flight);
		
	}


	@Override
	public FlightModel getFlightDetailById(long id) {
		// TODO Auto-generated method stub
		return flightDb.findById(id).get();
		
	}


	@Override
	public void updateFlight(FlightModel flight_update) {
		// TODO Auto-generated method stub

		FlightModel flight = getFlightDetailById(flight_update.getId());
		
		flight.setFlightNumber(flight_update.getFlightNumber());
		flight.setOrigin(flight_update.getOrigin());
		flight.setDestination(flight_update.getDestination());
		flight.setTime(flight_update.getTime());
	}
	
}
