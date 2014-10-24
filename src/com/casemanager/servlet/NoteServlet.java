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

import com.casemanager.data.NoteData;
import com.casemanager.domain.Note;

/**
 * Servlet implementation class NoteServlet
 */
@WebServlet("/NoteServlet")
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet.....");

		request.setAttribute("caseId", request.getParameter("caseId"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("./addnote.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("doPost..............");

		List<Note> noteSearchResult = saveSearchNote(request);

		System.out.println(noteSearchResult);
		request.setAttribute("noteSearchResult", noteSearchResult);
		request.setAttribute("caseId", request.getParameter("caseId"));

		RequestDispatcher dispatcher = request.getRequestDispatcher("./addnote.jsp");
		dispatcher.forward(request, response);
	}

	private List<Note> saveSearchNote(HttpServletRequest request) throws ServletException {
		Note noteDomain = createNoteRequest(request);
		NoteData noteDAO = new NoteData();
		try {
			noteDAO.createNote(noteDomain);

			List<Note> noteSearchResult = noteDAO.searchNotes(noteDomain.getCaseId());
			return noteSearchResult;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		throw new ServletException("Note can't  be created");
	}

	private Note createNoteRequest(HttpServletRequest request) {
		Note noteDomain = new Note();
		System.out.println(request.getParameter("caseId"));
		System.out.println("noteText=" + request.getParameter("noteText"));
		noteDomain.setCaseId(Integer.parseInt(request.getParameter("caseId")));
		noteDomain.setNoteText(request.getParameter("noteText"));

		return noteDomain;
	}

	private boolean islogin(HttpServletRequest request) {
		Object login = request.getSession().getAttribute("login");
		if (login != null && (Boolean) login) {
			return true;
		}

		return false;
	}
}
