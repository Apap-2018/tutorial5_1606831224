package com.apap.tutorial4.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.PilotDB;

/*
 * 
 * PilotService*/

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDB pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePilot(String licenseNumber) {
		// TODO Auto-generated method stub
		PilotModel pilot = pilotDb.findByLicenseNumber(licenseNumber);
		pilotDb.delete(pilot);
	}

	@Override
	public void updatePilot(PilotModel pilot_update) {
		// TODO Auto-generated method stub
		PilotModel pilot = pilotDb.findByLicenseNumber(pilot_update.getLicenseNumber());
		pilot.setName(pilot_update.getName());
		pilot.setFlyHour(pilot_update.getFlyHour());
		
	}
	
}
