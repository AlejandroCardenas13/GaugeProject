package example.ui;

import example.StepUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GoogleAndFlutterPage extends StepUser {


    public static WebElement textBoxGoogleSearch = driver.findElement(By.xpath("//input[@name='q']"));
    public static WebElement btnGoogleSearch = driver.findElement(By.name("btnK"));
    public static WebElement flutterPage = driver.findElement(By.xpath("//div[contains(text(),'Flutter - Framework para Apps Móviles')]"));
    public static WebElement flutterLogo = driver.findElement(By.xpath("//img[@alt='Flutter logo']"));
}
