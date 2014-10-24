package com.casemanager.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.casemanager.domain.CareProvider;
import com.casemanager.domain.Case;
import com.casemanager.domain.CaseType;
import com.casemanager.domain.Doctor;
import com.casemanager.domain.PatientInfo;

public class CaseData {

	String doctor_search = "SELECT * from  DOCTORS";

	String careProvider_search = "SELECT * from CAREPROVIDERS";

	String patient_search = "SELECT PATIENTID from  PATIENTS where PARENTNAME = ? AND PARENTAGE = ? AND PATIENTCITY = ?";

	String case_search_select = "SELECT * from  casedetail c where c.casetype is not null ";

	String case_count_select = "SELECT casetype, count(*) as count from  cases c group by c.casetype";

	String delete_case = "delete from cases where caseid= ?";

	public void createCase(Case caseDomain) throws SQLException {

		CallableStatement createCaseProcedurestmt = null;
		Connection con = getConnection();

		try {

			// call the case create stored procedure
			createCaseProcedurestmt = con.prepareCall("{ call createCase(?, ?, ?, ?, ?, ?,?, ?, ?,?) }");

			createCaseProcedurestmt.setString(1, caseDomain.getType().name());
			createCaseProcedurestmt.setString(2, caseDomain.getDescription());
			createCaseProcedurestmt.setString(3, caseDomain.getPatientInfo().getName());
			createCaseProcedurestmt.setInt(4, caseDomain.getPatientInfo().getAge());
			createCaseProcedurestmt.setString(5, caseDomain.getPatientInfo().getGender());
			createCaseProcedurestmt.setString(6, caseDomain.getPatientInfo().getCity());
			createCaseProcedurestmt.setString(7, caseDomain.getPatientInfo().getState());
			createCaseProcedurestmt.setInt(8, caseDomain.getPatientInfo().getZip());
			createCaseProcedurestmt.setInt(9, caseDomain.getDoctor().getId());
			createCaseProcedurestmt.setInt(10, caseDomain.getCareProvider().getId());

			createCaseProcedurestmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (createCaseProcedurestmt != null) {
				createCaseProcedurestmt.close();
			}
			con.close();
		}
	}

	public void updateCase(Case caseDomain) throws SQLException {

		CallableStatement createCaseProcedurestmt = null;
		Connection con = getConnection();

		try {

			// call the case create stored procedure
			createCaseProcedurestmt = con.prepareCall("{ call updateCase(?, ?, ?, ?, ?, ?, ?,?, ?, ?,?) }");
			int i = 1;

			System.out.println("Id for the case : " + caseDomain.getId());
			createCaseProcedurestmt.setInt(1, caseDomain.getId());
			createCaseProcedurestmt.setString(2, caseDomain.getType().name());
			createCaseProcedurestmt.setString(3, caseDomain.getDescription());
			createCaseProcedurestmt.setString(4, caseDomain.getPatientInfo().getName());
			createCaseProcedurestmt.setInt(5, caseDomain.getPatientInfo().getAge());
			createCaseProcedurestmt.setString(6, caseDomain.getPatientInfo().getGender());
			createCaseProcedurestmt.setString(7, caseDomain.getPatientInfo().getCity());
			createCaseProcedurestmt.setString(8, caseDomain.getPatientInfo().getState());
			createCaseProcedurestmt.setInt(9, caseDomain.getPatientInfo().getZip());
			createCaseProcedurestmt.setInt(10, caseDomain.getDoctor().getId());
			createCaseProcedurestmt.setInt(11, caseDomain.getCareProvider().getId());

			createCaseProcedurestmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (createCaseProcedurestmt != null) {
				createCaseProcedurestmt.close();
			}
			con.close();
		}
	}

	public void deleteCase(int caseId) throws SQLException {
		
		CallableStatement deleteCaseStmt = null;
		Connection con = getConnection();

		try {
			deleteCaseStmt = con.prepareCall("{ call deleteCase(?) }");

			deleteCaseStmt.setInt(1, caseId);
			deleteCaseStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (deleteCaseStmt != null) {
				deleteCaseStmt.close();
			}
			con.setAutoCommit(true);
		}

	}

