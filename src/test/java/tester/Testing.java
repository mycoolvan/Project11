package tester;


import org.junit.Test;

import junit.framework.TestCase;
import service.EmployeeService;

public class Testing extends TestCase {
	@Test
	public void EmployeeTest() {
		assertTrue(EmployeeService.getAllEmployee()!=null);
	}
}
   