package com.ps.services;

import org.joda.time.DateTime;

import com.ps.ents.Pet;
import com.ps.ents.Request;
import com.ps.ents.User;
import com.ps.util.Pair;

public interface RequestService {

    Request createRequest(User user, Pet pet, Pair<DateTime, DateTime> interval, String details);

}
