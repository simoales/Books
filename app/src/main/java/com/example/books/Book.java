package com.example.books;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class Book implements Parcelable {
    public ObservableField<String> id = new ObservableField<String>();
    public String title;
    public ObservableField<String> subTitle= new ObservableField<String>();
    public ObservableField<String> authors= new ObservableField<String>();
    public ObservableField<String> publisher= new ObservableField<String>();
    public ObservableField<String> publishedDate= new ObservableField<String>();
    public ObservableField<String> description= new ObservableField<String>();
    public ObservableField<String> thumbnail= new ObservableField<String>();


    public Book(String id, String title, String subTitle, String[] authors, String publisher,
                String publishedDate, String description, String thumbnail) {

        try {
            this.id.set(id);
            this.title=title;
            this.subTitle.set(subTitle);
            this.authors.set(TextUtils.join(", ", authors));
            this.publisher.set(publisher);
            this.publishedDate.set(publishedDate);
            this.description.set(description);
            this.thumbnail.set(thumbnail);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Book(Parcel in) {
        id.set(in.readString());
        title = (in.readString());
        subTitle.set(in.readString());
        authors.set(in.readString());
        publisher.set(in.readString());
        publishedDate.set(in.readString());
        description.set(in.readString());
        thumbnail.set(in.readString());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id.get());
        parcel.writeString(title);
        parcel.writeString(subTitle.get());
        parcel.writeString(authors.get());
        parcel.writeString(publisher.get());
        parcel.writeString(publishedDate.get());
        parcel.writeString(description.get());
        parcel.writeString(thumbnail.get());
    }

    @BindingAdapter({"android:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        if (!imageUrl.isEmpty()) {
            Picasso.with(view.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.book_open)
                    .into(view);
        }
        else {
            view.setBackgroundResource(R.drawable.book_open);
        }

    }


}
