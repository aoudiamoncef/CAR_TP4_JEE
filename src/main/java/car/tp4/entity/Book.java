package car.tp4.entity;

import javax.persistence.*;

/**
 * Class Book
 */
@Entity
public class Book {
  /**
   * id book
   */
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;
  /**
   * author book
   */
  @Column(length=50)
  private String author;
  /**
   * title book
   */
  @Column(length=200)
  private String title;
  /**
   * year book
   */
  @Column(length=10)
  private int year;
  /**
   * quantity book
   */
  @Column(length=50)
  private int quantity;


  /**
   * Constructor
   * @param author
   * @param title
   * @param year
   * @param quantity
   */
  public Book(String author, String title, int year, int quantity) {
    this.author = author;
    this.title = title;
    this.year = year;
    this.quantity = quantity;
  }

  /**
   * Constructor used for Cart
   * @param book
   */
  public Book(Book book) {

    this.author = book.author;
    this.title = book.title;
    this.year = book.year;
    this.quantity = book.quantity;
    this.id = book.id;

  }


  /**
   * getter
   * @return id book
   */
  public long getId() {
    return id;
  }

  /**
   * setter
   * @param id book
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * getter
   * @return quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * setter
   * @param quantity book
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * getter
   * @return year
   */
  public int getYear() {
    return year;
  }

  /**
   * setter
   * @param year book
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * getter
   * @return author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * setter
   * @param author book
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * getter
   * @return title
   */
  public String getTitle() {
    return title;
  }

  /**
   * setter
   * @param title book
   */
  public void setTitle(String title) {
    this.title = title;
  }


/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    if (id != book.id) return false;
    if (year != book.year) return false;
    if (quantity != book.quantity) return false;
    if (author != null ? !author.equals(book.author) : book.author != null) return false;
    return title != null ? title.equals(book.title) : book.title == null;
  }

/* (non-Javadoc)
 * @see java.lang.Object#equals(java.lang.Object)
 */
  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (author != null ? author.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + year;
    result = 31 * result + quantity;
    return result;
  }

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
  @Override
  public String toString() {
    return "Book{" +
            "id=" + id +
            ", author='" + author + '\'' +
            ", title='" + title + '\'' +
            ", year=" + year +
            ", quantity=" + quantity +
            '}';
  }
}
