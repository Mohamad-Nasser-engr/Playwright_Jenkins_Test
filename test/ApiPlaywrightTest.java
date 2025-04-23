import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ApiPlaywrightTest {
  static Playwright playwright;
  static APIRequestContext request;

  @BeforeAll
  static void setup() {
    playwright = Playwright.create();
    APIRequest requestContext = playwright.request();
    request = requestContext.newContext(new APIRequest.NewContextOptions()
      .setBaseURL("https://jsonplaceholder.typicode.com"));
  }

  @AfterAll
  static void teardown() {
    request.dispose();
    playwright.close();
  }

  @Test
  void testGetPost() {
    APIResponse response = request.get("/posts/1");
    assertEquals(200, response.status());

    String json = response.text();
    assertTrue(json.contains("\"userId\": 1"));
    assertTrue(json.contains("\"id\": 1"));
  }

  @Test
  void testCreatePost() {
    APIResponse response = request.post("/posts", RequestOptions.create()
      .setData("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}")
      .setHeader("Content-Type", "application/json"));

    assertEquals(401, response.status());
    String json = response.text();
    assertTrue(json.contains("\"title\": \"foo\""));
  }
}
