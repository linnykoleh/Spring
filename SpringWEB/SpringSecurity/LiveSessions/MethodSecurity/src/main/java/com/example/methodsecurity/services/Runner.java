package com.example.methodsecurity.services;

import java.util.function.Function;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.methodsecurity.entities.Authority;
import com.example.methodsecurity.entities.Message;
import com.example.methodsecurity.entities.User;
import com.example.methodsecurity.repositories.AuthorityRepository;
import com.example.methodsecurity.repositories.MessageRepository;
import com.example.methodsecurity.repositories.UserRepository;

import lombok.extern.log4j.Log4j2;

@Transactional
@Component
@Log4j2
public class Runner implements ApplicationRunner {

	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;
	private final MessageRepository messageRepository;
	private final UserDetailsService userDetailsService;

	Runner(UserRepository userRepository, AuthorityRepository authorityRepository, MessageRepository messageRepository,
			UserDetailsService userDetailsService) {
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
		this.messageRepository = messageRepository;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void run(ApplicationArguments args) {
		// install some data
		Authority user = this.authorityRepository.save(new Authority("USER"));
		Authority admin = this.authorityRepository.save(new Authority("ADMIN"));

		User josh = this.userRepository.save(new User("josh", "password", user));
		User rob = this.userRepository.save(new User("rob", "password", admin, user));

		Message messageForRob = this.messageRepository.save(new Message("hi Rob!", rob));
		this.messageRepository.save(new Message("Hi 1", rob));
		this.messageRepository.save(new Message("Hi 2", rob));
		this.messageRepository.save(new Message("Hi 1", josh));

		log.info("josh: " + josh.toString());
		log.info("rob: " + rob.toString());

		attemptAccess(rob.getEmail(), josh.getEmail(), messageForRob.getId(), this.messageRepository::findByIdRolesAllowed);

		attemptAccess(rob.getEmail(), josh.getEmail(), messageForRob.getId(), this.messageRepository::findByIdSecured);

		attemptAccess(rob.getEmail(), josh.getEmail(), messageForRob.getId(), this.messageRepository::findByIdPreAuthorize);

		attemptAccess(rob.getEmail(), josh.getEmail(), messageForRob.getId(), this.messageRepository::findByIdPostAuthorize);

		authenticate(rob.getEmail());
		this.messageRepository.findMessagesFor(PageRequest.of(0, 5)).forEach(log::info);

		authenticate(josh.getEmail());
		this.messageRepository.findMessagesFor(PageRequest.of(0, 5)).forEach(log::info);

		log.info("audited: " + this.messageRepository.save(new Message("this is a test", rob)));
	}

	private void attemptAccess(String adminUser, String regularUser, Long msgId, Function<Long, Message> fn) {
		authenticate(adminUser);
		log.info("result for Rob:" + fn.apply(msgId));

		try {
			authenticate(regularUser);
			log.info("result for Josh:" + fn.apply(msgId));
		}
		catch (Throwable e) {
			log.error("oops! couldn't obtain the result for Josh");
		}

	}

	private void authenticate(String username) {
		UserDetails user = this.userDetailsService.loadUserByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
