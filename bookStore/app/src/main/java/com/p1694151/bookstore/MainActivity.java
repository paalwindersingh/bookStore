package com.p1694151.bookstore;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.p1694151.bookstore.model.Author;
import com.p1694151.bookstore.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<String > categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONObject authorsObj = obj.getJSONObject("authors");

            Iterator<String> authorKeys = authorsObj.keys();

            while (authorKeys.hasNext()) {
                JSONObject author = authorsObj.getJSONObject(authorKeys.next());
                String name = author.getString("name");
                String image = author.getString("image");
                authors.add(new Author(name, image));
            }

            JSONObject booksObject = obj.getJSONObject("books");

            Iterator<String> bookKeys = booksObject.keys();

            while (bookKeys.hasNext()) {
                JSONObject book = booksObject.getJSONObject(bookKeys.next());
                int author_id = book.getInt("author_id");
                String category = book.getString("category");
                String cover = book.getString("cover");
                String date = book.getString("date");
                String publisher = book.getString("publisher");
                int quantity = book.getInt("quantity");
                boolean shipToCanada = book.getBoolean("shipToCanada");
                boolean shipToUSA = book.getBoolean("shipToUSA");
                String title = book.getString("title");
                books.add(new Book(author_id, category, cover, date, publisher, quantity, shipToCanada, shipToUSA, title));
            }

            JSONArray categoryArray = obj.getJSONArray("category");
            for (int i=0;i<categoryArray.length();i++){
                categories.add(categoryArray.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            AssetManager am = getAssets();
            InputStream is = am.open("books.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
