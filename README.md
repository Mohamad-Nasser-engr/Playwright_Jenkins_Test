# 📘 Playwright with Java & JUnit – Research Findings

## 🧩 Overview

This document summarizes my research as a DevOps intern on using [Microsoft Playwright](https://playwright.dev/java/) for end-to-end testing with **Java** and **JUnit**. It focuses on how Playwright can facilitate rapid test creation via its **codegen recorder**, and how it fits into modern DevOps pipelines.

---

## 🚀 Why Playwright?

Playwright is an open-source test automation framework by Microsoft that supports:
- Cross-browser testing (Chromium, Firefox, WebKit)
- Headless & headed execution
- Native support for multiple languages (JavaScript, Python, Java, C#)
- Auto-wait and smart assertions
- **Recorder tool to auto-generate tests**

Compared to traditional tools like **Selenium**, Playwright offers:
- Better support for modern web apps (e.g., React, Angular)
- Fewer flaky tests due to built-in waiting
- A faster, simpler test setup

---

## 🧪 Technology Stack

- **Playwright** v1.44.0 (Java binding)
- **Java** 17
- **JUnit 5**
- **Maven** (for project and dependency management)

---

## ⚙️ Installation & Setup

### 1. Add Playwright to your Maven `pom.xml`:

```xml
<dependency>
  <groupId>com.microsoft.playwright</groupId>
  <artifactId>playwright</artifactId>
  <version>1.44.0</version>
</dependency>
