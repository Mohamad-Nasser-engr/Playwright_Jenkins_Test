# 📘 Playwright with Java & JUnit – Research Findings

## 🧩 Overview

This document summarizes my research on using [Microsoft Playwright](https://playwright.dev/java/) for end-to-end testing with **Java** and **JUnit**. It focuses on how Playwright can facilitate rapid test creation via its **codegen recorder**, and how it fits into modern DevOps pipelines.

---

## Tool Evaluation and Selection Justification
As part of my research, I evaluated several popular test automation tools: TestRigor, Mabl, Katalon Studio, Selenium, and Playwright. Each was assessed based on its features, usability, pricing, and integration capabilities.

### Summary of Comparison:

| Tool             | Strengths                                                                 | Limitations                                                                 |
|------------------|---------------------------------------------------------------------------|------------------------------------------------------------------------------|
| **TestRigor**     | Natural language scripting, AI-powered suggestions, API testing, CI/CD ready | Recorder is **very inaccurate**, expensive (~$900/month), limited control for complex scenarios |
| **Mabl**          | Low-code scripting, visual testing, parallel execution, cloud-based       | No free tier beyond 14-day trial, must contact sales for pricing             |
| **Katalon Studio**| Supports UI/code test creation, good recorder, API & mobile testing       | **CI/CD integration requires paid tier**, many features behind paywall       |
| **Selenium**      | Free and open-source, supports many languages, flexible for dev teams     | **Jenkins plugin is unstable**, lacks built-in visual/mobile/API testing, steep learning curve |
| **Playwright**    | Free, modern, fast, supports Chromium/Firefox/WebKit, great dev experience| Limited mobile support (emulated only), no built-in AI, requires coding      |

### Why I Chose Playwright
After analyzing these tools, I chose Playwright for the following reasons:
- Comprehensive cross-browser testing — includes Chromium, Firefox, and WebKit support out of the box.
- Fast and modern — excellent for dynamic web apps with faster test execution than Selenium.
- Code-first flexibility — full control for customizing complex test flows.
- Developer-focused tools — powerful selector engine, smooth debugging, and intelligent error messages.
- Cost-effective — fully open-source with no licensing costs.
- CI/CD ready — seamless integration into modern pipelines with parallel execution support.
- Native Support for Multiple Languages — Playwright supports JavaScript, Python, Java, and C#, providing flexibility to use your preferred language.
- Headless & Headed Execution — It supports both headless and headed browser executions.
- Auto-Wait and Smart Assertions — Playwright automatically waits for elements to be ready before interacting with them, ensuring more reliable tests.
- Recorder Tool — Playwright’s recorder tool automatically generates tests, simplifying test creation and boosting productivity.

---

## 🔧 Prerequisites
Before getting started, ensure you have the following tools and accounts set up:
- Java 
- Node.js installed (for Playwright CLI tools)
- Maven installed
- Jenkins server set up
- JIRA Cloud account with admin access

---

## 🧪 Technology Stack

- **Playwright** v1.51.0 
- **Java** 
- **JUnit 5**
- **Maven** (for project and dependency management)

---

## ⚙️ Installation & Setup
This section guides you through setting up a Java project using Maven, configuring Playwright for browser automation, and using the recorder to generate test code.

### 1. Create a Maven Project
Maven is a build automation tool used primarily for Java projects. It uses a **pom.xml** file to manage dependencies and build configurations. If you're using an IDE like IntelliJ IDEA or Eclipse, you can create a Maven project from the "New Project" wizard.

File -> New Project -> Maven Project

This creates a basic Maven structure with a pom.xml file.

### 2. Add Playwright and Junit 5 to your Maven `pom.xml`:
Edit the pom.xml file to include the following dependencies. These will enable Java to work with Playwright and run JUnit 5 tests:
```xml
<dependency>
  <groupId>com.microsoft.playwright</groupId>
  <artifactId>playwright</artifactId>
  <version>1.51.0</version>
</dependency>
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter-api</artifactId>
  <version>5.8.1</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter-engine</artifactId>
  <version>5.8.1</version>
  <scope>test</scope>
</dependency>
```
### 3. Install Playwright recorder
This step installs Playwright using the Node.js version, which is required to record and generate Java test code.
```bash
npx playwright install 
```
This will download browser binaries and set up Playwright's command-line tools.
### 4. Use Playwright's recorder 
Now you're ready to generate Java test code using Playwright's recorder:
```bash
npx playwright codegen https://example.com
```

This command opens a browser window where you can interact with the site. As you click, type, or navigate, Playwright automatically generates Java test code in real time.

Important: In the top-right corner of the recorder, change the target language to Java JUnit to ensure it generates compatible test code.

The screenshot below shows an example of what the recorder looks like:

![image](https://github.com/user-attachments/assets/35989883-8197-4c9c-829c-cfbcd92da884)

---

## Integration with Jenkins 

This section shows how to integrate your Playwright Java tests into a CI pipeline using Jenkins. The goal is to automatically run your tests every time you push code to GitHub.

### 1. Setup a GitHub Repository

You’ll need your Maven project hosted on GitHub so Jenkins can pull and build it.

**Steps**:
- Create a GitHub repository
- Clone it:
  ```bash
  git clone https://github.com/your-username/repository-name.git
  cd playwright-java-tests
  ```
- Add your Maven project files (pom.xml, test files, etc)
- Commit and push:
  ```bash
  git add .
  git commit -m "Initial Playwright test setup"
  git push -u origin
  ```
### 2. Install Jenkins on Windows:
- Go to https://www.jenkins.io/download/
- Download the .msi installer for windows
- Run the installer and follow the setup wizard
- Jenkins will install as a Windows service (default port: 8080 or custom one like 8443)
Now you can access Jenkins by going to the following url:
```
http://localhost:<port-number>
```
- The first time you access Jenkins, it will ask for a password which can be found in this file:
  ```
  <Jenkins-installation-path>/secrets/initialAdminPassword
  ```
- paste the password in the web UI
- Finalize Jenkins Setup
  
### 3. Install Required Plugins
Ensure the following plugins are installed (Manage Jenkins → Manage Plugins):
- Git Plugin – For Git integration
- GitHub Plugin – For GitHub webhooks and integration
- JUnit – To publish test results
- Maven Integration – To build Maven projects

### 4. Create and configure a new Maven Project:
- In Jenkins, click New Item, name your job, and select Maven Project.
- Under Source Code Management, select Git and enter your GitHub repository URL.
- In the Build Trigger section, make sure to check “GitHub hook trigger for GITScm polling”. This allows Jenkins to automatically trigger a build when changes are pushed to the GitHub repository.
- In the Build section:

  Root POM:
  ```
  pom.xml
  ```
  Goals and Options:
  ```
  clean install
  ```
This setup will clone your code and run your tests automatically using the pom.xml file.

  ### Ensure **pom.xml** is Jenkins-Ready:
  To ensure Jenkins can detect and execute your Playwright tests via Maven:
  - Use the maven-surefire-plugin, which runs JUnit 5 tests during the test phase:
    ```xml
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.0.0-M5</version>
      <configuration>
      ...
      </configuration>
    </plugin>
    ```
  - The junit-jupiter-api and junit-jupiter-engine dependencies are declared with <scope>test</scope>, so tests can be discovered and run correctly.
  - Ensure your test classes follow the naming convention ***Test.java** so Surefire can detect them. If not, update includes in the plugin config.

### 5. Expose Jenkins to GitHub using ngrok (for Webhooks):
If Jenkins is hosted locally (e.g., on http://localhost:8443), GitHub won’t be able to trigger webhooks unless it's exposed to the internet.

To solve this we use ngrok to create a public URL that links to Jenkins:
- Install ngrok
- Start ngrok on the Jenkins port:
```bash
ngrok http 8443
```
This will generate a public URL that links to Jenkins

![image](https://github.com/user-attachments/assets/5fa0b1a1-5b2e-4de3-83d8-a901a5157f4d)

- Now that we created the URL we will add it as a WebHook in GitHub:
   - Go to your repository → Settings → Webhooks → Add webhook
   - Payload URL: <generated-ngrok-url>
   - Content type: application/json
   - Events: Choose “Just the push event”
 
![image](https://github.com/user-attachments/assets/1f81ec7a-b59e-45d7-8533-5d8592f6b0c0)

- In Jenkins:
   - Make sure your job is configured to trigger builds on GitHub push events
- Push a change to test the setup
  
⚠️ Note:
- The ngrok URL is only valid as long as the terminal window running ngrok is active. If you close the window or your system restarts, the URL will stop working.
- Each time you restart ngrok, a new URL is generated. Don’t forget to update the GitHub webhook with the new URL.

---

## Jenkins Integration with JIRA (Basic Build and Issue Tracking)
Integrating Jenkins with JIRA allows automatic linking of build results, commits, and issues, making it easier to track development progress directly from JIRA.
### Steps to integrate Jenkins with JIRA
### 1. Install the Jenkins JIRA Plugin
- Go to Manage Jenkins → Manage Plugins
- Under the Available tab, search for and install JIRA Plugin
- Restart Jenkins if prompted
### 2. Create a JIRA API Token (for Atlassian Cloud users)
If you're using Atlassian Cloud, you need an API token to authenticate Jenkins.
To generate one:
- Visit https://id.atlassian.com/manage/api-tokens
- Click Create API token
- Enter a label like "Jenkins Integration", then click Create
- Copy the generated token (you won’t be able to view it again)

  ![image](https://github.com/user-attachments/assets/32bd2b04-afb5-49f3-8cec-29b5c7e0602e)

### 3. Add JIRA credentials in Jenkins:
- Go to Manage Jenkins → Credentials
- Click **Add Credentials**
  - **Kind**: Username with password
  - **Username**: Your Atlassian account email
  - **Password**: Paste the API token
  - **ID/Description**: Optional (e.g., jira-api-token)

  ![image](https://github.com/user-attachments/assets/705dc3e8-80ca-4028-a2bb-994e05789edd)

### 4. Configure JIRA site in Jenkins
- Go to Manage Jenkins → Configure System
- Scroll to the JIRA section
- Click Add Jira Site
    - Enter the JIRA server URL (e.g., https://yourcompany.atlassian.net)
    - Add the previously created credentials (JIRA API token)
    - Test the connection
### 5. Link JIRA site in a Jenkins Job
- Open your Jenkins Job → Configure
- Scroll to the JIRA site section
- Select the JIRA site you previously configured
- This step enables Jenkins to associate builds with JIRA issues, making issue references clickable in build logs and views

*Note: This integration does not automatically update JIRA issues (e.g., adding comments or changing statuses). For that, you must use additional plugins, scripted logic, or smart commits via your version control system*

---

## Xray Integration for Test Management:
Xray is a powerful test management tool for Jira that allows teams to manage automated and manual tests directly from within Jira. We integrate Xray with Jenkins to automatically import test execution results, enabling traceability between test runs and Jira issues.
Using Xray ensures full visibility into testing status within Jira

Pricing: Xray for Jira Cloud is a paid add-on, with pricing starting at $10/month for up to 10 users. Larger teams incur additional costs.

### 1. Install the Xray App in JIRA:
- In Jira, go to Apps → Explore more apps
- Search for Xray Test Management for Jira
- Click Install
- Once installed, it will appear in the Apps section
### 2. Install the Xray Plugin in Jenkins
- Navigate to Jenkins Dashboard → Manage Jenkins → Plugins.
- Search for Xray and install the plugin.
- Restart Jenkins if required.
### 3. Create API Credentials in Jira:
- In Jira, go to Apps → Manage Apps → API Keys.
- Click Create API Key.
- Give it a name, and copy the generated key for later use.
### 4. Configure Xray in Jenkins:
- Go to Manage Jenkins → System → Configure System.
- Scroll to the Xray Configuration section.
- Fill in the following:
  - Configuration Alias: Choose JIRA Cloud.
  - Hosting Type: Select Cloud.
  - Credentials: Add the API Key from Step 3 as a new credential.
- Click Test Connection to ensure everything works correctly.
### 5. Add Xray Reporting in a Jenkins Job:
- Open your Jenkins job and click Configure.
- Under Post-build Actions, click Add post-build action → Select Xray: Results Import Task.
- Set the following options:
  - Instance: Choose JIRA Cloud.
  - Format: Choose JUnit XML.
  - Execution Report File: Enter your test report path (e.g., target/surefire-reports/*.xml).
  - JIRA Project Key: Enter the Jira project key (e.g., ABC).
  - Test Execution Key: Enter the corresponding Test Execution issue key (e.g., ABC-123).
 
--- 

## Manually Trigerring Jenkins Jobs From JIRA:
Manually triggering Jenkins jobs directly from JIRA allows QA or developers to initiate specific builds without switching context. This setup can be useful for on-demand testing or non-automated flows.

*Note: This section assumes you have already set up your Jenkins API token and exposed Jenkins via an ngrok URL as described in the earlier sections.*

### Steps to Setup JIRA Automation Rule:
- Navigate to JIRA Automation
  - Go to your JIRA project → Project Settings → Automation → Create Rule
- Configure the Trigger
  - Select Manual trigger as the rule trigger
  - his allows users to run the rule from a JIRA issue manually
- Add an Action: Send Web Request
  - Choose Send web request as the action
- Fill in Web Request Details:
  - Web Request URL:
    ```
    https://<your-ngrok-url>/job/<jenkins-job-name>/build?token=<my-trigger-token>
    ```
  Note: You must first enable the remote trigger in your Jenkins job:
  - Go to the Jenkins job → Configure
  - Check "Trigger builds remotely"
  - Set your Authentication Token (e.g., my-trigger-token)
- HTTP Method: POST
- Headers
  - Key 1: Authorization:
    ```
    Basic <base64_encoded_credentials>
    ```
    Replace with *base64-encoded string* of jenkins-username:jenkins-api-token
  - Key 2: Jenkins-Crumb:
    ```
    <crumb-value>
    ```
    To get the crumb use the following command:
    ```
    curl -u jenkinsuser:jenkins-api-token https://<your-ngrok-url>/crumbIssuer/api/json
    ```
    The response will be a JSON Object:
    ```
    {
     "crumb": "abc123...",
     "crumbRequestField": "Jenkins-Crumb"
    }
    ```
    Use the value of "crumb" as the header value, and make sure the key matches "crumbRequestField".

### Using The Automation Rule:
Once the rule is set up:
- Open any JIRA Issue
- Select Actions
- Select the created Automation Rule to trigger the Jenkins job














  
