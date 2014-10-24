package com.casemanager.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.casemanager.domain.Note;

public class NoteData {

	public static String save_note_stmt = " Insert into Notes(CaseID,NoteText,NoteCreateDate) Values (?,?,?)";
	public static String search_note_stmt = " select * from notes where caseId =  ?";

	public void createNote(Note noteDomain) throws SQLException {

		PreparedStatement createNoteStmt = null;
		Connection con = getConnection();

		try {
			createNoteStmt = con.prepareStatement(save_note_stmt);
			createNoteStmt.setInt(1, noteDomain.getCaseId());
			createNoteStmt.setString(2, noteDomain.getNoteText());
			createNoteStmt.setDate(3, new Date(System.currentTimeMillis()));

			createNoteStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (createNoteStmt != null) {
				createNoteStmt.close();
			}
			con.close();
		}
	}

	public List<Note> searchNotes(int caseId) throws SQLException {

		List<Note> noteSearchResult = new ArrayList<Note>();
		PreparedStatement searchNote = null;
		Connection con = getConnection();

		try {
			con.setAutoCommit(false);
			searchNote = con.prepareStatement(search_note_stmt);
			searchNote.setInt(1, caseId);
			ResultSet rs = searchNote.executeQuery();

			while (rs.next()) {
				Note noteDomain = new Note();
				noteDomain.setCaseId(caseId);
				noteDomain.setNoteText(rs.getString("NoteText"));
				noteDomain.setCreateDate(rs.getDate("NoteCreateDate"));

				noteSearchResult.add(noteDomain);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (searchNote != null) {
				searchNote.close();
			}
			con.setAutoCommit(true);
		}

		return noteSearchResult;

	}

	private Connection getConnection() throws SQLException {
		DataUtil dbutil = new DataUtil();
		return dbutil.getConnection();
	}

}
