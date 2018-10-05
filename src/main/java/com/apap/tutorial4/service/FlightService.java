package com.apap.tutorial4.service;

import com.apap.tutorial4.model.FlightModel;

/*
 * 
 * FlightService*/

public interface FlightService {
	void addFlight(FlightModel flight);
	void deleteFlight(long id);
	void updateFlight(FlightModel flight_update);
	
	FlightModel getFlightDetailById(long id);
	
	
	
}
