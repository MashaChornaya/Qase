import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginTest {

private final static String EMAIL="mashabigdreams@gmail.com";
private final static String PASSWORD="sawdas5620";
private final static String PROJECT="Best project";
private final static String TESTCASE="Payment";
private final static String DEFECT="Bad project";
private final static String SEARCH_TEST_CASE= "//div[@data-handler-id]/descendant::div[text()='%s']";
private final static String SEARCH_DEFECT= "//a[@class='defect-title'and text()='%s']";
    @BeforeMethod
    public void navigate() {
        open("https://app.qase.io/login");
    }
    @Test
    public void login(){
        $("#inputEmail").setValue(EMAIL);
        $("#inputPassword").setValue(PASSWORD);
        $(".custom-control-indicator").click();
        $("#btnLogin").click();
        $(byXpath("//h1")).shouldHave(text("Projects"));
        Assert.assertTrue($(By.xpath("//h1[text()='Projects']")).isDisplayed());
    }
    @Test
    public void createTestCase(){
        $("#inputEmail").setValue("mashabigdreams@gmail.com");
        $("#inputPassword").setValue("sawdas5620");
        $(".custom-control-indicator").click();
        $("#btnLogin").click();
        $(byXpath("//h1")).shouldHave(text("Projects"));
        $("#createButton").click();
        $(byXpath("//input[@name='title']")).setValue(PROJECT);
        $("#public-access-type").click();
        $(byXpath("//button[@type='submit']")).click();
        $("#create-case-button").click();
        $("#title").sendKeys(TESTCASE);
        $("#suite").sendKeys("Test cases without suite");
        $("#save-case").click();
        $("span.OL6rtE").shouldHave(text("Test case was created successfully!"));
        Assert.assertEquals(($(By.xpath(String.format(SEARCH_TEST_CASE, TESTCASE))).getText()),TESTCASE);
    }
    @Test
    public void createDefects(){
        $("#inputEmail").setValue("mashabigdreams@gmail.com");
        $("#inputPassword").setValue("sawdas5620");
        $(".custom-control-indicator").click();
        $("#btnLogin").click();
        $(byXpath("//h1")).shouldHave(text("Projects"));
        $("#createButton").click();
        $(byXpath("//input[@name='title']")).setValue(PROJECT);
        $("#public-access-type").click();
        $(byXpath("//button[@type='submit']")).click();
        $(byXpath("//span[@class='nbWgel' and text()='Defects']")).click();
        $(byXpath("//a[@class='btn btn-primary' and text()='Create new defect']")).click();
        $("#title").setValue("Search by author not working");
        $(byXpath("//div[@class='ProseMirror toastui-editor-contents']")).setValue("1)Go to the webpage https://www.sharelane.com/cgi-bin/main.py\n" +
                "\n" +
                "2)Enter the author of the book in the search field\n" +
                "\n" +
                "3)Expected result: All books by this author found\n" +
                "\n" +
                "Actual result: Nothing found");
        $(byXpath("//button[@type='submit']")).click();
        $(byXpath("//span[@class='OL6rtE']")).shouldHave(text("Defect was created successfully!"));
        Assert.assertEquals(($(byXpath(String.format(SEARCH_DEFECT,DEFECT))).getText()),DEFECT);
    }
    @AfterMethod
    public void close() {
        getWebDriver().quit();
    }
}
