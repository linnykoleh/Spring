package livelessons.xauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class XAuthDslConfigurer
		extends AbstractHttpConfigurer<XAuthDslConfigurer, HttpSecurity> {

	@Override
	public void init(HttpSecurity builder) throws Exception {
		builder.httpBasic().and().csrf().disable();
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		ApplicationContext context = builder.getSharedObject(ApplicationContext.class);
		XAuthTokenFilter xAuthTokenFilter = context.getBean(XAuthTokenFilter.class);
		builder.addFilterBefore(xAuthTokenFilter,
				UsernamePasswordAuthenticationFilter.class);
	}

}
