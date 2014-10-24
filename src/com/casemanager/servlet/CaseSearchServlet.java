package com.casemanager.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casemanager.data.CaseData;
import com.casemanager.domain.Case;

/**
 * Servlet implementation class CaseSearchServlet
 */
@WebServlet("/CaseSearchServlet")
public class CaseSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaseSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (islogin(request)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./searchcase.jsp");
			dispatcher.forward(request, response);
		} else {

			RequestDispatcher dispatcher = request.getRequestDispatcher("./LoginServlet");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		CaseData caseDAO = new CaseData();
		try {
			String type = request.getParameter("caseType");
			String patientName = request.getParameter("patient");
			String doctorName = request.getParameter("doctor");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipcode = request.getParameter("zipcode");
			String careProvider = request.getParameter("careProvider");

			System.out.println("type=" + type + "patient=" + patientName + " city=" + city);

			List<Case> caseSearchResult = caseDAO.searchCases(type, patientName, doctorName, city, state, zipcode);

			System.out.println(caseSearchResult);
			request.setAttribute("caseSearchResults", caseSearchResult);
			RequestDispatcher dispatcher = request.getRequestDispatcher("./searchresult.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean islogin(HttpServletRequest request) {
		Object login = request.getSession().getAttribute("login");
		if (login != null && (Boolean) login) {
			return true;
		}

		return false;
	}
}
