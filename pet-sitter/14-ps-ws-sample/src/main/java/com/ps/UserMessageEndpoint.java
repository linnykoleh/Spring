package com.ps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ps.ws.GetUserRequest;
import com.ps.ws.GetUserResponse;

@Endpoint
public class UserMessageEndpoint {

    static final String NAMESPACE_URI = "http://ws-boot.com/schemas/um";

    private final UserMessageRepository userMessageRepository;

    @Autowired
    public UserMessageEndpoint(UserMessageRepository userMessageRepository) {
        this.userMessageRepository = userMessageRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUserType(userMessageRepository.findUserType(request.getEmail()));
        return response;
    }
}
