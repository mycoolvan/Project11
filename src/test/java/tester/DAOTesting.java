package tester;

import static org.junit.Assert.*;

import org.junit.Test;

import service.EmployeeService;
import service.MessageService;
import service.RequestService;

public class DAOTesting {

	@Test
	public void EmployeeTest() {
		assertTrue(EmployeeService.getAllEmployee()!=null);
	}
	
	@Test
	public void loginTest() {
		assertTrue(EmployeeService.login("ceo", "pass").getEmptype().equals("CEO"));
	}

	@Test
	public void MessageTest() {
		assertTrue(MessageService.getRequestMessages(0)!=null);
	}
	@Test
	public void RequestTestingbyID() {
		assertTrue(RequestService.getRequests(0)!=null);
	}
	@Test
	public void RequestsTestingbyID() {
		assertTrue(RequestService.userRequest(0)!=null);
	}
	@Test
	public void RequestTestingAll() {
		assertTrue(RequestService.getAllRequests()!=null);
	}
	@Test
	public void RequestbyManaged() {
		assertTrue(RequestService.managedRequest("ceo", 0)!=null);
	}
	
}
