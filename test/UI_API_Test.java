import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;



@UsePlaywright
public class UI_API_Test {
  @Test
  void test(Page page) {
    page.navigate("https://freelance-learn-automation.vercel.app/");
    page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("menu")).click();
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in")).click();
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).click();
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Email")).fill("test2@example.com");
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).click();
    page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter Password")).fill("test123");
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
  }
}












/*
 * @UsePlaywright public class UI_API_Test {
 * 
 * @Test void test(Page page) {
 * page.navigate("https://freelance-learn-automation.vercel.app/");
 * page.getByRole(AriaRole.IMG, new
 * Page.GetByRoleOptions().setName("menu")).click();
 * page.getByRole(AriaRole.BUTTON, new
 * Page.GetByRoleOptions().setName("Log in")).click();
 * page.getByRole(AriaRole.TEXTBOX, new
 * Page.GetByRoleOptions().setName("Enter Email")).click();
 * page.getByRole(AriaRole.TEXTBOX, new
 * Page.GetByRoleOptions().setName("Enter Email")).fill("test2@example.com");
 * page.getByRole(AriaRole.TEXTBOX, new
 * Page.GetByRoleOptions().setName("Enter Password")).click();
 * page.getByRole(AriaRole.TEXTBOX, new
 * Page.GetByRoleOptions().setName("Enter Password")).fill("test123");
 * page.getByRole(AriaRole.BUTTON, new
 * Page.GetByRoleOptions().setName("Sign in")).click(); } }
 */