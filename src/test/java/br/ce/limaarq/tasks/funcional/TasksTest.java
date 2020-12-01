package br.ce.limaarq.tasks.funcional;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\qui_j\\Documents\\Desenvolvimento\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() {
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
    public void naoDeveSalvarTarefaSemDescricao() {
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
    public void naoDeveSalvarTarefaSemData() {
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
    public void naoDeveSalvarTarefaComDataPassada() {
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
