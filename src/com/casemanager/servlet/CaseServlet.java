package com.casemanager.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casemanager.data.CaseData;
import com.casemanager.domain.CareProvider;
import com.casemanager.domain.Case;
import com.casemanager.domain.CaseType;
import com.casemanager.domain.Doctor;
import com.casemanager.domain.PatientInfo;

/**
 * Servlet implementation class CaseServlet
 */
@WebServlet("/CaseServlet")
public class CaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet.....");

		RequestDispatcher dispatcher = request.getRequestDispatcher("./createcase.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		System.out.println("doPost..............");

		saveCase(request);

		RequestDispatcher dispatcher = request.getRequestDispatcher("./caseSuccess.jsp");
		dispatcher.forward(request, response);
	}

	private void saveCase(HttpServletRequest request) throws ServletException {
		Case caseDomain = createCaseFromRequest(request);
		CaseData caseDAO = new CaseData();
		try {
			caseDAO.createCase(caseDomain);
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		throw new ServletException("Case can't  be created");
	}

	private Case createCaseFromRequest(HttpServletRequest request) {
		Case caseDomain = new Case();
		caseDomain.setType(CaseType.valueOf(request.getParameter("caseType")));
		caseDomain.setDescription(request.getParameter("caseDesc"));
		caseDomain.setPatientInfo(createPatient(request));
		caseDomain.setDoctor(createDoctor(request));
		caseDomain.setCareProvider(createCareProvider(request));

		return caseDomain;
	}

	private PatientInfo createPatient(HttpServletRequest request) {
		PatientInfo patientInfo = new PatientInfo();
		System.out.println("patientName=" + request.getParameter("patientName"));
		System.out.println("patientAge=" + request.getParameter("patientAge"));

		patientInfo.setName(request.getParameter("patientName"));
		patientInfo.setAge(Integer.parseInt(request.getParameter("patientAge")));
		patientInfo.setGender(request.getParameter("patientGender"));
		patientInfo.setCity(request.getParameter("patientCity"));
		patientInfo.setState(request.getParameter("patientSate"));
		patientInfo.setZip(Integer.parseInt(request.getParameter("patientZip")));
		return patientInfo;

	}

	private Doctor createDoctor(HttpServletRequest request) {
		Doctor doctor = new Doctor();

		doctor.setId(Integer.parseInt(request.getParameter("caseDoctor")));
		return doctor;
	}

	private CareProvider createCareProvider(HttpServletRequest request) {
		CareProvider careProvider = new CareProvider();

		careProvider.setId(Integer.parseInt(request.getParameter("careProvider")));
		return careProvider;

	}
	
	private boolean islogin(HttpServletRequest request) {
		Object login = request.getSession().getAttribute("login");
		if (login != null && (Boolean) login) {
			return true;
		}

		return false;
	}

}
