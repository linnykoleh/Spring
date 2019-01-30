package livelessons;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CreditCardController {
	private Map<String, String> principalNameToCreditCardNumber = new HashMap<>();

	@PostMapping("/")
	String submit(Principal principal, @RequestParam String creditCardNumber) {
		this.principalNameToCreditCardNumber.put(principal.getName(),creditCardNumber);
		return "redirect:/?success";
	}

	@GetMapping("/")
	String index(Principal principal, Map<String, Object> model) {
		String username = principal.getName();
		String creditCardNumber = this.principalNameToCreditCardNumber
				.getOrDefault(username, "No Credit Cards!");
		model.put("creditCardNumber", creditCardNumber);
		model.put("username", username);
		return "index";
	}
}
