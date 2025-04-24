import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class api_ui {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext context = browser.newContext();
      Page page = context.newPage();
      page.navigate("https://freelance-learn-automation.vercel.app/login");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).fill("test2@example.com");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).fill("test123");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
      assertThat(page.locator("#root")).containsText("Java For Tester");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to Cart right arrow")).first().click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cart 1")).click();
      assertThat(page.locator("#root")).containsText("Total Price");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Remove from Cart right arrow")).click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Shop Now")).click();
      assertThat(page.locator("#root")).containsText("Selenium For Web Automation");
    }
  }
}