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
    request = playwright.request().newContext(new APIRequest.NewContextOptions()
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
    assertTrue(response.text().contains("\"userId\": 1"));
    assertEquals("application/json; charset=utf-8", response.headers().get("content-type"));
  }

  @Test
  void testCreatePost() {
    APIResponse response = request.post("/posts", RequestOptions.create()
      .setHeader("Content-Type", "application/json")
      .setData("{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}"));

    assertEquals(201, response.status());
    assertTrue(response.text().contains("\"title\": \"foo\""));
  }

  @Test
  void testUpdatePost() {
    APIResponse response = request.put("/posts/1", RequestOptions.create()
      .setHeader("Content-Type", "application/json")
      .setData("{\"id\": 1, \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1}"));

    assertEquals(200, response.status());
    assertTrue(response.text().contains("\"title\": \"updated title\""));
  }

  @Test
  void testDeletePost() {
    APIResponse response = request.delete("/posts/1");
    assertEquals(200, response.status());
  }

  @Test
  void testError404() {
    APIResponse response = request.get("/invalid-endpoint");
    assertEquals(404, response.status());
  }

  @Test
  void testTimeoutBehavior() {
    APIRequestContext customRequest = playwright.request().newContext(new APIRequest.NewContextOptions()
      .setBaseURL("https://jsonplaceholder.typicode.com")
      .setTimeout(1000)); // 1 second timeout

    APIResponse response = customRequest.get("/posts/1");
    assertEquals(200, response.status());
    customRequest.dispose();
  }
}
