/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openmrs.module.covid19.fragment.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.openmrs.module.covid19.Covid19Cases;
import org.openmrs.module.covid19.api.dao.Covid19Dao;
import org.openmrs.ui.framework.fragment.FragmentModel;

/**
 * @author ihvn
 */
public class Cv19detailsFragmentController {
	
	Covid19Dao covid19Dao = new Covid19Dao();
	
	List<Map<String, String>> indicList = new ArrayList<Map<String, String>>();
	
	public void controller(FragmentModel model, HttpServletRequest request) {
		int subSet = Integer.parseInt(request.getParameter("subset"));
		int type = Integer.parseInt(request.getParameter("type"));
		
		DateTime startDateTime = new DateTime();
		DateTime endDateTime = new DateTime();
		
		if (request.getParameter("startDate") != null && !request.getParameter("startDate").equalsIgnoreCase("")) {
			startDateTime = new DateTime(request.getParameter("startDate"));
			endDateTime = new DateTime(request.getParameter("endDate"));
		} else {
			startDateTime = new DateTime("1990-01-01");
			endDateTime = new DateTime(new Date());
		}
		
		String startDate = startDateTime.toString("yyyy'-'MM'-'dd");
		String endDate = endDateTime.toString("yyyy'-'MM'-'dd");
		
		model.addAttribute("subset", subSet);
		model.addAttribute("type", type);
		
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
		        */
		/*
		Map<String, String> dataMap = new HashMap();
		indic1Total = male1Total + female1Total;
		dataMap.put("male1Total", male1Total + "");
		dataMap.put("female1Total", female1Total + "");
		dataMap.put("indic1Total", indic1Total + "");
		male1Total = 0;
		female1Total = 0;
		indic1Total = 0;
		*/
		//dataMap.put("totalAdultsTestedPositive",  adultsTestedPositive+"");
		//return new JSONObject(dataMap).toString();
		
		if (subSet == 1) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData1(startDate, endDate);
			model.addAttribute("title", "# of Suspected cases ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 2) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData2(startDate, endDate);
			model.addAttribute("title", "# of Lab-confirmed positive cases ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 3) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData2(startDate, endDate);
			model.addAttribute("title", "# of Lab-confirmed positive cases in Health care Worker(s) ( " + startDate
			        + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 4) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData4(startDate, endDate);
			model.addAttribute("title", "# of Probable cases ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 5) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Signal under investigation ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 6) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Persons screened at POEs ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 7) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Persons of interest under monitoring ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 8) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData8(startDate, endDate);
			model.addAttribute("title", "# of Number of confirmed cases on admission ( " + startDate + " and " + endDate
			        + ")");
			
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 9) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of confirmed cases discharged ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 10) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData10(startDate, endDate);
			model.addAttribute("title", "# of Number of deaths in confirmed cases ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 11) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of deaths in Health care workers ( " + startDate + " and " + endDate
			        + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 12) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData12(startDate, endDate);
			model.addAttribute("title", "# of Number of deaths in suspected cases ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 13) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of suspected cases in correctional facilities ( " + startDate + " and "
			        + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 14) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of confirmed cases in correctional facilities ( " + startDate + " and "
			        + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 15) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of suspected cases in Schools ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 16) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of confirmed cases in schools ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 17) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Contacts listed ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 18) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Contacts seen today ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 19) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Contacts currently under follow up ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 20) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Contacts symptomatic ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 21) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Contacts confirmed positive ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 22) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Contacts who completed 14 days follow up ( " + startDate + " and " + endDate
			        + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 23) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Contacts lost to follow Up ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 24) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of Initial samples collected (Initial only) ( " + startDate + " and "
			        + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 25) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of pending laboratory samples (initial only) ( " + startDate + " and "
			        + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 26) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of Negative Samples ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 27) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of specimens collected ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 28) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of specimens tested negative ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 29) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of pending laboratory samples ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 30) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of Treatment Centre in the State ( " + startDate + " and " + endDate
			        + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 31) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Total Bed Capacity in the State ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 32) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Total Number of ICU Beds in the states ( " + startDate + " and " + endDate
			        + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 33) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Total Number of Oxygen Plants in the State ( " + startDate + " and " + endDate
			        + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 34) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Total number of Oxygen concentrators ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 35) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of cases on Admission in the State ( " + startDate + " and " + endDate
			        + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 36) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of cases managed at home ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 37) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Number of cases discharged ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 38) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData38(startDate, endDate);
			model.addAttribute("title", "# of Number of Asymptomatic Cases ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 39) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData39(startDate, endDate);
			model.addAttribute("title", "# of Number of Symptomatic Cases ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", allPatients);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 0x27a) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Mild (M) ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 0x27b) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Moderate (MD) ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 0x27c) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Severe (S) ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 39d) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute("title", "# of Critical (C) ( " + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		if (subSet == 40) {//Percentage of new adult PLHIV offered index testing
			//lets get the data
			List<Covid19Cases> allPatients = covid19Dao.getCasesData6(startDate, endDate);
			model.addAttribute(
			    "title",
			    "# of Number of cases with co-morbidities e.g., hypertension, diabetes, asthma, immunosuppressed (cancer, HIV), sickle cell etc. ( "
			            + startDate + " and " + endDate + ")");
			
			model.addAttribute("patients", "");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			//model.addAttribute("Misc", Misc.class);
		}
		
	}
}
