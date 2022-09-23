/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openmrs.module.covid19.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.SQLQuery;
//import org.openmrs.module.dataquality.Constants;
//import org.openmrs.module.dataquality.Misc;

/**
 * @author ihvn
 */
public class PersonDao {
	
	public int getTotalPersons() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = "SELECT COUNT(person.person_id) AS count  FROM person WHERE person.voided=0 ";
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			rs.next();
			int totalCount = rs.getInt("count");
			return totalCount;
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return 0;
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Map<String, String>> getAllPatients(int limit, int offset, String lastUpdateDate) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Map<String, String>> allPatients = new ArrayList();
		
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = " SELECT patient_id FROM patient  WHERE patient.voided=0 AND (patient.date_created >=? OR patient.date_changed >=? ";
			query += " OR " + " " + "   patient_id  in (SELECT person_id FROM obs WHERE date_created >= ? ) OR "
			        + "   patient_id IN (SELECT patient_id FROM encounter WHERE date_created >=? OR date_changed >=?) "
			        + ")";
			query += " ORDER BY patient_id LIMIT " + offset + ", " + limit;
			
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//stmt.setFetchSize(200);
			stmt.setString(i++, lastUpdateDate);
			stmt.setString(i++, lastUpdateDate);
			stmt.setString(i++, lastUpdateDate);
			stmt.setString(i++, lastUpdateDate);
			stmt.setString(i++, lastUpdateDate);
			
			rs = stmt.executeQuery();
			rs.setFetchSize(limit);
			while (rs.next()) {
				Map<String, String> tempMap = new HashMap();
				tempMap.put("patient_id", rs.getString("patient_id"));
				allPatients.add(tempMap);
			}
			return allPatients;
			
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return null;
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public List<Map<String, String>> getCovid19Cases(int patientId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Map<String, String>> allPatients = new ArrayList();
		
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = "select obs.person_id as patient_id, MAX(IF(pid3.identifier_type=13, pid3.identifier, NULL)) as  'case_id',\n"
			        + " MAX(IF(pid3.identifier_type=4, pid3.identifier, NULL)) as  'pepfar_id', pers.gender AS 'gender', DATE_FORMAT(pers.birthdate,'%Y-%m-%d') AS 'dob' "
			        + " ,DATE_FORMAT(MAX(IF(obs_epi.concept_id=160554,obs_epi.value_datetime, NULL)),'%Y-%m-%d') AS 'hiv_diagnosis_date' "
			        + " ,DATE_FORMAT(MAX(IF(obs_epi.concept_id=159599,obs_epi.value_datetime, NULL)),'%Y-%m-%d') as  'art_start_date', MAX(IF(obs.concept_id=163088, obs.value_coded, NULL)) as  'case_status' "
			        + " ,DATE_FORMAT(encounter.encounter_datetime,'%Y-%m-%d') as 'case_status_date' "
			        + " ,MAX(IF(obs.concept_id in (140238,158843,113224,143264,141600,122983,5978,142412,124628,120749,127777,135588,139084,135589,118998,165613), 1, 0)) as  'poe_screened' "
			        + " ,MAX(IF(obs.concept_id IN (166443,166451,166454,166455,166456) and obs.value_coded=1065 and obs.voided=0, 1, 0)) AS 'on_admission' "
			        + " ,MAX(IF(obs.concept_id IN (166491) and obs.voided=0, obs.value_coded, NULL)) AS 'status_d_a', MAX(IF(obs.concept_id IN (1542) and obs.voided=0, obs.value_coded, NULL)) AS 'occupation' "
			        + " ,MAX(IF(obs.concept_id IN (166438) and obs.voided=0, obs.value_coded, NULL)) AS 'typ_respiratory' "
			        + " ,MAX(IF(obs.concept_id IN (166439) and obs.value_coded=1065 and obs.voided=0, 1, 0)) AS 'typ_base_serum' "
			        + " ,MAX(IF(obs.concept_id IN (166441) and obs.value_coded=1065 and obs.voided=0, 1, 0)) AS 'typ_others'  "
			        + " ,MAX(IF(obs.concept_id IN (140238,158843,113224,143264,141600,122983,5978,142412,124628,120749,127777,135588,139084,135589,118998,165613) and obs.value_coded=1065 and obs.voided=0, 1, 0)) AS 'symp_asymp'  "
			        + " from obs "
			        + " left join patient_identifier pid3 on(pid3.patient_id=obs.person_id and pid3.identifier_type in (13,4) and pid3.voided=0) "
			        + " left join encounter on(encounter.encounter_id=obs.encounter_id and encounter.voided=0) "
			        + " left join person pers ON (pers.person_id = obs.person_id and pers.voided=0) "
			        + " left join patient_program pp1 on(pp1.patient_id=pers.person_id and pp1.program_id=4 and pp1.voided=0) "
			        + " left join patient_program pp2 on(pp2.patient_id=pers.person_id and pp2.program_id=6 and pp2.voided=0) "
			        
			        + " left join obs obs_epi on (obs_epi.person_id=pers.person_id and obs_epi.concept_id in (160554, 159599) and obs_epi.voided=0) ";
			
			query += " where encounter.form_id in(79) and encounter.voided=0 and obs.voided=0 GROUP BY obs.encounter_id order by obs.encounter_id; ";
			
			int i = 1;
			//DateTime now = new DateTime(new Date());
			//String nowString = now.toString("yyyy'-'MM'-'dd' 'HH':'mm");
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//stmt.setInt(i++, patientId);
			//stmt.setFetchSize(200);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map<String, String> tempMap = new HashMap();
				tempMap.put("patient_id", rs.getString("patient_id"));
				tempMap.put("case_id", rs.getString("case_id"));
				tempMap.put("pepfar_id", rs.getString("pepfar_id"));
				tempMap.put("gender", rs.getString("gender")/*Misc.getEducationalStatus(rs.getInt("educational_staus"))*/);
				tempMap.put("dob", rs.getString("dob")/*Misc.getOccupationalStatus(rs.getInt("occupation_status"))*/);
				tempMap.put("hiv_diagnosis_date", rs.getString("hiv_diagnosis_date")/*Misc.getCivilStatus(rs.getInt("marital_status"))*/);
				tempMap.put("art_start_date", rs.getString("art_start_date"));
				tempMap.put("case_status", rs.getString("case_status"));
				tempMap.put("case_status_date", rs.getString("case_status_date"));
				tempMap.put("poe_screened", rs.getString("poe_screened"));
				tempMap.put("on_admission", rs.getString("on_admission"));
				tempMap.put("status_d_a", rs.getString("status_d_a"));
				tempMap.put("occupation", rs.getString("occupation"));
				tempMap.put("typ_respiratory", rs.getString("typ_respiratory"));
				tempMap.put("typ_base_serum", rs.getString("typ_base_serum"));
				tempMap.put("typ_others", rs.getString("typ_others"));
				tempMap.put("symp_asymp", rs.getString("symp_asymp"));
				allPatients.add(tempMap);
			}
			return allPatients;
			
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return null;
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public int saveCovid19Cases(List<Map<String, String>> allPatientMetas) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = "INSERT INTO cv19_case_inv (patient_id,case_id,pepfar_id,gender,dob,hiv_diagnosis_date,art_start_date,case_status,case_status_date,poe_screened,on_admission,status_d_a,occupation,typ_respiratory,typ_base_serum,typ_others,symp_asymp)VALUES";
			for (int i = 0; i < allPatientMetas.size(); i++) {
				query += "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),";
			}
			
