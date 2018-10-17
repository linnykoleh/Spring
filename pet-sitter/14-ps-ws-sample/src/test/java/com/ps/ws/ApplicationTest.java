package com.ps.ws;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {


    private static final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    private static final WebServiceTemplate ws = new WebServiceTemplate(marshaller);

    @LocalServerPort
    private int port;

    @BeforeClass
    public static void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetUserRequest.class));
        marshaller.afterPropertiesSet();
    }

    @Test
    public void testSendAndReceive() {
        GetUserRequest request = new GetUserRequest();
        request.setEmail("John.Cusack@pet.com");

        Object responseObject = ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request);
        assertThat(responseObject).isNotNull();
        assertThat(responseObject instanceof GetUserResponse).isTrue();

        GetUserResponse response = (GetUserResponse) responseObject;
        assertThat(response.getUserType()).isEqualTo(UserType.BOTH);
    }
}
