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
public class PatientDao {
	
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
