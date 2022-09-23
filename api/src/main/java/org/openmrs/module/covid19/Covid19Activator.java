/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.covid19;

import org.openmrs.module.covid19.api.dao.Database;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import java.util.UUID;
import org.openmrs.Encounter;
//import org.openmrs.event.Event;
import org.openmrs.GlobalProperty;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.UserContext;
import org.openmrs.module.BaseModuleActivator;

import org.openmrs.module.covid19.api.dao.PersonDao;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class Covid19Activator extends BaseModuleActivator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	PersonDao dao = new PersonDao();
	
	private List<Map<String, String>> allCovid19Cases = new ArrayList();
	
	private String lastAnalysisDate = "";
	
	/**
	 * @see #started()
	 */
	public void started() {
		log.info("Started Covid19");
		Database.initConnection();
		//sets set sql mode to no substitution 
		Database.setSQLMode("NO_ENGINE_SUBSTITUTION");
		System.out.println("started Covid19 Dashboard");
		
		this.startAnalyticsTask();
	}
	
	/**
	 * @see #shutdown()
	 */
	public void shutdown() {
		log.info("Shutdown Covid19");
	}
	
	private void startAnalyticsTask() {
		Timer t = new Timer();
		TimerTask tt = new TimerTask() {
			
			@Override
			public void run() {
				PersonDao personDao = new PersonDao();
				Context.openSession();
				
				lastAnalysisDate = dao.getGlobalProperty("covid19_last_analysis_date");
				System.out.println("Last analysis Date" + lastAnalysisDate);
				if (lastAnalysisDate == null || lastAnalysisDate.equalsIgnoreCase("")) {
					lastAnalysisDate = "1990-01-01";
					UUID uuid = UUID.randomUUID();
					String uuidAsString = uuid.toString();
					personDao.saveGlobalProperty("covid19_last_analysis_date", lastAnalysisDate,
					    "Last time Covid19 Analysis was run", uuidAsString);
					
				}
				
				int totalPersons = dao.getTotalPersons();
				// System.out.println("total patient count" + totalPatients);
				int limit = 1000;
				int totalPages = totalPersons / limit;
				if (totalPages == 0) {
					totalPages = 1;
				}
				
				//get patients in fragments of 1000.
				for (int i = 0; i < totalPages; i++) {
					//for each fragment, perform analysis
					int offset = i * limit;
					List<Map<String, String>> allPatients = dao.getAllPatients(limit, offset, lastAnalysisDate);
					
					//loop through the patients and save encounters in flat tables
					//System.out.println("PPPPPPPPPPPPPPPPPPPPPPPatient SIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIzeEEEEEEEEEEEEE"+allPatients.size());
					for (int j = 0; j < allPatients.size(); j++) {
						int patientId = Integer.parseInt(allPatients.get(j).get("patient_id"));
						allCovid19Cases.addAll(dao.getCovid19Cases(patientId));
						//System.out.println("Patient ID" + patientId);
						
					}
					//System.out.println("All pattttttttttttttttttttttttttttttttttttt: " + allCovid19Cases);
					dao.saveCovid19Cases(allCovid19Cases);
					
					//clear all datastructures
					allCovid19Cases.clear();
					
				}
				
				///once complete, lets save last run date
				DateTime today = new DateTime(new Date());
				String now = today.toString("yyyy'-'MM'-'dd HH:mm");
				Context.getAdministrationService().updateGlobalProperty("covid19_last_analysis_date", now);
				Context.closeSession();
				//System.out.println("completed");
			};
		};
		t.scheduleAtFixedRate(tt, 5000, 10000);
		
	}
	
}
