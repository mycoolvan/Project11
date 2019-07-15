package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Message;
import util.JDBCConnection;

public class MessageDAO implements IMessage{
	
	public static Connection conn = JDBCConnection.getConnection();

	public List<Message> getAllMessages() {
		
		List<Message> messages = new ArrayList<Message>();
		try {
			String sql = "SELECT * FROM messages ORDER BY msgdate";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next())
			{
				messages.add(new Message(
						rs.getInt("msgid"),
						rs.getInt("reqid"),
						rs.getString("touser"),
						rs.getString("fromuser"),
						rs.getString("unread").equals("0"),
						rs.getString("msg"),
						rs.getString("msgdate")));
			}
			
			rs.close();

			
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return messages;
	}

	public boolean sendMsg(Message m) {
		try {
			String sql = "CALL addMessage(? , ? , ? , ?)";
			//sql = "CALL addRequest(5, null, null, 200, null, null, null, null, 1)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, Integer.toString(m.getReqid()));
			cs.setString(2, m.gettouser());
			cs.setString(3, m.getfromuser());
			cs.setString(4, m.getMsg());

			
			
			cs.execute();
			cs.close();
			return true;
			
		}catch (SQLIntegrityConstraintViolationException e)
		{
			System.out.println("lol brah");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
