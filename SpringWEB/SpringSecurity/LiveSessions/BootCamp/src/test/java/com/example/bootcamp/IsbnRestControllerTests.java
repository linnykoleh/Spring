package com.example.bootcamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@Import(IsbnRestController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IsbnRestControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getByIsbnRequest() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/books/9781449374648"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.jsonPath("@.items.[0].id")
						.value("r1f6rQEACAAJ"))
				.andExpect(mvcResult -> {
					String contentAsString = mvcResult.getResponse().getContentAsString();

					System.out.println("result: " + contentAsString);
				});
	}

}
