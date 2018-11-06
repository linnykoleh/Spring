package com.learning.linnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

@SpringBootApplication
@EnableOAuth2Client
@RestController
public class OauthClientApplication {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Bean
    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
        final ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        details.setAccessTokenUri("http://localhost:9090/oauth/token");
        details.setClientId("webapp");
        details.setClientSecret("websecret");
        details.setGrantType("password");

        return details;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), new DefaultOAuth2ClientContext());
    }

    @RequestMapping("/execute")
    public String execute(Principal principal) throws URISyntaxException {
        final User user = (User) ((Authentication) principal).getPrincipal();
        final URI uri = new URI("http://localhost:7070/resource/endpoint");

        final RequestEntity<String> requestEntity = new RequestEntity<>(HttpMethod.GET, uri);
        final AccessTokenRequest accessTokenRequest = oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest();
        accessTokenRequest.set("username", user.getUsername());
        accessTokenRequest.set("password", user.getPassword());

        return oAuth2RestTemplate.exchange(requestEntity, String.class).getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(OauthClientApplication.class, args);
    }
}
