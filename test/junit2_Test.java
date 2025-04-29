/*
 * import com.microsoft.playwright.junit.UsePlaywright; import
 * com.microsoft.playwright.Page; import com.microsoft.playwright.options.*;
 * 
 * import org.junit.jupiter.api.*; import static
 * com.microsoft.playwright.assertions.PlaywrightAssertions.*;
 * 
 * @UsePlaywright public class junit2_Test {
 * 
 * @Test void test(Page page) {
 * page.navigate("https://freelance-learn-automation.vercel.app/login");
 * page.getByRole(AriaRole.TEXTBOX, new
 * Page.GetByRoleOptions().setName("Enter Email")).click();
 * page.getByRole(AriaRole.TEXTBOX, new
 * Page.GetByRoleOptions().setName("Enter Email")).fill("test123@gmail.com");
 * //page.getByRole(AriaRole.TEXTBOX, new
 * Page.GetByRoleOptions().setName("Enter Email")).fill("test2@example.com");
 * page.getByRole(AriaRole.TEXTBOX, new
 * Page.GetByRoleOptions().setName("Enter Password")).click();
 * page.getByRole(AriaRole.TEXTBOX, new
 * Page.GetByRoleOptions().setName("Enter Password")).fill("test123");
 * page.getByRole(AriaRole.BUTTON, new
 * Page.GetByRoleOptions().setName("Sign in")).click();
 * assertThat(page.getByRole(AriaRole.HEADING, new
 * Page.GetByRoleOptions().setName("error Email and Password"))).isVisible();
 * 
 * try { Thread.sleep(10000); } catch (InterruptedException e) {
 * e.printStackTrace(); }
 * 
 * 
 * } }
 */

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class junit2_Test {

  @Test
  void testLogin() {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
      BrowserContext context = browser.newContext();
      Page page = context.newPage();

      page.navigate("https://freelance-learn-automation.vercel.app/login");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).fill("test123@gmail.com");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).fill("test123");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();

      assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("error Email and Password"))).isVisible();

      Thread.sleep(10000); // Optional: just for observation
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
