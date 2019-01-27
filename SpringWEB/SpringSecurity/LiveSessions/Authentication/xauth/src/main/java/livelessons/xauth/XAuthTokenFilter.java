package livelessons.xauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Slf4j
class XAuthTokenFilter extends GenericFilterBean {

	private final UserDetailsService detailsService;

	private String xAuthTokenHeaderName;

	XAuthTokenFilter(UserDetailsService userDetailsService) {
		this(userDetailsService, "x-auth-token");
	}

	XAuthTokenFilter(UserDetailsService userDetailsService, String xAuthTokenHeaderName) {
		this.detailsService = userDetailsService;
		this.xAuthTokenHeaderName = xAuthTokenHeaderName;
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(arg0);
		String at = httpServletRequest.getHeader(this.xAuthTokenHeaderName);
		Optional.ofNullable(at).ifPresent(authToken -> {
			UserDetails details = this.detailsService
					.loadUserByUsername(TokenUtils.getUserNameFromToken(authToken));
			if (TokenUtils.validateToken(authToken, details)) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
						details, details.getPassword(), details.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		});
		filterChain.doFilter(arg0, arg1);
	}

}
