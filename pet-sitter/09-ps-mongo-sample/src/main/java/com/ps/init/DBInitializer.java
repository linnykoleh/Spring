package com.ps.init;

import static com.ps.util.RecordBuilder.buildUser;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.ents.User;
import com.ps.repos.UserRepo;

@Service
public class DBInitializer {

    private final UserRepo userRepo;

    @Autowired
    public DBInitializer(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void initDb() {
        userRepo.deleteAll();

        System.out.println("===================================");
        System.out.println("Starting database initialization...");
        System.out.println("===================================");

        final Set<User> users = new HashSet<>();
        final User john = buildUser("john.cusack@pet.com");
        john.setPassword("test");
        users.add(john);

        final User mary = buildUser("Mary.Poppins@pet.com");
        mary.setPassword("test");
        users.add(mary);

        final User jessica = buildUser("Jessica.Jones@pet.com");
        jessica.setPassword("test");
        users.add(jessica);

        final User johnny = buildUser("johnny.big@pet.com");
        johnny.setPassword("test");
        users.add(johnny);

        final User gigi = buildUser("gigi.pedala@pet.com");
        gigi.setPassword("test");
        users.add(gigi);

        userRepo.save(users);

        System.out.println("=================================");
        System.out.println("Database initialization finished.");
        System.out.println("=================================");
    }
}
