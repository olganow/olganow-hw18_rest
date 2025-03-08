package api;

import io.qameta.allure.Step;
import models.*;
import models.response.BookArrayResponse;
import models.response.BooksListResponse;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.BOOKS_URL;
import static io.restassured.RestAssured.given;
import static specs.TestSpec.*;

public class BooksApi {

    @Step("API. Add a book")
    public BooksListResponse addBook(String isb, String token, String userId) {

        List<IsbnBookModel> books = new ArrayList<>();
        books.add(new IsbnBookModel(isb));

        AddBookModel bookData = new AddBookModel();
        bookData.setUserId(userId);
        bookData.setCollectionOfIsbns(books);
        return given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post(BOOKS_URL)
                .then()
                .spec(responseSpecWithStatusCode201)
                .extract().as(BooksListResponse.class);
    }

    @Step("API. Get books")
    public BookArrayResponse getBooks() {
        return given(requestSpec)
                .when()
                .get(BOOKS_URL)
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(BookArrayResponse.class);
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
