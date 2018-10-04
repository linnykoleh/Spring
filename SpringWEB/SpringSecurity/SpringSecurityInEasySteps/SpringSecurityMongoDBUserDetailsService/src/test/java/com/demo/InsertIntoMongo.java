package com.demo;

import  com.demo.to.UserTo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class InsertIntoMongo {

    public static void main(String s[]) {
        final String host = "127.0.0.1";
        final int port = 27017;
        final String database = "local";

        final ServerAddress server = new ServerAddress(host, port);
        final MongoClient mongo = new MongoClient(server);
        final MongoOperations mongoOperation = new MongoTemplate(mongo, database);
        mongoOperation.dropCollection(UserTo.class);

        final UserTo user = new UserTo("ankidaemon", "password", true);
        mongoOperation.save(user);

        final UserTo user1 = new UserTo("test", "test", true);
        mongoOperation.save(user1);

        //Retrieve all users as a list
        final List<UserTo> users = mongoOperation.findAll(UserTo.class);
        for (UserTo userVar : users) {
            System.out.println(userVar.getUsername() + " " + userVar.getPassword());
        }

        //Find User based on query
        final UserTo userVar = mongoOperation.findOne(
                new Query(Criteria.where("username").is("ankidaemon")),
                UserTo.class);
        System.out.println(userVar.getUsername() + " " + userVar.getPassword());
    }

}
