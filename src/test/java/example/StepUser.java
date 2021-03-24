package example;

import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.Step;
import org.apache.http.HttpHost;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static example.ui.GoogleAndFlutterPage.*;
import static example.util.CommonFunctions.getId;
import static example.util.GoRestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StepUser {
    public static WebDriver driver;
    private HttpResponse<String> postResponse = null;
    private HttpResponse<JsonNode> response = null;

    @BeforeScenario(tags = "Web")
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterScenario(tags = "Web")
    public void closeBrowser() {
        driver.close();
    }

    @Step("And search about <findWord> and enter to page")
    public void search(String findWord) throws InterruptedException, IllegalMonitorStateException {
        //textBoxGoogleSearch.click(); -- Not Work
        //  driver.wait(3000); -- Not Work
        driver.findElement(By.xpath("//input[@name='q']")).click();
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(findWord);

        driver.findElement(By.name("btnK")).click();
        driver.findElement(By.xpath("//div[contai|s(text(),'Flutter - Framework para Apps')]")).click();
        driver.findElement(By.xpath("//img[@alt='Flutter logo']")).click();
    }

    @Step("I want to go to <url>")
    public void goTo(String url) {
        driver.get(url);
    }

    @Step("Then I should see the flutter dev link is present")
    public void thenWatchLogo() throws InterruptedException {
        //assertThat(flutterLogo.isEnabled());
        driver.wait(2000);
        assertThat(driver.findElement(By.xpath("//img[@alt='Flutter logo']")).isEnabled());
    }

    ////////////////////////////


    @BeforeScenario(tags = "Api")
    public void configApiRequest() {
        System.out.println("--------------- Config Api Request ---------------");
        //Use a proxy
        //Unirest.setProxy(new HttpHost("61.135.217.12", 80));

        //Change the default user-agent header
        Unirest.setDefaultHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0");
    }

    //Make a post request with form data
    @Step("I add a user with first name <name> and last name <lastName> - First way")
    public void addUser(String name, String lastName) throws UnirestException {
        System.out.println("============= First way =============");
        Map<String, Object> request = new HashMap<>();
        request.put("name", name + lastName);
        request.put("email", "emaila@got.com");
        request.put("gender", "Male");
        request.put("status", "Active");
        request.put("id", getId());
        postResponse = Unirest.post(BASE_URL + PATH_POST).fields(request).asString();
        System.out.println(postResponse.getBody());//Does not work

    }

    //Make a post request with body data (Json)
    @Step("I add a user with first name <name> and last name <lastName> - Second way")
    public void addUserSecondWay(String name, String lastName) throws UnirestException {
        System.out.println("============= Second way =============");
        JSONObject request = new JSONObject();
        request.put("name", name + lastName);
        request.put("email", "emaila@got.com");
        request.put("gender", "Male");
        request.put("status", "Active");
        request.put("id", getId());
        postResponse = Unirest.post(BASE_URL + PATH_POST).body(request).asString();
        System.out.println(postResponse.getHeaders()); //Does not work

    }

    @Step("The response status code is <statusCode> POST")
    public void validateStatusCode(String statusCode) {
        assertThat(postResponse.getStatus()).isEqualTo(Integer.parseInt(statusCode));
    }

    @Step("The user was saved correctly")
    public void savedUSer() {

    }

    @Step("I consult a user with number ID <id>")
    public void consultUser(String id) throws UnirestException {
        response = Unirest.get(BASE_URL + PATH_GET_PUT_AND_DELETE).queryString("id", id).asJson();
    }

    @Step("The response status code is <statusCode> GET")
    public void validateStatusCodeGet(String statusCode) {
        assertThat(response.getStatus()).isEqualTo(Integer.parseInt(statusCode));
    }

    @Step("The user whit first name <name> and last name <lastName> should be correct")
    public void validName(String name, String lastName) throws UnirestException {
        //assertThat(response.getBody().getObject().getJSONObject("data[]").get("name")).isEqualTo(name + lastName); Ask how it is work

    }


}
//Make a get request to https://gorest.co.in/

//Make a delete request to https://gorest.co.in/

//Make a put request with body data (Json)