			query = query.substring(0, query.length() - 1);
			//System.out.println("queryyyyyyyyyyyyyyyy: " + query);
			query += " ON DUPLICATE KEY UPDATE patient_id=VALUES(patient_id), pepfar_id=VALUES(pepfar_id), gender=VALUES(gender), ";
			query += " dob=VALUES(dob), hiv_diagnosis_date=VALUES(hiv_diagnosis_date), art_start_date=VALUES(art_start_date), case_status=VALUES(case_status), case_status_date=VALUES(case_status_date),  ";
			query += " poe_screened=VALUES(poe_screened), on_admission=VALUES(on_admission), status_d_a=VALUES(status_d_a), occupation=VALUES(occupation), typ_respiratory=VALUES(typ_respiratory), typ_base_serum=VALUES(typ_base_serum), typ_others=VALUES(typ_others), symp_asymp=VALUES(symp_asymp) ";
			
			int i = 1;
			stmt = con.prepareStatement(query);
			for (int j = 0; j < allPatientMetas.size(); j++) {
				stmt.setInt(i++, Integer.parseInt(allPatientMetas.get(j).get("patient_id")));
				stmt.setString(i++, allPatientMetas.get(j).get("case_id"));
				stmt.setString(i++, allPatientMetas.get(j).get("pepfar_id"));
				stmt.setString(i++, allPatientMetas.get(j).get("gender"));
				stmt.setString(i++, allPatientMetas.get(j).get("dob"));
				stmt.setString(i++, allPatientMetas.get(j).get("hiv_diagnosis_date"));
				stmt.setString(i++, allPatientMetas.get(j).get("art_start_date"));
				stmt.setString(i++, allPatientMetas.get(j).get("case_status"));
				stmt.setString(i++, allPatientMetas.get(j).get("case_status_date"));
				stmt.setString(i++, allPatientMetas.get(j).get("poe_screened"));
				stmt.setString(i++, allPatientMetas.get(j).get("on_admission"));
				stmt.setString(i++, allPatientMetas.get(j).get("status_d_a"));
				stmt.setString(i++, allPatientMetas.get(j).get("occupation"));
				stmt.setString(i++, allPatientMetas.get(j).get("typ_respiratory"));
				stmt.setString(i++, allPatientMetas.get(j).get("typ_base_serum"));
				stmt.setString(i++, allPatientMetas.get(j).get("typ_others"));
				stmt.setString(i++, allPatientMetas.get(j).get("symp_asymp"));
				
			}
			//stmt.setFetchSize(200);
			System.out.println("allPatientMetas hereeeeeeeeeeeeeeeeeeeeeeee: " + allPatientMetas);
			if (allPatientMetas.size() > 0)
				stmt.executeUpdate();
			
			return 1;
			
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return 0;
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public String getGlobalProperty(String property) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = "SELECT global_property.property_value FROM global_property WHERE property=?";
			
			int i = 1;
			stmt = con.prepareStatement(query);
			stmt.setString(i++, property);
			rs = stmt.executeQuery();
			rs.next();
			
			return rs.getString("property_value");
			
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			return "";
			
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
	public void saveGlobalProperty(String property, String propertyValue, String description, String uuid) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con = Database.connectionPool.getConnection();
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			//stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String query = "INSERT INTO global_property (property, property_value, description, uuid)VALUES(?, ?, ?, ?)";
			
			int i = 1;
			stmt = con.prepareStatement(query);
			stmt.setString(i++, property);
			stmt.setString(i++, propertyValue);
			stmt.setString(i++, description);
			stmt.setString(i++, uuid);
			
			stmt.executeUpdate();
			
		}
		catch (SQLException ex) {
			Database.handleException(ex);
			
		}
		finally {
			Database.cleanUp(rs, stmt, con);
		}
	}
	
}
