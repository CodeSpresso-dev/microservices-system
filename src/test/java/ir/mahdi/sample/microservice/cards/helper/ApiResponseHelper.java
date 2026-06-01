package ir.mahdi.sample.microservice.cards.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;

public class ApiResponseHelper {

    private final MvcResult result;
    private final JsonNode root;

    public ApiResponseHelper(MvcResult result) throws Exception {
        this.result = result;

        ObjectMapper mapper = new ObjectMapper();
        this.root = mapper.readTree(
                result.getResponse().getContentAsString()
        );
    }

    public static ApiResponseHelper assertThat(MvcResult result) throws Exception {
        return new ApiResponseHelper(result);
    }

    public ApiResponseHelper hasStatus(int expectedStatus) {
        assertEquals(
                expectedStatus,
                result.getResponse().getStatus()
        );

        return this;
    }

    public ApiResponseHelper isSuccess() {
        assertTrue(root.path("success").asBoolean());
        return this;
    }

    public ApiResponseHelper isFailure() {
        assertFalse(root.path("success").asBoolean());
        return this;
    }

    public ApiResponseHelper hasMessage(String expectedMessage) {
        assertEquals(
                expectedMessage,
                root.path("message").asText()
        );

        return this;
    }

    public ApiResponseHelper hasErrorCode(String expectedCode) {
        assertEquals(
                expectedCode,
                root.path("error").path("code").asText()
        );

        return this;
    }

    public ApiResponseHelper hasFieldError(
            String field,
            String message
    ) {
        JsonNode subErrors =
                root.path("error")
                        .path("subErrors");

        boolean found = false;

        for (JsonNode error : subErrors) {

            String actualField =
                    error.path("field").asText();

            String actualMessage =
                    error.path("message").asText();

            if (field.equals(actualField)
                    && message.equals(actualMessage)) {

                found = true;
                break;
            }
        }

        assertTrue(
                found,
                "Field error not found. field=" + field
                        + ", message=" + message
        );

        return this;
    }
}
