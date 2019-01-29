package com.example.methodsecurity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.methodsecurity.entities.Authority;
import com.example.methodsecurity.entities.Message;
import com.example.methodsecurity.entities.User;
import com.example.methodsecurity.repositories.AuthorityRepository;
import com.example.methodsecurity.repositories.MessageRepository;
import com.example.methodsecurity.repositories.UserRepository;
import com.example.methodsecurity.services.UserRepositoryUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class MethodSecurityApplicationTests {

	private static final String ROB_EMAIL = "rob@email.com";
	private static final String JOSH_EMAIL = "josh@email.com";

	private User rob, josh;

	private Authority writer, admin, user;

	private Message forJosh, forRob;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRepositoryUserDetailsService userDetailsService;

	@Before
	public void start() {
		Stream.of(this.messageRepository, this.userRepository, this.authorityRepository)
				.forEach(jr -> {
					jr.deleteAll();
					jr.flush();
				});

		this.admin = this.authorityRepository.save(new Authority("ADMIN"));
		this.user = this.authorityRepository.save(new Authority("USER"));
		this.writer = this.authorityRepository.save(new Authority("WRITER"));

		Assert.assertEquals(this.authorityRepository.findAll().size(), 3);
		this.rob = this.userRepository.save(new User(ROB_EMAIL, "password", new HashSet<>(Arrays.asList(this.admin, this.user))));
		this.josh = this.userRepository.save(new User(JOSH_EMAIL, "password", new HashSet<>(Arrays.asList(this.user))));

		Assert.assertEquals(this.userRepository.findAll().size(), 2);
		this.forJosh = this.messageRepository.save(new Message("this is a message", this.josh));
		this.forRob = this.messageRepository.save(new Message("yet another message", this.rob));
	}

	@Test
	public void usersAndAuthorities() {
		assertUserAuthorities(JOSH_EMAIL, 1);
		this.josh.getAuthorities().add(this.writer);
		this.authorityRepository.save(this.writer);
		assertUserAuthorities(JOSH_EMAIL, 2);
		Assert.assertEquals(this.messageRepository.count(), 2);
		Assert.assertNotNull(this.messageRepository.findById(forJosh.getId()));
		Assert.assertNotNull(this.messageRepository.findById(forRob.getId()));
	}

	@Test(expected = AccessDeniedException.class)
	public void findByIdRolesAllowed() {
		this.testAuthenticationWithMethodSecurity(id -> this.messageRepository.findByIdRolesAllowed(id), forRob.getId());
	}

	@Test(expected = AccessDeniedException.class)
	public void findByIdSecured() {
		this.testAuthenticationWithMethodSecurity(id -> this.messageRepository.findByIdSecured(id), forRob.getId());
	}

	@Test(expected = AccessDeniedException.class)
	public void findByIdPreAuthorize() {
		this.testAuthenticationWithMethodSecurity(id -> this.messageRepository.findByIdPreAuthorize(id), forRob.getId());
	}

	@Test
	public void findMessagesFor() {
		installAuthentication(ROB_EMAIL);

		this.messageRepository.save(new Message("this is a message for rob", this.rob));
		this.messageRepository.save(new Message("this is yet another message for rob", this.rob));
		this.messageRepository.save(new Message("this is also yet another message for rob", this.rob));

		Assert.assertEquals(this.messageRepository.findMessagesFor(PageRequest.of(0, 10)).getTotalElements(), 4);

		installAuthentication(JOSH_EMAIL);
		Assert.assertEquals(this.messageRepository.findMessagesFor(PageRequest.of(0, 10)).getTotalElements(), 1);
	}

	private void testAuthenticationWithMethodSecurity(Function<Long, Message> callback, Long id) {
		this.installAuthentication(ROB_EMAIL);
		callback.apply(id);
		this.installAuthentication(JOSH_EMAIL);
		callback.apply(id);
		Assert.fail("you shouldn't be able to reach this point as you don't have the right permissions");
	}

	private void installAuthentication(String username) {
		UserDetails principal = this.userDetailsService.loadUserByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private void assertUserAuthorities(String username, int size) {
		User usr = this.userRepository.findByEmail(username);
		Assert.assertNotNull(usr);
		Assert.assertEquals(usr.getAuthorities().size(), size);
	}

}