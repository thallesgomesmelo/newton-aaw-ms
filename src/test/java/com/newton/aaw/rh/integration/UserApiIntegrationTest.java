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

import com.newton.aaw.rh.controller.UserController;
import com.newton.aaw.rh.domain.entity.User;
import com.newton.aaw.rh.exception.NotFoundException;
import com.newton.aaw.rh.service.UserService;

@WebMvcTest(UserController.class)
public class UserApiIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	void test_getById_withInvalidId_souldReturn404( ) throws Exception {
		//Given
		var id = "0001";
		
		//Mock definitions
		Mockito.when(userService.get(id)).thenThrow(new NotFoundException("Not Found"));
		
		//Test//Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/users/" + id))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		
		//Verify
		Mockito.verify(userService).get(id);
	}
	
	@Test
	void test_getById_withValidId_shouldReturn202() throws Exception {
		//Given
		var id = "0001";
		
		//Mock definitions
			Mockito.when(userService.get(id)).thenReturn(new User());
				
		//test//Assert
			mockMvc.perform(MockMvcRequestBuilders.get("/users/" + id))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isOk());
				
		//Verify
		Mockito.verify(userService).get(id);
	}
	
	@Test
	void test_delete_withInvalidId_shouldReturn404() throws Exception {
		//Given
		var id = "0001";
		
		//Mock definitions
		Mockito.doThrow(new NotFoundException("Not Found")).when(userService).delete(id);
		
		//test//Assert
				mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + id))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isNotFound());
				
		//Verify
		Mockito.verify(userService).delete(id);
	}
	
	@Test
	void test_delete_withValidId_shouldReturn202() throws Exception {
		//Given
		var id = "0001";
				
		//test//Assert
			mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + id))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());
				
		//Verify
		Mockito.verify(userService).delete(id);
	}
	
	@Test
	void test_getAll_returnOk() throws Exception {
		//Given
		var list = new ArrayList<User>();
		list.add(new User());
		
		//Mock
		Mockito.when(userService.getAll()).thenReturn(list);
		
		//Test//Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/users/"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk());
		
		//Verify
		Mockito.verify(userService).getAll();
	}
	
	@Test
	void test_create_returnOk() {
		//Given
		var user = new User();
		
		//Mock
		Mockito.when(userService.create(user)).thenReturn(user);
		
//		//Test
//		mockMvc.perform(MockMvcRequestBuilders.(user))
//			.andDo(MockMvcResultHandlers.print())
//			.andExpect(MockMvcResultMatchers.status().isOk());

		//Verify
//		Mockito.verify(userService).create(new User());
		
	}
	
	@Test
	void test_update() throws Exception {
		//Given
		var user = new User();
		var id = "0001";
				
		//Mock
		Mockito.when(userService.get(id)).thenReturn(user);
		
		//Test
		mockMvc.perform(MockMvcRequestBuilders.get("/users/" + id))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk());
		
		//Verify
		//Mockito.verify(userService).update(id, user);
	}

}
