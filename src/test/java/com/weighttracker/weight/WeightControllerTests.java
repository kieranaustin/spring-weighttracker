package com.weighttracker.weight;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.weighttracker.weight.WeightRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@WebMvcTest(WeightController.class)
class WeightControllerTests
{

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeightRepository weightRepository;
	
	private Weight weight;
	private final Integer WEIGHT_ID = 42;
	
	@BeforeEach
	void setup()
	{
		weight = new Weight();
		weight.setId(WEIGHT_ID);
		weight.setDate(LocalDate.of(2002, 12, 31));
		weight.setTime(LocalTime.of(9, 32));
		weight.setValue(76.5f);
	}

	@Test
	void testAddNewWeight() throws Exception
	{
		mockMvc.perform(
			post("/weights/add")
				.param("date", "2003-11-30")
				.param("time", "08:01")
				.param("weight", "73.1")
			)
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/weights/list.html"));
	}
}
