package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    // Localizadores
    private final By myAccountMenu = By.linkText("My Account");
    private final By loginOption = By.linkText("Login");
    private final By emailField = By.id("input-email");
    private final By passwordField = By.id("input-password");
    private final By editAccountLink = By.linkText("Edit Account");
    private By firstNameField = By.id("input-firstname");
    private final By loginButton = By.xpath("//input[@value='Login']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Métodos para interactuar con la página
    public void navigateToLogin() {
        // Haz clic en "My Account" y luego en "Login"
        driver.findElement(myAccountMenu).click();
        driver.findElement(loginOption).click();
    }

    public void login(String email, String password) {
        // Ingresa credenciales y haz clic en el botón de login
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void clickEditAccount() {
        driver.findElement(editAccountLink).click(); // Haz clic en "Edit Account"
    }

    public String getFirstName() {
        return driver.findElement(firstNameField).getAttribute("value"); // Extrae el valor del campo "First Name"
    }

    public boolean isLoginSuccessful(String expectedName) {
        // Validar si el login fue exitoso verificando un texto esperado
        return driver.getPageSource().contains(expectedName);
    }
}
