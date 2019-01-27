package livelessons;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@GetMapping
	public String index() {
		return "index";
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) {
		return "redirect:/?success";
	}

}
