import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @Test
    public void login(){
        open("https://app.qase.io/login");
        $("#inputEmail").setValue("mashabigdreams@gmail.com");
        $("#inputPassword").setValue("sawdas5620");
        $(".custom-control-indicator").click();
        $("#btnLogin").click();
        $(byXpath("//h1")).shouldHave(text("Projects"));
    }
    @Test
    public void createTestCase(){
        open("https://app.qase.io/login");
        $("#inputEmail").setValue("mashabigdreams@gmail.com");
        $("#inputPassword").setValue("sawdas5620");
        $(".custom-control-indicator").click();
        $("#btnLogin").click();
        $(byXpath("//h1")).shouldHave(text("Projects"));
        $("#createButton").click();
        $(byXpath("//input[@name='title']")).setValue("Best project");
        $(byXpath("//span[@class='vrNjrd']")).click();
        $(byXpath("//div[@class='DJXdnf']")).click();
        $("#public-access-type").click();
        $(byXpath("//button[@type='submit']")).click();
        $("#create-case-button").click();
        $("#title").setValue("Payment");
        $(byXpath("//input[@placeholder='Type to search']")).click();
        $(byXpath("//div[@class='DJXdnf' and text()='High']")).click();
        $(byXpath("//div[@class='RZYgph' and text()='Actual']")).click();
        $(byXpath("//div[@class='DJXdnf' and text()='Smoke']")).click();
        $("#save-case").click();
        $(byXpath("//span[@class='OL6rtE' and text()='Test case was created successfully!']")).shouldHave(text("Test case was created successfully!"));
    }
    @Test
    public void createDefects(){
        open("https://app.qase.io/login");
        $("#inputEmail").setValue("mashabigdreams@gmail.com");
        $("#inputPassword").setValue("sawdas5620");
        $(".custom-control-indicator").click();
        $("#btnLogin").click();
        $(byXpath("//h1")).shouldHave(text("Projects"));
        $("#createButton").click();
        $(byXpath("//input[@name='title']")).setValue("Bad project");
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
        $(byXpath("//span[@class='OL6rtE' and text()='Defect was created successfully!']")).shouldHave(text("Defect was created successfully!"));
    }
}
