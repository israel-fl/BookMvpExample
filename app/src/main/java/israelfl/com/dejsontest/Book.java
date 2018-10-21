package israelfl.com.dejsontest;

/**
 * Immutable model class for a book
 */
public class Book {

    String name;

    String boxArt;


    public Book(String name, String boxArt) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", boxArt='" + boxArt + '\'' +
                '}';
    }

}
