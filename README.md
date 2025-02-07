# Page Object Model (POM) Framework in Java

This repository contains a Page Object Model (POM)-based automation framework for web application testing. Built using Selenium and Java, this framework is designed to enhance test automation by promoting modularity, reusability, and maintainability.

## Features

- Page Object Model (POM) Design Pattern: Separates test logic from page-specific logic for better maintainability.

- TestNG Integration: Supports parallel execution, data-driven testing, and advanced reporting.

- Reusable Components: Common utilities and helper classes for seamless test automation.

- Cross-Browser Testing: Supports testing across multiple browsers (Chrome, Firefox, Edge, etc.).

- Logging and Reporting: Integrated with ExtentReports for detailed test execution reports.

- CI/CD Ready: Easily integrates with CI/CD tools like Jenkins for continuous testing.

# Prerequisites

Before running the tests, ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher

- Maven (for dependency management)

- ChromeDriver or other browser drivers (ensure they match your browser version)

- IDE (IntelliJ IDEA, Eclipse, or any preferred IDE)

# Setup Instructions

## Clone the Repositorybash

```bash

git clone https://github.com/waseemofficial/page_object_model_java.git

cd page_object_model_java
```
## Install Dependencies:
  
Run the following command to install all required dependencies:

```bash
   mvn clean install
```
## Update Browser Drivers:

- Download the appropriate browser driver (e.g., ChromeDriver) and place it in the src/test/resources/drivers directory.

- Update the browser and driverPath properties in the config.properties file located in src/test/resources.

## Run Tests:
Execute the tests using the following command:

```bash   
mvn test
```

## Project Structure

```
page_object_model_java/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/              # Page classes for each web page
│   │       ├── utils/              # Utility classes (e.g., WebDriver setup, helpers)
│   │       └── BasePage.java       # Base class for page objects
│   └── test/
│       ├── java/
│       │   ├── tests/              # Test classes
│       │   └── TestBase.java       # Base class for tests
│       └── resources/
│           ├── drivers/            # Browser drivers
│           ├── config.properties   # Configuration file
│           └── testdata/           # Test data files (e.g., Excel, JSON)
├── pom.xml                         # Maven dependencies
└── README.md                       # Project documentation
```

## Example Test

Here’s an example of a test case using the POM framework:

```java 

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import tests.TestBase;

public class LoginTest extends TestBase {

    @Test
    public void testSuccessfulLogin() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.clickLoginLink();
        loginPage.enterUsername("testuser");
        loginPage.enterPassword("password123");
        loginPage.clickLoginButton();
        homePage.verifyWelcomeMessage("Welcome, testuser!");
    }
}
```
## Reports

After test execution, detailed reports are generated using ExtentReports. You can find the reports in the test-output/ExtentReports directory.
Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

- Fork the repository.

- Create a new branch for your feature or bugfix.

- Submit a pull request with a detailed description of your changes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
