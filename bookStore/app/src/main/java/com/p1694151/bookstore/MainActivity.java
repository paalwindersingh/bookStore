package com.p1694151.bookstore;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.p1694151.bookstore.model.Author;
import com.p1694151.bookstore.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> categories = new ArrayList<>();

    ListView mylistview;
    EditText searchEt;
    Author[] authors;
    Book[] books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylistview = (ListView) findViewById(R.id.listview);
        searchEt = (EditText) findViewById(R.id.etSearchText);

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONObject authorsObj = obj.getJSONObject("authors");

            Iterator<String> authorKeys = authorsObj.keys();
             authors = new Author[authorsObj.length()];

            int authorIndex = 0;
            while (authorKeys.hasNext()) {
                String key = authorKeys.next();
                JSONObject author = authorsObj.getJSONObject(key);
                String name = author.getString("name");
                String image = author.getString("image");
                authors[authorIndex] = new Author(Integer.valueOf(key), name, image);
                authorIndex++;
            }

            JSONObject booksObject = obj.getJSONObject("books");
            books = new Book[booksObject.length()];
            Iterator<String> bookKeys = booksObject.keys();

            int index = 0;
            while (bookKeys.hasNext()) {
                String key = bookKeys.next();
                JSONObject book = booksObject.getJSONObject(key);
                int author_id = book.getInt("author_id");
                String category = book.getString("category");
                String cover = book.getString("cover");
                String date = book.getString("date");
                String publisher = book.getString("publisher");
                int quantity = book.getInt("quantity");
                boolean shipToCanada = book.getBoolean("shipToCanada");
                boolean shipToUSA = book.getBoolean("shipToUSA");
                String title = book.getString("title");
                books[index] = new Book(Integer.valueOf(key), author_id, category, cover, date, publisher, quantity, shipToCanada, shipToUSA, title);
                index++;
            }

            JSONArray categoryArray = obj.getJSONArray("category");
            for (int i = 0; i < categoryArray.length(); i++) {
                categories.add(categoryArray.getString(i));
            }

            BookAdapater bookAdapater = new BookAdapater(this, authors, books);
            mylistview.setAdapter(bookAdapater);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        searchEt.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();
                List<Book> tempBooks = new ArrayList<Book>();
                for (int i = 0; i < books.length; i++) {

                    final String text = books[i].getTitle().toLowerCase();
                    if (text.contains(query)) {
                        tempBooks.add(books[i]);
                    }
                }
                Book[] books = new Book[tempBooks.size()];
                for(int i=0;i<tempBooks.size();i++){
                    books[i] = tempBooks.get(i);
                }
                BookAdapater bookAdapater = new BookAdapater(MainActivity.this, authors, books);
                mylistview.setAdapter(bookAdapater);  // data set changed
            }
        });
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
