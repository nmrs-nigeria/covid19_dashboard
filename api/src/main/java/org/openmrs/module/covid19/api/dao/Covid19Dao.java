/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.covid19.api.dao;

import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.covid19.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.openmrs.module.covid19.Covid19Cases;

@Repository("covid19.Covid19Dao")
public class Covid19Dao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Item getItemByUuid(String uuid) {
		return (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	public Item saveItem(Item item) {
		getSession().saveOrUpdate(item);
		return item;
	}
	
	public List<Covid19Cases> getCasesData(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.case_status_date BETWEEN ? AND ? order by cv19_case_inv.case_status_date");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Covid19Cases> getCasesData1(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.case_status='162743' AND cv19_case_inv.case_status_date BETWEEN ? AND ? ");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Covid19Cases> getCasesData2(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.case_status='166428' AND cv19_case_inv.case_status_date BETWEEN ? AND ? ");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Covid19Cases> getCasesData4(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.case_status='162863' AND cv19_case_inv.case_status_date BETWEEN ? AND ? ");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Covid19Cases> getCasesData6(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.poe_screened='1' AND cv19_case_inv.case_status_date BETWEEN ? AND ? ");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Covid19Cases> getCasesData8(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.on_admission='1' AND cv19_case_inv.case_status_date BETWEEN ? AND ? ");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Covid19Cases> getCasesData10(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.case_status='166428' AND cv19_case_inv.status_d_a='160432' AND cv19_case_inv.case_status_date BETWEEN ? AND ? ");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Covid19Cases> getCasesData12(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.case_status='162743' AND cv19_case_inv.status_d_a='160432' AND cv19_case_inv.case_status_date BETWEEN ? AND ? ");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Covid19Cases> getCasesData38(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.symp_asymp='0' AND cv19_case_inv.poe_screened='1' AND cv19_case_inv.case_status_date BETWEEN ? AND ? ");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Covid19Cases> getCasesData39(String startDate, String endDate) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Covid19Cases> allPatients = new ArrayList();
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			StringBuilder queryString = new StringBuilder(
			        "SELECT cv19_case_id,patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp from cv19_case_inv \n"
			                + " where cv19_case_inv.symp_asymp='1' AND cv19_case_inv.poe_screened='1' AND cv19_case_inv.case_status_date BETWEEN ? AND ? ");
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(queryString.toString());
			stmt.setString(i++, startDate);
			stmt.setString(i++, endDate);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Covid19Cases tempPatient = new Covid19Cases();
				tempPatient.setCv19_case_id(rs.getInt("cv19_case_id"));
				tempPatient.setPatient_id(rs.getInt("patient_id"));
				tempPatient.setCase_id(rs.getString("case_id"));
				tempPatient.setPepfar_id(rs.getString("pepfar_id"));
				tempPatient.setGender(rs.getString("gender"));
				tempPatient.setDob(rs.getString("dob"));
				tempPatient.setHiv_diagnosis_date(rs.getString("hiv_diagnosis_date"));
				tempPatient.setArt_start_date(rs.getString("art_start_date"));
				tempPatient.setCase_status(rs.getInt("case_status"));
				tempPatient.setCase_status_date(rs.getString("case_status_date"));
				tempPatient.setPoe_screened(rs.getInt("poe_screened"));
				tempPatient.setOn_admission(rs.getInt("on_admission"));
				tempPatient.setStatus_d_a(rs.getInt("status_d_a"));
				tempPatient.setOccupation(rs.getInt("occupation"));
				tempPatient.setTyp_respiratory(rs.getInt("typ_respiratory"));
				tempPatient.setTyp_base_serum(rs.getInt("typ_base_serum"));
				tempPatient.setTyp_others(rs.getInt("typ_others"));
				tempPatient.setSymp_asymp(rs.getInt("symp_asymp"));
				
				allPatients.add(tempPatient);
				
			}
			return allPatients;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return new ArrayList();
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
}
