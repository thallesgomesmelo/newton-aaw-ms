package com.newton.aaw.rh.integration;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.newton.aaw.rh.controller.EmployeeController;
import com.newton.aaw.rh.domain.entity.Employee;
import com.newton.aaw.rh.exception.NotFoundException;
import com.newton.aaw.rh.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeApiIntegrationTest { //Class vai test a camada rest da Api

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Test
	void test_getById_withInvalidId_shouldReturn404() throws Exception {
		//Given
		var id = "0001";
		
		//Mock definitions
		Mockito.when(employeeService.get(id)).thenThrow(new NotFoundException("Not Found"));
		
		//test//Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/" + id))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
		
		//Verify
		Mockito.verify(employeeService).get(id);
	}
	
	@Test
	void test_getById_withValidId_shouldReturn202() throws Exception {
		//Given
		var id = "0001";
		
		//Mock definitions
		Mockito.when(employeeService.get(id)).thenReturn(new Employee().withId(id));
		
		//test//Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/" + id))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		//Verify
		Mockito.verify(employeeService).get(id);
	}
	
	@Test
	void test_delete_withValidId_shouldReturn204() throws Exception {
		//Given
		var id = "0001";
		
		//test//Assert
		mockMvc.perform(MockMvcRequestBuilders.delete("/employees/" + id))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		
		//Verify
		Mockito.verify(employeeService).delete(id);
	}
	
	@Test
	void test_delete_withInvalidId_shouldReturn404() throws Exception {
		//Given
		var id = "0001";
				
		//Mock definitions //Lancando uma execao para um metodo VOID
		Mockito.doThrow(new NotFoundException("Not Found")).when(employeeService).delete(id);;
		
		//test//Assert
		mockMvc.perform(MockMvcRequestBuilders.delete("/employees/" + id))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound());
		
		//Verify
		Mockito.verify(employeeService).delete(id);
	}
	
	@Test
	void test_getAll_returnOk() throws Exception {
		//Given
		var list = new ArrayList<Employee>();
		list.add(new Employee());
		
		//Mock
		Mockito.when(employeeService.getAll()).thenReturn(list);
		
		//Test//Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk());
		
		//Verify
		Mockito.verify(employeeService).getAll();
	}
}
