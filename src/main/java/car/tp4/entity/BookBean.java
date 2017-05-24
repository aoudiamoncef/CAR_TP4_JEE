package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import java.text.Normalizer;
import java.util.*;

/**
 * Class BookBean
 */
@Stateless
@Local
public class  BookBean {
  /**
   * entity manager
   */
  @PersistenceContext(unitName = "book-pu")
  private EntityManager entityManager;

  /**
   * add book to db
   * @param book object
   */
  public void addBook(Book book) {
    entityManager.persist(book);
  }

  /**
   * get all books in db
   * @return list of books
   */
  public List<Book> getAllBooks() {
    Query query = entityManager.createQuery("SELECT m from Book as m");
    return query.getResultList();
  }

  /**
   * get book
   * @param id
   * @return list of book
   */
  public List<Book> getBook(int id) {
    Query query = entityManager.createQuery("SELECT m from Book as m WHERE m.id=?1");
    query.setParameter(1, id);
    return query.getResultList();
  }

  /**
   * get all authos in db
   * @return list of authors
   */
  public List<String> getAllAuthors() {

    Query query = entityManager.createNativeQuery("SELECT m.author FROM Book as m");

    List<String> authorsAll = query.getResultList();

    ArrayList<String> authors = new ArrayList<String> (authorsAll) ;

    Set<String> hs = new HashSet <String>();
    hs.addAll(authors);
    authors.clear();
    authors.addAll(hs);

    return authors ;
  }

  /**
   * get sorted book list by asc
   * @return list of books
   */
  public List<Book> getSortedBooksAsc() {
    Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.year ASC ");
    return query.getResultList();
  }

  /**
   * get sorted book list by desc
   * @return list of books
   */
  public List<Book> getSortedBooksDesc() {
    Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.year DESC ");
    return query.getResultList();
  }

  /**
   * delete book from db
   * @param id
   * @param number
   * @return list of books
   */
  public List<Book> deleteBook(int id, int number) {
    System.out.println("deleteBook");
    Query queryUpdate = entityManager.createQuery("UPDATE Book m SET m.quantity=m.quantity-?1 WHERE m.id=?2 AND m.quantity>=1");
    queryUpdate.setParameter(1, number);
    queryUpdate.setParameter(2, id);
    queryUpdate.executeUpdate();

    Query queryDelete = entityManager.createQuery("DELETE from Book as m WHERE m.id=?1 AND m.quantity<1");
    queryDelete.setParameter(1, id);
    queryDelete.executeUpdate();

    Query query = entityManager.createQuery("SELECT m from Book as m");
    return query.getResultList();
  }

  /**
   * delete single book
   * @param id
   * @param number
   */
  public void deleteBookOnly(int id, int number) {
    System.out.println("deleteBook");
    Query queryUpdate = entityManager.createQuery("UPDATE Book m SET m.quantity=m.quantity-?1 WHERE m.id=?2 AND m.quantity>=1");
    queryUpdate.setParameter(1, number);
    queryUpdate.setParameter(2, id);
    queryUpdate.executeUpdate();

    Query queryDelete = entityManager.createQuery("DELETE from Book as m WHERE m.id=?1 AND m.quantity<1");
    queryDelete.setParameter(1, id);
    queryDelete.executeUpdate();

  }


  /**
   * add book quantity
   * @param id
   * @param number
   * @return list of books
   */
  public List<Book> AddBookQuantity(int id, int number) {

    Query queryAdd = entityManager.createQuery("UPDATE Book as m SET m.quantity=m.quantity+?1 WHERE m.id=?2 AND m.quantity>1");
    queryAdd.setParameter(1, number);
    queryAdd.setParameter(2, id);
    queryAdd.executeUpdate();

    Query query = entityManager.createQuery("SELECT m from Book as m");
    return query.getResultList();
  }


  /**
   * find book from given title
   * @param title
   * @return list of fended books
   */

  public List<Book> findTitle(String title){

    Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.year DESC ");

    List<Book> findedBookList = query.getResultList();

    ArrayList<Book> findedBookTitle = new ArrayList <Book>();


    String titleSearch= Normalizer.normalize(title.toLowerCase(),Normalizer.Form.NFD).replaceAll("\\s","").replaceAll("[^\\p{ASCII}]","");


        for (Book book : findedBookList) {

          if (Normalizer.normalize(book.getTitle().toLowerCase(), Normalizer.Form.NFD).replaceAll("\\s", "").replaceAll("[^\\p{ASCII}]", "").contains(titleSearch)) {

            findedBookTitle.add(book);
          }

        }

    return findedBookTitle;

  }

  /**
   * find book from given author
   * @param author
   * @return list of fended books
   */
  public List<Book> findAuthor(String author){
    Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.year DESC ");

    List<Book> findedBookList = query.getResultList();

    ArrayList<Book> findedBookAuthor = new ArrayList <Book>();

    String authorSearch = Normalizer.normalize(author.toLowerCase(),Normalizer.Form.NFD).replaceAll("\\s","").replaceAll("[^\\p{ASCII}]","");


    for (Book book : findedBookList) {

      if (Normalizer.normalize(book.getAuthor().toLowerCase(), Normalizer.Form.NFD).replaceAll("\\s", "").replaceAll("[^\\p{ASCII}]", "").contains(authorSearch)) {

        findedBookAuthor.add(book);
      }

    }

    return findedBookAuthor;

  }

}

