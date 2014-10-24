package com.casemanager.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casemanager.data.CaseData;

/**
 * Servlet implementation class CaseHome
 */
@WebServlet("/CaseHomeServlet")
public class CaseHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaseHomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CaseHomeServlet doGet.....");
		try {
			if (islogin(request)) {
				CaseData caseDAO = new CaseData();
				HashMap<String, String> caseCount = caseDAO.getCaseCountByGroup();
				System.out.println(caseCount);
				request.setAttribute("caseCount", caseCount);
				RequestDispatcher dispatcher = request.getRequestDispatcher("./casehome.jsp");
				dispatcher.forward(request, response);
				return;
			} else {

				RequestDispatcher dispatcher = request.getRequestDispatcher("./LoginServlet");
				dispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean islogin(HttpServletRequest request) {
		System.out.println("*****");
		Object login = request.getSession().getAttribute("login");
		System.out.println(login);
		if (login != null && (Boolean) login) {
			
			return true;
		}

		return false;
	}
}