	public List<Case> searchCases(String type, String patientName, String doctorName, String city, String state,
			String zipcode) throws SQLException {

		String case_search_where = "";
		if (type != null && !type.isEmpty()) {
			case_search_where = " and c.casetype = '" + type + "'";
		}

		if (patientName != null && !patientName.isEmpty()) {
			case_search_where = " and c.patientname = '" + patientName + "'";
		}

		if (city != null && !city.isEmpty()) {
			case_search_where = " and c.patientcity = '" + city + "'";
		}

		if (state != null && !state.isEmpty()) {
			case_search_where = " and c.patientstate = '" + state + "'";
		}

		if (zipcode != null && !zipcode.isEmpty()) {
			case_search_where = " and c.patientzip = '" + zipcode + "'";
		}

		if (doctorName != null && !doctorName.isEmpty()) {
			case_search_where = " and c.doctorName like '%" + doctorName + "%'";
		}

		String case_search_query = case_search_select + case_search_where;
		System.out.println(case_search_query);

		return searchCases(case_search_query);

	}

	public Case searchCases(int caseId) throws SQLException {

		String case_search_query = case_search_select + " and c.caseId = '" + caseId + "'";
		System.out.println(case_search_query);

		return searchCases(case_search_query).get(0);

	}

	public List<Case> searchCases(String caseSearchStatement) throws SQLException {

		List<Case> caseSearchResult = new ArrayList<Case>();
		PreparedStatement searchCase = null;
		Connection con = getConnection();

		try {
			con.setAutoCommit(false);
			searchCase = con.prepareStatement(caseSearchStatement);

			ResultSet rs = searchCase.executeQuery();

			while (rs.next()) {
				Case caseDomain = new Case();
				PatientInfo patient = new PatientInfo();
				Doctor doctor = new Doctor();
				CareProvider careProvider = new CareProvider();

				caseDomain.setId(rs.getInt("caseid"));
				caseDomain.setType(CaseType.valueOf(rs.getString("casetype")));
				caseDomain.setDescription(rs.getString("casedesc"));
				caseDomain.setCreateDate(rs.getDate("CaseCreateDate"));

				patient.setName(rs.getString("patientname"));
				patient.setAge(rs.getInt("patientage"));
				patient.setCity(rs.getString("patientcity"));
				patient.setZip(rs.getInt("patientzip"));
				caseDomain.setPatientInfo(patient);

				doctor.setName(rs.getString("doctorname"));
				caseDomain.setDoctor(doctor);
				careProvider.setName(rs.getString("careprovider"));
				caseDomain.setCareProvider(careProvider);

				caseSearchResult.add(caseDomain);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (searchCase != null) {
				searchCase.close();
			}
			con.setAutoCommit(true);
		}

		return caseSearchResult;

	}

	public int searchPatient(String patientName, int patientAge, String patientCity) throws SQLException {

		PreparedStatement searchPatientStmt = null;
		Connection con = getConnection();

		try {
			searchPatientStmt = con.prepareStatement(patient_search);

			searchPatientStmt.setString(1, patientName);
			searchPatientStmt.setInt(2, patientAge);
			searchPatientStmt.setString(3, patientCity);

			ResultSet rs = searchPatientStmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (searchPatientStmt != null) {
				searchPatientStmt.close();
			}
			con.setAutoCommit(true);
		}

		return -1;
	}

	public HashMap<String, String> getCaseCountByGroup() throws SQLException {

		HashMap<String, String> returnResult = new HashMap<String, String>();
		PreparedStatement caseCountStmt = null;
		Connection con = getConnection();

		try {
			caseCountStmt = con.prepareStatement(case_count_select);
			ResultSet rs = caseCountStmt.executeQuery();

			while (rs.next()) {
				returnResult.put(rs.getString("casetype"), rs.getString("count"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (caseCountStmt != null) {
				caseCountStmt.close();
			}
			con.setAutoCommit(true);
		}

		return returnResult;
	}

	private Connection getConnection() throws SQLException {
		DataUtil dbutil = new DataUtil();
		return dbutil.getConnection();
	}

}
