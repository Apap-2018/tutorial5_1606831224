package com.apap.tutorial4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.FlightService;
import com.apap.tutorial4.service.PilotService;

/*
 * 
 * FlightController*/


@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value= "/flight/delete/{id}", method = RequestMethod.GET)
	private String deleteFlightById(@PathVariable(value = "id") String id, Model model) {
		long id_long = Long.parseLong(id);
		flightService.deleteFlight(id_long);
		return "delete";
	}
	
	@RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable String id, Model model) {
		long id_long = Long.parseLong(id);
		FlightModel flight = flightService.getFlightDetailById(id_long);
		model.addAttribute("flight", flight);

		return "update-flight";
	}
	
	@RequestMapping(value= "/flight/update", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel flight_update, Model model) {
		flightService.updateFlight(flight_update);
		return "update";
	}
	
	@RequestMapping(value= "/flight/view/{id}", method = RequestMethod.GET)
	private String getPilotByLicense(@PathVariable String id, Model model) {
		long id_long = Long.parseLong(id);
		FlightModel flight = flightService.getFlightDetailById(id_long);
		PilotModel pilot = flight.getPilot();
		model.addAttribute("flight", flight);
		model.addAttribute("pilot", pilot);
		
		return "view-flight";
	}
	
}
