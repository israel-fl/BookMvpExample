package flores.israel.androidcodingchallenge.data.source.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Immutable model class for a book
 */
public class Book {

    @SerializedName("title")
    @Expose
    String name;

    @SerializedName("author")
    @Expose
    String author;

    @SerializedName("imageURL")
    @Expose
    String boxArt;

    public Book() {
        // No args constructor for use in deserialization
    }

    public Book(String name, String author, String boxArt) {
        this.name = name;
        this.author = author;
        this.boxArt = boxArt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoxArt() {
        return boxArt;
    }

    public void setBoxArt(String boxArt) {
        this.boxArt = boxArt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", boxArt='" + boxArt + '\'' +
                '}';
    }

}
