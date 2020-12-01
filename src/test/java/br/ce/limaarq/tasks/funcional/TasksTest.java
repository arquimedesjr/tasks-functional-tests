package br.ce.limaarq.tasks.funcional;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() throws MalformedURLException {
//        WebDriver driver = new ChromeDriver();
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.14:4444/wd/hub"), cap);
        driver.navigate().to("http://192.168.0.14:8001/tasks");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            // clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            // escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // escreve a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar a mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

        } finally {
            // fecha o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            // clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            // escreve a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar a mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);

        } finally {
            // fecha o browser
            driver.quit();
        }
    }


    @Test
    public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            // clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            // escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar a mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);

        } finally {
            // fecha o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
        WebDriver driver = acessarAplicacao();

        try {
            // clicar em Add Todo
            driver.findElement(By.id("addTodo")).click();

            // escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            // escreve a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

            // clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            // validar a mensagem de sucesso
            String message = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);

        } finally {
            // fecha o browser
            driver.quit();
        }
    }


}
