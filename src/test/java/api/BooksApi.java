package api;

import io.qameta.allure.Step;
import models.*;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.BOOKS_URL;
import static io.restassured.RestAssured.given;
import static specs.TestSpec.*;

public class BooksApi {

    @Step("API. Add a book")
    public AddBookResponse addBook(String isb, String token, String userId) {

        List<IsbnBookModel> books = new ArrayList<>();
        books.add(new IsbnBookModel(isb));

        AddBookBodyModel bookData = new AddBookBodyModel();
        bookData.setUserId(userId);
        bookData.setCollectionOfIsbns(books);
        return given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post(BOOKS_URL)
                .then()
                .spec(responseSpecWithStatusCode201)
                .extract().as(AddBookResponse.class);
    }

    @Step("API. Get books")
    public BookCollectionResponse getBooks() {
        return given(requestSpec)
                .when()
                .get(BOOKS_URL)
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(BookCollectionResponse.class);
    }

    @Step("API. Delete books")
    public void deleteBooks(AuthModel loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete(BOOKS_URL)
                .then()
                .spec(responseSpecWithStatusCode204);
    }
}
