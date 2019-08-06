package br.com.luizalabs.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.luizalabs.entity.EmployeeRestParameter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test 
	public void getEmployees_thenReturnStatusNotFound() throws Exception {
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/employee").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}
	
	@Test
	public void postEmployee_thenReturnStatusCreated() throws Exception {
		EmployeeRestParameter param = EmployeeRestParameter.builder().id(0L).department("TI")
				.email("ti@luizalabs.com.br").name("new Employee").build();

		this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(param.toJson()))
				.andDo(print()).andExpect(status().isCreated());

	}

	@Test
	public void postEmployee_thenReturnStatusBadRequestRequiredAllFields() throws Exception {
		EmployeeRestParameter param = EmployeeRestParameter.builder().build();

		this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(param.toJson()))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").isArray())
				.andExpect(jsonPath("$.errors", hasItem("Please provide a name")))
				.andExpect(jsonPath("$.errors", hasItem("Please provide a email")))
				.andExpect(jsonPath("$.errors", hasItem("Please provide a department")));

	}
	
	@Test
	public void postEmployee_thenReturnStatusRequiredFieldEmailDepartment() throws Exception {
		EmployeeRestParameter param = EmployeeRestParameter.builder().name("LuizaLabs").build();

		this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(param.toJson()))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").isArray())
				.andExpect(jsonPath("$.errors", hasItem("Please provide a email")))
				.andExpect(jsonPath("$.errors", hasItem("Please provide a department")));

	}
	
	@Test
	public void postEmployee_thenReturnStatusRequiredFieldDepartment() throws Exception {
		EmployeeRestParameter param = EmployeeRestParameter.builder().name("LuizaLabs").email("luizaLabs@luizalabs.com").build();

		this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(param.toJson()))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").isArray())
				.andExpect(jsonPath("$.errors", hasItem("Please provide a department")));

	}
	
	@Test
	public void postEmployee_thenReturnStatusBadRequestEmailSintax() throws Exception {
		EmployeeRestParameter param = EmployeeRestParameter.builder().name("LuizaLabs").email("luizaLabs.com").department("Retail").build();

		this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(param.toJson()))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").isArray())
				.andExpect(jsonPath("$.errors", hasItem("Email sintax is not valid")));

	}

	@Test
	public void deleteEmployee_thenReturnStatusSucess() throws Exception {

		EmployeeRestParameter param = EmployeeRestParameter.builder().id(0L).department("TI")
				.email("ti@luizalabs.com.br").name("new Employee").build();

		this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(param.toJson()))
				.andDo(print()).andExpect(status().isCreated());

		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/employee/{id}", "1").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void deleteEmployee_thenReturnStatusNoContent() throws Exception {
		this.mockMvc
				.perform(
						MockMvcRequestBuilders.delete("/employee/{id}", "1111").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNoContent());

	}

	@Test
	public void getEmployees_thenReturnStatusOK() throws Exception {
		
		EmployeeRestParameter param = EmployeeRestParameter.builder().id(0L).department("TI")
				.email("ti@luizalabs.com.br").name("new Employee").build();

		this.mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(param.toJson()))
				.andDo(print()).andExpect(status().isCreated());
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/employee").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
}