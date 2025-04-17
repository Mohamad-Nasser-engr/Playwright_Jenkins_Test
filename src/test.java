import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class test {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext context = browser.newContext();
      Page page = context.newPage();
      page.navigate("https://freelance-learn-automation.vercel.app/login");
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("New user? Signup")).click();
      assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Sign Up"))).isVisible();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).fill("test");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).click();
      
      String randomEmail = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
      
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).fill(randomEmail);
      //page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).fill("test@example.com");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password must be atleast 6")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password must be atleast 6")).fill("test123");
      page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Selenium Grid")).check();
      page.locator("#gender1").check();
      page.locator("#state").selectOption("Manipur");
      page.locator("#hobbies").selectOption("Dancing");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign up")).click();
      assertThat(page.locator("form")).containsText("Sign In");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).fill(randomEmail);
      //page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).fill("test@example.com");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).fill("test123");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
      assertThat(page.locator("#root")).containsText("Java For Tester");
      page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("menu")).click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign out")).click();
      assertThat(page.locator("form")).containsText("Sign In");
    }
  }
}