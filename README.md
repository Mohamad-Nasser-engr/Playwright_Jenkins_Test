# 📘 Playwright with Java & JUnit – Research Findings

## 🧩 Overview

This document summarizes my research on using [Microsoft Playwright](https://playwright.dev/java/) for end-to-end testing with **Java** and **JUnit**. It focuses on how Playwright can facilitate rapid test creation via its **codegen recorder**, and how it fits into modern DevOps pipelines.

---

## 🚀 Why Playwright?

Playwright is an open-source test automation framework by Microsoft that supports:
- Cross-browser testing (Chromium, Firefox, WebKit)
- Headless & headed execution
- Native support for multiple languages (JavaScript, Python, Java, C#)
- Auto-wait and smart assertions
- **Recorder tool to auto-generate tests**

---

## 🧪 Technology Stack

- **Playwright** v1.51.0 
- **Java** 
- **JUnit 5**
- **Maven** (for project and dependency management)

---

## ⚙️ Installation & Setup

### 1. Add Playwright and Junit 5 to your Maven `pom.xml`:

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
### 2. Install Playwright recorder
This step installs Playwright using the Node.js version, which is required to record and generate Java test code.
```bash
npx playwright install 
```
### 3. Use Playwright's recorder 
This will generate Java + JUnit code you can paste into your test classes.
```bash
npx playwright codegen https://example.com
```

---

## Integration with Jenkins 
To integrate your Playwright tests with Jenkins, follow these steps to set up a continuous integration (CI) pipeline that automatically runs your tests.
### 1. Install Jenkins
### 2. Install Required Plugins
Ensure the following plugins are installed (Manage Jenkins → Manage Plugins):
- Git Plugin – For Git integration
- GitHub Plugin – For GitHub webhooks and integration
- JUnit – To publish test results
- Maven Integration – To build Maven projects
### 3. Create and configure a new Maven Project:
- In Jenkins, click New Item, name your job, and select Maven Project.
- Under Source Code Management, select Git and enter your GitHub repository URL.
- In the Build section:

  Root POM:
  ```
  pom.xml
  ```
  Goals and Options:
  ```
  clean install
  ```
  This runs your tests based on the pom.xml configuration.
  ### Note on ***pom.xml*** for Jenkins Compatibility:
  To ensure Jenkins can execute your Playwright tests via Maven:
  - The project defines maven-surefire-plugin, which runs JUnit 5 tests during the test phase:
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

---

## Jenkins Integration with JIRA
Integrating Jenkins with JIRA allows automatic linking of build results, commits, and issues, making it easier to track development progress directly from JIRA.
### Steps to integrate Jenkins with JIRA
### 1. Install the Jenkins JIRA Plugin
- Go to Manage Jenkins → Manage Plugins
- Under the Available tab, search for and install JIRA Plugin
- Restart Jenkins if prompted
### 2. Create a JIRA API Token (for Atlassian Cloud users)
If you're using Atlassian Cloud, you need an API token to authenticate Jenkins.

To generate one:
1. Visit https://id.atlassian.com/manage/api-tokens
2. Click Create API token
3. Enter a label like "Jenkins Integration", then click Create
4. Copy the generated token (you won’t be able to view it again)
5. In Jenkins:
   - Go to Manage Jenkins → Credentials
   - Click **Add Credentials**
     - **Kind**: Username with password
     - **Username**: Your Atlassian account email
     - **Password**: Paste the API token
     - **ID/Description**: Optional (e.g., jira-api-token)
### 3. Configure JIRA site in Jenkins
- Go to Manage Jenkins → Configure System
- Scroll to the JIRA section
- Click Add Jira Site
    - Enter the JIRA server URL (e.g., https://yourcompany.atlassian.net)
    - Add the previously created credentials (JIRA API token)
    - Test the connection
### 4. Link JIRA site in a Jenkins Job
- Open your Jenkins Job → Configure
- Scroll to the JIRA site section
- Select the JIRA site you previously configured
  
