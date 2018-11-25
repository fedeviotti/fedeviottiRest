package com.aws.codestar.projecttemplates.handler;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;

import com.aws.codestar.projecttemplates.GatewayResponse;
import com.aws.codestar.projecttemplates.model.User;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link HelloWorldHandler}. Modify the tests in order to support your use case as you build your project.
 */
@DisplayName("Tests for HelloWorldHandler")
public class HelloWorldHandlerTest {

    private static final String EXPECTED_CONTENT_TYPE = "application/json";
    private static final String EXPECTED_RESPONSE_VALUE_ID = "1";
    private static final String EXPECTED_RESPONSE_VALUE_NAME = "Federico Viotti";
    private static final String EXPECTED_RESPONSE_VALUE_USERNAME = "fedeviotti";
    private static final String EXPECTED_RESPONSE_VALUE_EMAIL = "fedeviotti@gmail.com";
    private static final int EXPECTED_STATUS_CODE_SUCCESS = 200;

    // A mock class for com.amazonaws.services.lambda.runtime.Context
    private final MockLambdaContext mockLambdaContext = new MockLambdaContext();
    private final Object input = new Object();

    /**
     * Initializing variables before we run the tests.
     * Use @BeforeAll for initializing static variables at the start of the test class execution.
     * Use @BeforeEach for initializing variables before each test is run.
     */
    @BeforeAll
    static void setup() {
        // Use as needed.
    }

    /**
     * De-initializing variables after we run the tests.
     * Use @AfterAll for de-initializing static variables at the end of the test class execution.
     * Use @AfterEach for de-initializing variables at the end of each test.
     */
    @AfterAll
    static void tearDown() {
        // Use as needed.
    }

    /**
     * Basic test to verify the result obtained when calling {@link HelloWorldHandler} successfully.
     */
    @Test
    @DisplayName("Basic test for request handler")
    void testHandleRequest() {
        GatewayResponse response = (GatewayResponse) new HelloWorldHandler().handleRequest(input, mockLambdaContext);

        // Verify the response obtained matches the values we expect.
        JSONObject jsonObjectFromResponse = new JSONObject(response.getBody());
        
        Gson gson = new Gson();	
        User user = gson.fromJson(jsonObjectFromResponse.get("User").toString(), User.class);
        
        assertEquals(EXPECTED_RESPONSE_VALUE_ID, user.getId());
        assertEquals(EXPECTED_RESPONSE_VALUE_NAME, user.getName());
        assertEquals(EXPECTED_RESPONSE_VALUE_USERNAME, user.getUsername());
        assertEquals(EXPECTED_RESPONSE_VALUE_EMAIL, user.getEmail());
        assertEquals(EXPECTED_CONTENT_TYPE, response.getHeaders().get("Content-Type"));
        assertEquals(EXPECTED_STATUS_CODE_SUCCESS, response.getStatusCode());
    }
}
