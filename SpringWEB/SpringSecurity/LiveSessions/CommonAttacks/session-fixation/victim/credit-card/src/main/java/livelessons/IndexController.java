package livelessons;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/login")
	String login() {
		return "login";
	}

}
