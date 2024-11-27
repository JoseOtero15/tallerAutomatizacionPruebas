package org.example.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.example.pages.LoginPage;
import org.example.utils.CSVReaderUtil;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    CSVReaderUtil csvReaderUtil;

    @BeforeClass
    public void setUp() {
        // Configuración de WebDriver (asegúrate de usar la ruta correcta al chromedriver)
        String chromedriverPath = "src/main/resources/chromedriver.exe"; // Ruta al chromedriver.exe
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opencart.abstracta.us");

        loginPage = new LoginPage(driver);
        csvReaderUtil = new CSVReaderUtil(); // Crear el lector CSV
    }

    @Test
    public void testValidLogin() throws Exception {
        // Leer las credenciales desde el archivo CSV (usando la fila 1, por ejemplo)
        String[] loginData = csvReaderUtil.getLoginData("src/test/resources/testdataLoginCSV.csv", 1); // Ruta al CSV en src/test/resources
        String email = loginData[0];   // email
        String password = loginData[1]; // password

        // Realizar el login
        loginPage.navigateToLogin();
        loginPage.login(email, password);

        // Verificar que el login fue exitoso
        Assert.assertTrue(loginPage.isLoginSuccessful("My Account"), "Login fallido.");

        // Navegar a "Edit Account" y obtener el nombre del usuario
        loginPage.clickEditAccount();
        String actualFirstName = loginPage.getFirstName();

        System.out.println("El NOMBRE del USUARIO extraído del formulario es: " + actualFirstName);

        // Validar que el nombre obtenido coincida con el esperado
        String expectedFirstName = "test"; // Nombre esperado
        Assert.assertEquals(actualFirstName, expectedFirstName, "El nombre del usuario no coincide.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
