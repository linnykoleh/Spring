package livelessons.xauth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
class XAuthTokenRestController {

	private final AuthenticationManager authenticationManager;

	private final UserDetailsService userDetailsService;

	XAuthTokenRestController(AuthenticationManager authenticationManager,
			UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping(value = "/authenticate")
	Map<String, Object> authenticate(@RequestParam String username,
			@RequestParam String password) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		Authentication authentication = this.authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails details = this.userDetailsService.loadUserByUsername(username);

		Map<String, Object> transfer = new HashMap<>();
		transfer.put("token", TokenUtils.createToken(details));
		transfer.put("username", details.getUsername());
		transfer.put("roles", details.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		return transfer;
	}

}
