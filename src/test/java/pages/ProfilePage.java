package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static constants.Constants.PROFILE_URL;


public class ProfilePage {
    private final SelenideElement deleteButton = $("#delete-record-undefined");
    private final SelenideElement okButton = $("#closeSmallModal-ok");

    @Step("Open Profile page")
    public ProfilePage openPage() {
        open(PROFILE_URL);
        return this;
    }

    @Step("Verify that the book '{title}' is in a list")
    public ProfilePage verifyBookIsInList(String title) {
        String xpath = String.format("//*[text()='%s']", title);
        $x(xpath).shouldBe(visible);
        return this;
    }

    @Step("Delete book")
    public ProfilePage deleteBook() {
        deleteButton.click();
        return this;
    }

    @Step("Verify that the book was deleted")
    public ProfilePage verifyDeleteBook() {
        okButton.click();
        Selenide.switchTo().alert().accept();
        Selenide.switchTo().parentFrame();
        return this;
    }

    @Step("Verify that the book '{title}' isn't in a list")
    public ProfilePage verifyBookIsNotInList(String title) {
        String xpath = String.format("//*[text()='%s']", title);
        $x(xpath).shouldNot(visible);
        return this;
    }
}
