package livelessons.xauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(XAuthConfiguration.class)
public class XAuthAutoConfiguration {

}
