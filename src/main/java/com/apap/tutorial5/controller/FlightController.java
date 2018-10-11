package com.apap.tutorial5.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

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
//		FlightModel flight = new FlightModel();
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//		flight.setPilot(pilot);
		
		PilotModel pilot = new PilotModel();
		
		List<FlightModel> listFlight = new ArrayList<FlightModel>();
		FlightModel flight = new FlightModel();
		flight.setTime(new Date(118, 9, 11));
		listFlight.add(flight);
		pilot.setPilotFlight(listFlight);
		
		model.addAttribute("pilot", pilot);
		model.addAttribute("licenseNumber", licenseNumber);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"addRow"})
	private String addRow(@PathVariable(value = "licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilot, Model model) {
		
		for (int i = 0; i < pilot.getPilotFlight().size(); i++) {
			System.out.println();
			System.out.println(pilot.getPilotFlight().get(i).getTime());
		}
		
		FlightModel flight = new FlightModel();
		flight.setTime(new Date(118, 9, 11));
		pilot.getPilotFlight().add(flight);
		
		model.addAttribute("licenseNumber", licenseNumber);
		model.addAttribute("pilot", pilot);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"submit"})
	private String addFlightSubmit(@PathVariable(value = "licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilot_new) {
		PilotModel pilot_insert = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		for (int i = 0; i < pilot_new.getPilotFlight().size(); i++) {
			FlightModel flight = pilot_new.getPilotFlight().get(i);
			flight.setPilot(pilot_insert);
			flightService.addFlight(flight);
		}
		return "add";
	}
	
	@RequestMapping(value= "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlight(flight.getId());
		}
		
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
