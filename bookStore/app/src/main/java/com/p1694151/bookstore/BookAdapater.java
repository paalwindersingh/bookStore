package com.p1694151.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.p1694151.bookstore.model.Author;
import com.p1694151.bookstore.model.Book;

public class BookAdapater extends ArrayAdapter<Book> {

    private Author[] authors;

    public BookAdapater(Context context, Author[] authors, Book... objects) {
        super(context, R.layout.view_book_item, objects);
        this.authors = authors;
        //Context is the context we are in... For our example it will be within a listview.
        //Layout: we are going to make a layout with feed in mind.
        //Objects: This will be whatever else we need to pass to it.
    }

    //This fun
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflator = LayoutInflater.from(getContext());  //Instantiates a layout XML file into its corresponding object
        //In our test case it would our ListView object. Context will change where you use.
        //Think of it as prepare data.

        View itemView = myInflator.inflate(R.layout.view_book_item, parent, false);

        /***Think of the inflator part as in it converts the xml layout into a widget!*****/

        //Get our widgets

        TextView authorTv = (TextView) itemView.findViewById(R.id.author);
        ImageView coverIv = (ImageView) itemView.findViewById(R.id.cover);
        TextView dateTv = (TextView) itemView.findViewById(R.id.date);
        TextView titleTv = (TextView) itemView.findViewById(R.id.title);
        TextView countTv = (TextView) itemView.findViewById(R.id.count);
        TextView notAvailableTv = (TextView) itemView.findViewById(R.id.not_available);

        //Get our values from Json
        final Book book = getItem(position);
        if (book != null) {
            authorTv.setText(Utils.getAuthorName(book.getAuthor_id(), authors));
            titleTv.setText(book.getTitle());
            dateTv.setText(book.getDate());
            countTv.setText(String.valueOf(book.getQuantity()));
            notAvailableTv.setVisibility(book.isShipToCanada() && book.isShipToUSA() ? View.VISIBLE : View.INVISIBLE);
            if (!book.isShipToCanada()) {
                notAvailableTv.setText("Can\'t be shipped to Canada");
            } else if (!book.isShipToUSA()) {
                notAvailableTv.setText("Can\'t be shipped to USA");
            }
            new DownloadImageTask(coverIv)
                    .execute(book.getCover());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (book.getQuantity() > 0) {
                        Toast.makeText(getContext(), "Book added to your cart", Toast.LENGTH_SHORT).show();
                        book.setQuantity(book.getQuantity() - 1);
                    }else{
                        Toast.makeText(getContext(), "This book is currently unavailable! Please check again later.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        return itemView;  //Sending the view back, in this case as a row.
    }
}
