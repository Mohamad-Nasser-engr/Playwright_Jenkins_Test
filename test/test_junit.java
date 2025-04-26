import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;
import org.junit.jupiter.api.*;
import java.util.UUID;

@UsePlaywright
public class test_junit {
  @Test
  void test(Page page) {
    // Generate a random email
    String randomEmail = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";

    // Navigate to the login page
    page.navigate("https://freelance-learn-automation.vercel.app/login");

    // Click on "New user? Signup"
    page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("New user? Signup")).click();
    assertThat(page.locator("form")).containsText("Sign Up");

    // Fill the sign-up form
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).click();
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).fill("test");
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).click();
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).fill(randomEmail);
    //page.getByRole(AriaRole.TEXTBOX, new  Page.GetByRoleOptions().setName("Email")).fill("test2@example.com");
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password must be atleast 6")).click();
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password must be atleast 6")).fill("test123");

    // Select Java checkbox and other form options
    page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Java")).check();
    page.locator("#gender2").check();
    page.locator("#state").selectOption("Punjab");
    page.locator("#hobbies").selectOption("Swimming");

    // Submit the form
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign up")).click();
    assertThat(page.locator("form")).containsText("Sign In");

    // Sign in with the newly registered email
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).click();
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).fill(randomEmail);
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).click();
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).fill("test123");
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
    assertThat(page.locator("#root")).containsText("Java For Tester");

    // Sign out
    page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("menu")).click();
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign out")).click();
    assertThat(page.locator("form")).containsText("Sign In");
  }
}
