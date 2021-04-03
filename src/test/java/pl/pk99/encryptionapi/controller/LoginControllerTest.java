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
import pl.pk99.encryptionapi.form.LoginForm;
import pl.pk99.encryptionapi.service.LoginService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    private static final String URI = "/login";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoginService loginService;

    @Test
    public void login_nullBody_400() throws Exception {
        mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void login_invalidData_400() throws Exception {
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
                                "\"username\":\"Field is required\"}}"));
    }

    @Test
    public void login_validData_400() throws Exception {
        LoginForm loginForm = new LoginForm("mockUsername", "mockPassword");

        mockMvc
                .perform(post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginForm)))
                .andExpect(status().isNoContent());
    }
}
