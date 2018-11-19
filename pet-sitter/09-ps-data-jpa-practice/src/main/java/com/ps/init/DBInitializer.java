package com.ps.init;

import static com.ps.util.RecordBuilder.buildUser;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.base.PetType;
import com.ps.base.UserType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.UserRepo;

@Service
public class DBInitializer {

	private final UserRepo userRepo;

	@Autowired
	public DBInitializer(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@PostConstruct
	public void init() {
		// always start with a clean database
		userRepo.deleteAll();

		System.out.println("===================================");
		System.out.println("Starting database initialization...");
		System.out.println("===================================");

		final Set<User> users = new HashSet<>();
		User john = buildUser("john.cusack@pet.com");
		john.setPassword("test");
		john.setUserType(UserType.OWNER);
		users.add(john);

		final Pet max = new Pet();
		max.setName("Max");
		max.setAge(10);
		max.setPetType(PetType.DOG);
		max.setRfid("1122334455");
		john.addPet(max);

		final Pet mona = new Pet();
		mona.setName("Mona");
		mona.setAge(2);
		mona.setPetType(PetType.CAT);
		mona.setRfid("1100223344");
		john.addPet(mona);

		final User mary = buildUser("Mary.Poppins@pet.com");
		mary.setPassword("test");
		mary.setUserType(UserType.SITTER);
		users.add(mary);

		final User jessica = buildUser("Jessica.Jones@pet.com");
		jessica.setPassword("test");
		jessica.setUserType(UserType.BOTH);
		users.add(jessica);

		final Pet kiki = new Pet();
		kiki.setName("Kiki");
		kiki.setAge(3);
		kiki.setPetType(PetType.BIRD);
		kiki.setRfid("1100221144");
		jessica.addPet(kiki);

		final User johnny = buildUser("johnny.big@pet.com");
		johnny.setPassword("test");
		johnny.setUserType(UserType.SITTER);
		users.add(johnny);

		final User gigi = buildUser("gigi.pedala@pet.com");
		gigi.setPassword("test");
		gigi.setUserType(UserType.SITTER);
		users.add(gigi);
		userRepo.save(users);

		System.out.println("=================================");
		System.out.println("Database initialization finished.");
		System.out.println("=================================");
	}
}
