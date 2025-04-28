import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Request;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.*;

import org.junit.jupiter.api.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

@UsePlaywright
public class UI_API_Test {
	@Test
	void test(Page page) {
		// Step 1: Navigate to the login page
		page.navigate("https://freelance-learn-automation.vercel.app/login");
		
		
		// SIGNIN API
		// make sure that data is not empty
		  page.onRequest((Request request) -> { if
		   (request.url().contains("/api/signin") && request.method().equals("POST")) {
		   String requestPayload = request.postData();
		   Assertions.assertFalse(requestPayload.isEmpty(), "Request payload should not be empty");
		   } 
		  });
		  
				page.onResponse(response -> {
					if (response.url().contains("/api/signin")) {

						// Convert response body (byte[]) to string
						byte[] bodyBytes = response.body();
						String bodyString = new String(bodyBytes, StandardCharsets.UTF_8);

						// Print the API response body
						System.out.println("API Response Body: " + bodyString);
						
						// Check if the status code is 200
						Assertions.assertEquals(200, response.status());

						// Parse the response body as JSON
						JSONObject jsonResponse = new JSONObject(bodyString);

						// Verify the token is not null or empty
						String token = jsonResponse.getString("token");
						Assertions.assertFalse(token.isEmpty(), "Token should not be empty");

						// Verify user email and name
						JSONObject user = jsonResponse.getJSONObject("user");
						String email = user.getString("email");
						String name = user.getString("name");

						Assertions.assertEquals("test2@example.com", email, "Email does not match");
						Assertions.assertEquals("test", name, "Name does not match");

					}
				});
		
		// SIGNOUT API
		page.onResponse(response -> {
            if (response.url().contains("/api/signout")) {
                // Check if the status code is 200
                Assertions.assertEquals(200, response.status());
                
                
             // Optionally, verify the response body
                byte[] bodyBytes = response.body();
                String bodyString = new String(bodyBytes, StandardCharsets.UTF_8);
                System.out.println("Sign-Out API Response Body: " + bodyString);

                // Check the message in the sign-out response
                JSONObject jsonResponse = new JSONObject(bodyString);
                String message = jsonResponse.getString("message");
                Assertions.assertEquals("USER SIGNOUT SUCCESFULLY", message, "Sign-out message mismatch");
            }
        });

		// UI Testing
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).fill("test2@example.com");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).fill("test123");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
		page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("menu")).click();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign out")).click();		
		
		page.waitForTimeout(10000); 
	}
}
