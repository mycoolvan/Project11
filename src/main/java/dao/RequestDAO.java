package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Request;
import service.MessageService;
import util.JDBCConnection;

public class RequestDAO implements IRequest{
	public static Connection conn = JDBCConnection.getConnection();

	public List<Request> getAllRequest() {
		List<Request> requests = new ArrayList<Request>();
		try {
			String sql = "SELECT * FROM request ORDER BY reqexp";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next())
			{
				
				Request r = new Request(
						rs.getInt("reqid"), 
						rs.getInt("empid"), 
						rs.getString("reqdate"), 
						rs.getString("reqexp"), 
						rs.getString("reqdesc"), 
						rs.getDouble("cost"), 
						rs.getString("gradef"), 
						rs.getString("gradestatus"),
						rs.getString("eventtype"), 
						rs.getString("workjust"), 
						rs.getInt("status"), 
						rs.getString("remark"),
						null
						
						);
				r.setMessages(MessageService.getRequestMessages(rs.getInt("reqid")));
				requests.add(r);
				
			}
			
			rs.close();
			
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return requests;
	}

	public boolean addRequest(Request r) {
		try {
			String sql = "CALL addRequest(?, ? , ? , ? , ? , ? , ? , ? , ?)";
			//sql = "CALL addRequest(5, null, null, 200, null, null, null, null, 1)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, Integer.toString(r.getEmpid()));
			cs.setString(2, r.getReqdate());
			cs.setString(3, r.getReqdesc());
			cs.setString(4, Double.toString(r.getCost()));
			cs.setString(5, r.getGradef());
			cs.setString(6, r.getGradestatus());
			cs.setString(7, r.getEventtype());
			cs.setString(8, r.getWorkjust());
			cs.setString(9, Integer.toString(r.getStatus()));
			
			
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

	public boolean acceptRequest(int requestid, int status, String remark) {
		String sql = "UPDATE request SET status = ? , remark = ? WHERE reqid = ?";
		try {
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, Integer.toString(status));
		ps.setString(2, remark);
		ps.setString(3,Integer.toString(requestid));
		
		ps.executeQuery();
		ps.close();
		return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean denyRequest(int requestid) {
		// TODO Auto-generated method stub
		return false;
	}
 
}
