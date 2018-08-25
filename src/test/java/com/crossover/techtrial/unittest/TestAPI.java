package com.crossover.techtrial.unittest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestAPI extends CrossSolarApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getDailyPanels() throws Exception {
		mockMvc.perform(get("/api/panels/WER123/daily/")).andExpect(status().isOk());
	}
	
	@Test
	public void getHourlyPanels() throws Exception {
		mockMvc.perform(get("/api/panels/WER123/hourly/")).andExpect(status().isOk());
	}

	


}
