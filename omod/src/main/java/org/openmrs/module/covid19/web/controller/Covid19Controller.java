/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.covid19.web.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

import com.google.gson.Gson;
import org.codehaus.jettison.json.JSONObject;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/covid19/covid19Link.form'.
 */
@Controller("${rootrootArtifactId}.Covid19Controller")
@RequestMapping(value = "module/covid19/covid19.form")
public class Covid19Controller {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	UserService userService;
	
	/** Success form view name */
	private final String VIEW = "/module/covid19/covid19";
	
	/**
	 * Initially called after the getUsers method to get the landing form name
	 * 
	 * @return String form view name
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String onGet() {
		return VIEW;
	}
	
	/**
	 * All the parameters are optional based on the necessity
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String onPost(HttpSession httpSession, @ModelAttribute("anyRequestObject") Object anyRequestObject,
	        BindingResult errors) {
		
		if (errors.hasErrors()) {
			// return error view
		}
		
		return VIEW;
	}
	
	/**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal java
	 * pojo. The bean name defined in the ModelAttribute annotation and the type can be just defined
	 * by the return type of this method
	 */
	@ModelAttribute("users")
	protected List<User> getUsers() throws Exception {
		List<User> users = userService.getAllUsers();
		
		// this object will be made available to the jsp page under the variable name
		// that is defined in the @ModuleAttribute tag
		return users;
	}
	
	public String getAllCasesData(HttpServletRequest request) {
		DateTime startDateTime = new DateTime(request.getParameter("startDate"));
		DateTime endDateTime = new DateTime(request.getParameter("endDate"));
		
		//Database.initConnection();
		
		String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
		String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
		
		int male10To14 = 0;
		int male15To19 = 0;
		int male20To24 = 0;
		int maleabove24 = 0;
		
		int female10To14 = 0;
		int female15To19 = 0;
		int female20To24 = 0;
		int femaleabove24 = 0;
		
		Map<String, String> dataMap = new HashMap();
		
		dataMap.put("male10To14", male10To14 + "d");
		dataMap.put("male15To19", male15To19 + "d");
		dataMap.put("male20To24", male20To24 + "d");
		dataMap.put("maleabove24", maleabove24 + "d");
		dataMap.put("maleabove24", maleabove24 + "d");
		dataMap.put("female10To14", female10To14 + "d");
		dataMap.put("female15To19", female15To19 + "d");
		dataMap.put("female20To24", female20To24 + "d");
		dataMap.put("femaleabove24", femaleabove24 + "dd");
		dataMap.put("femaleabove24", femaleabove24 + "d");
		
		//dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
		return new JSONObject(dataMap).toString();
	}
}
