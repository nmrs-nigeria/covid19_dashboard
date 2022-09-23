/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.covid19.fragment.controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;
import org.openmrs.api.UserService;
import org.openmrs.module.covid19.Covid19Cases;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.module.covid19.api.dao.Covid19Dao;

/**
 *  * Controller for a fragment that shows all users  
 */
public class UsersFragmentController {
	
	Covid19Dao covid19Dao = new Covid19Dao();
	
	List<Map<String, String>> indicList = new ArrayList<Map<String, String>>();
	
	public void controller(FragmentModel model, @SpringBean("userService") UserService service) {
		model.addAttribute("users", service.getAllUsers());
	}
	
	public String getAllCasesData(HttpServletRequest request) {
		DateTime startDateTime = new DateTime(request.getParameter("startDate"));
		DateTime endDateTime = new DateTime(request.getParameter("endDate"));
		System.out.println(startDateTime + "in UsersFragmentController");
		//Database.initConnection();
		
		String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
		String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
		
		int indicNo = 0;
		int male1Total = 0;
		int female1Total = 0;
		int indic1Total = 0;
		
		List<Covid19Cases> allPatients = covid19Dao.getCasesData(startDate, endDate);
		/*
		for (int i = 0; i < allPatients.size(); i++) {
		
		if (java.util.Objects.equals(allPatients.get(i).getCase_status(), 162743)) {
		
		if (allPatients.get(i).getGender().equalsIgnoreCase("M")
		        || allPatients.get(i).getGender().equalsIgnoreCase("Male")) {
			male1Total++;
		} else if (allPatients.get(i).getGender().equalsIgnoreCase("F")
		        || allPatients.get(i).getGender().equalsIgnoreCase("Female")) {
			female1Total++;
		}
		
		}
		}
		
		Map<String, String> dataMap = new HashMap();
		indic1Total = male1Total + female1Total;
		dataMap.put("male1Total", male1Total + "");
		dataMap.put("female1Total", female1Total + "");
		dataMap.put("indic1Total", indic1Total + "");
		male1Total = 0;
		female1Total = 0;
		indic1Total = 0;
		
		//dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
		return new JSONObject(dataMap).toString();
		*/
		String json = new Gson().toJson(allPatients);
		return json;
	}
	
}
