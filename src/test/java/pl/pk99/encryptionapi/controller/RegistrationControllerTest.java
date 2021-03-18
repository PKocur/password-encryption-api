package pl.pk99.encryptionapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.pk99.encryptionapi.form.RegistrationForm;
import pl.pk99.encryptionapi.service.RegistrationService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    private static final String URI = "/register";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationService registrationService;

    @Test
    public void register_nullBody_400() throws Exception {
        mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void register_invalidData_400() throws Exception {
        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("wrongProperty", "wrongValue");

        mockMvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new JSONObject(jsonBody).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        "{\"errors\":" +
                                "{\"password\":\"Field is required\"," +
                                "\"confirmPassword\":\"Field is required\"," +
                                "\"username\":\"Field is required\"}}"));
    }

    @Test
    public void register_validData_201() throws Exception {
        RegistrationForm registrationForm = new RegistrationForm("mockUsername", "mockPassword", "mockPassword");

        mockMvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationForm)))
                .andExpect(status().isCreated());
    }

}
