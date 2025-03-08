package tests;

import api.AuthApi;
import login.WithLogin;
import models.response.BookArrayResponse;
import models.AuthModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class BookStoreTest extends TestBase {
    private static final int BOOK_INDEX = 0;

    @Test
    @Tag("api_ui_test")
    @WithLogin
    void successDeleteBookFromProfileTest() {
        BookArrayResponse collection = booksApi.getBooks();
        AuthModel authResponse = new AuthApi().login();

        booksApi.deleteBooks(authResponse);

        String isbn = collection.getBooks()[BOOK_INDEX].getIsbn();
        String title = collection.getBooks()[BOOK_INDEX].getTitle();
        booksApi.addBook(isbn, authResponse.getToken(), authResponse.getUserId());

        profilePage
                .openPage()
                .verifyBookIsInList(title)
                .deleteBook()
                .verifyDeleteBook()
                .verifyBookIsNotInList(title);
    }
}