package livelessons;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class FileUploadTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void csrfProtectionEnabled() throws Exception {
		this.mockMvc.perform(multipart("/upload").file(new MockMultipartFile("name", "the-file".getBytes())))
			.andExpect(status().isForbidden());
	}
}
