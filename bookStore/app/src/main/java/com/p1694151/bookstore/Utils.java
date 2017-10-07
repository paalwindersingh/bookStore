package com.p1694151.bookstore;

import com.p1694151.bookstore.model.Author;

import java.util.List;

/**
 * Created by paalwinder on 07/10/17.
 */

public class Utils {

    public static String getAuthorName(int authorId, Author[] authors) {
        for (int i = 0; i < authors.length; i++) {
            Author author = authors[i];
            if (author.getId() == authorId) {
                return author.getName();
            }
        }
        return null;
    }

    public static String getAuthorImage(int authorId, Author[] authors) {
        for (int i = 0; i < authors.length; i++) {
            Author author = authors[i];
            if (author.getId() == authorId) {
                return author.getImage();
            }
        }
        return null;
    }
}
