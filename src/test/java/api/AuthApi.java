package api;

import io.qameta.allure.Step;
import models.LoginModel;
import models.AuthModel;

import static constants.Constants.LOGIN_URL;
import static constants.Credentials.PASSWORD;
import static constants.Credentials.USERNAME;
import static io.restassured.RestAssured.given;
import static specs.TestSpec.requestSpec;
import static specs.TestSpec.responseSpecWithStatusCode200;

public class AuthApi {

    @Step("API authorization")
    public AuthModel login() {

        LoginModel loginModel = new LoginModel();
        loginModel.setUserName(USERNAME);
        loginModel.setPassword(PASSWORD);

        return
                given(requestSpec)
                        .body(loginModel)
                        .when()
                        .post(LOGIN_URL)
                        .then()
                        .spec(responseSpecWithStatusCode200)
                        .extract().as(AuthModel.class);
    }
}


