package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsbnBookModel {
    private String isbn;

    public IsbnBookModel(String isbn) {
        this.isbn = isbn;
    }
}
