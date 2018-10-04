package com.demo.config;

import com.demo.to.UserTo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    private MongoTemplate mongoTemplate() {
        final String host = "127.0.0.1";
        final int port = 27017;
        final String database = "local";

        final ServerAddress server = new ServerAddress(host, port);
        final MongoClient mongo = new MongoClient(server);
        return new MongoTemplate(mongo, database);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserTo userTo = getUserToDetail(username);
        return new User(
                userTo.getUsername(), userTo.getPassword(), userTo.isEnabled(),
                true, true, true,
                getAuthorities()
        );
    }

    private List<GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }

    private UserTo getUserToDetail(String username) {
        final MongoOperations mongoOperation = mongoTemplate();
        return mongoOperation.findOne(new Query(Criteria.where("username").is(username)), UserTo.class);
    }

}
