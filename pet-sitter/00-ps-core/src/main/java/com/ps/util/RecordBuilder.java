package com.ps.util;

import com.ps.ents.User;

public class RecordBuilder {

    /**
     * Builder method used to create a simple User instance with all the main fields populated.
     */
    public static User buildUser(String email){
        final User user = new User();
        user.setEmail(email);
        String[] namePieces = email.split("@");
        user.setUsername(namePieces[0].replace(".","").toLowerCase());
        if (namePieces[0].contains(".")) {
            // fn and ln can be inferred
            String[] names = namePieces[0].split("\\.");
            user.setLastName(names[1]);
            user.setFirstName(names[0]);
        }
        user.setRating(0d);
//        user.setActive(true);
        return user;
    }
}
