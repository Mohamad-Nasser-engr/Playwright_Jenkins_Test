/*
 * import com.microsoft.playwright.*; import
 * com.microsoft.playwright.options.RequestOptions; import
 * org.junit.jupiter.api.*; import static org.junit.jupiter.api.Assertions.*;
 * 
 * import java.nio.charset.StandardCharsets; import java.nio.file.*; import
 * java.io.IOException; import java.nio.file.Files;
 * 
 * public class FileUploadTest {
 * 
 * private static Playwright playwright; private static APIRequestContext
 * requestContext;
 * 
 * @BeforeAll public static void setUp() { // Initialize Playwright playwright =
 * Playwright.create(); Browser browser = playwright.chromium().launch();
 * BrowserContext context = browser.newContext(); requestContext =
 * context.request(); }
 * 
 * @Test public void testFileUpload() throws IOException { // URL for the file
 * upload API endpoint String uploadApiUrl = "https://httpbin.org/post";
 * 
 * // Path to the file to be uploaded Path filePath =
 * Paths.get("C:\\Users\\user\\Desktop\\api_test_file.txt");
 * 
 * // Read the file content into a byte array byte[] fileContent =
 * Files.readAllBytes(filePath);
 * 
 * // Manually create a multipart/form-data body String boundary =
 * "----WebKitFormBoundary7MA4YWxkTrZu0gW"; StringBuilder body = new
 * StringBuilder(); body.append("--" + boundary + "\r\n");
 * body.append("Content-Disposition: form-data; name=\"file\"; filename=\"" +
 * filePath.getFileName() + "\"\r\n");
 * body.append("Content-Type: text/plain\r\n\r\n"); // Adjust MIME type as
 * necessary body.append(new String(fileContent, StandardCharsets.UTF_8) +
 * "\r\n"); body.append("--" + boundary + "--\r\n");
 * 
 * // Convert the body to byte array byte[] bodyBytes =
 * body.toString().getBytes(StandardCharsets.UTF_8);
 * 
 * // Upload the file as form-data using Playwright's request API APIResponse
 * response = requestContext.post(uploadApiUrl, RequestOptions.create()
 * .setHeader("Content-Type", "multipart/form-data; boundary=" + boundary)
 * .setData(bodyBytes));
 * 
 * // Assert the status code is 200 OK assertEquals(200, response.status());
 * 
 * // Get and print the response body to check if the file was uploaded
 * correctly byte[] responseBody = response.body(); String bodyString = new
 * String(responseBody, StandardCharsets.UTF_8);
 * System.out.println("Response Body: " + bodyString);
 * 
 * // Optionally, assert that the response contains some expected content
 * assertTrue(bodyString.contains("form")); }
 * 
 * @AfterAll public static void tearDown() { // Clean up Playwright resources if
 * (playwright != null) { playwright.close(); } } }
 */







  import com.microsoft.playwright.*; 
  import com.microsoft.playwright.options.RequestOptions;
  import org.junit.jupiter.api.*; import static org.junit.jupiter.api.Assertions.*;
  
  import java.nio.charset.StandardCharsets; import java.nio.file.*; import
  java.io.IOException; import java.nio.file.Files;
  
  public class FileUploadTest {
  
  private static Playwright playwright; private static APIRequestContext
  requestContext;
  
  @BeforeAll public static void setUp() { // Initialize 
	  Playwright playwright =
  Playwright.create(); Browser browser = playwright.chromium().launch();
  BrowserContext context = browser.newContext(); requestContext =
  context.request(); }
  
  @Test public void testFileUpload() throws IOException { // URL for the file upload API endpoint 
	  String uploadApiUrl = "https://httpbin.org/post";
  
  // Path to the file to be uploaded 
  Path filePath = Paths.get("C:\\Users\\user\\Desktop\\api_test_file.txt");
  
  // Read the file content as binary data 
  byte[] fileContent =
  Files.readAllBytes(filePath);
  
  // Manually create a multipart/form-data body 
  String boundary =
  "----WebKitFormBoundary7MA4YWxkTrZu0gW"; StringBuilder body = new
  StringBuilder(); body.append("--" + boundary + "\r\n");
  body.append("Content-Disposition: form-data; name=\"file\"; filename=\"" +
  filePath.getFileName() + "\"\r\n");
  body.append("Content-Type: application/octet-stream\r\n\r\n"); // MIME typefor binary data 
  body.append(new String(fileContent, StandardCharsets.UTF_8));
  // This is the file content in binary form 
  body.append("\r\n");
  body.append("--" + boundary + "--\r\n");
  
  // Convert the body to byte array 
  byte[] bodyBytes =
  body.toString().getBytes(StandardCharsets.UTF_8);
  
  // Upload the file as form-data using Playwright's request API 
  APIResponse
  response = requestContext.post(uploadApiUrl, RequestOptions.create()
  .setHeader("Content-Type", "multipart/form-data; boundary=" + boundary)
  .setData(bodyBytes));
  
  // Assert the status code is 200 OK 
  assertEquals(200, response.status());
  
  // Get and print the response body to check if the file was uploaded correctly 
  byte[] responseBody = response.body(); String bodyString = new
  String(responseBody, StandardCharsets.UTF_8);
  System.out.println("Response Body: " + bodyString);
  
  // Optionally, assert that the response contains some expected content
  assertTrue(bodyString.contains("form")); }
  
  @AfterAll public static void tearDown() { // Clean up Playwright resources 
	  if
  (playwright != null) { playwright.close(); } } }
 


/*
 * import com.microsoft.playwright.*; import
 * com.microsoft.playwright.options.FormData; import
 * com.microsoft.playwright.options.RequestOptions; import
 * org.junit.jupiter.api.*; import java.nio.file.*; import java.io.IOException;
 * 
 * import static org.junit.jupiter.api.Assertions.*;
 * 
 * public class FileUploadTest { private static Playwright playwright; private
 * static APIRequestContext requestContext;
 * 
 * @BeforeAll public static void setUp() { playwright = Playwright.create();
 * Browser browser = playwright.chromium().launch(); BrowserContext context =
 * browser.newContext(); requestContext = context.request(); }
 * 
 * @Test public void testFileUpload() throws IOException { // URL for the file
 * upload API endpoint String uploadUrl = "https://httpbin.org/post";
 * 
 * // Path to the file to be uploaded Path filePath =
 * Paths.get("C:\\Users\\user\\Desktop\\api_test_file.txt");
 * 
 * // Read the file content into a byte array byte[] fileBytes =
 * Files.readAllBytes(filePath);
 * 
 * // Create FormData and add the file FormData formData = FormData.create();
 * formData.set("file", filePath); // Here we specify the file field
 * 
 * // Send the POST request with FormData APIResponse response =
 * requestContext.post(uploadUrl, RequestOptions.create()
 * .setHeader("Content-Type", "multipart/form-data") // Content-Type is handled
 * by FormData .setData(formData) // Use FormData here );
 * 
 * // Print the response body String responseBody = response.text();
 * System.out.println("Response: " + responseBody);
 * 
 * // Assert the status code is 200 OK assertEquals(200, response.status());
 * 
 * // Assert that the response contains the uploaded filename
 * assertTrue(responseBody.contains("api_test_file.txt"),
 * "Uploaded filename should appear in response."); }
 * 
 * @AfterAll public static void tearDown() { // Clean up Playwright resources if
 * (playwright != null) { playwright.close(); } } }
 * 
 * 
 * 
 * 
 * 
 */