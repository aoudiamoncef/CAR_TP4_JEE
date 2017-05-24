package car.tp4.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import java.text.Normalizer;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

/**
 * Class CarBean
 */
@Stateful
@StatefulTimeout(value = 20)
@Local
public class CartBean  {


    /**
     * EJB BookBean
     */
    @EJB
    private BookBean bookBean;
    /**
     * List of books in cart
     */
    private List<Book> books = new ArrayList <Book>();

    /**
     * init books list
     */
    @PostConstruct
        private void initializeBean(){
            books = new ArrayList<Book>();


            System.out.println("initialisation de la cart");
        }

    /**
     * add book to cart
     * @param id
     * @param number
     */
    public void addBookToCart(int id ,int number) {


        Book bookAdded = new Book (bookBean.getBook(id).get(0));


            System.out.println("azul");
            if(!checkBookAddCart(id,number)){

                bookAdded.setQuantity(number);
                books.add(bookAdded);
            }
        }

    /**
     * check before adding book
     * @param id
     * @param number
     * @return boolean
     */
    public boolean checkBookAddCart(int id,int number) {

    int bookOrigin = bookBean.getBook(id).get(0).getQuantity();

    for (Book book : books) {
        if (book.getId() == id){
            if (bookOrigin>=book.getQuantity()+number){
                book.setQuantity(book.getQuantity() + number);
                return true;
            }else{
                return true;
            }

        }
    }
    return false;
}

    /**
     * remove book from cart
     * @param id
     * @param number
     */
    public void removeBookFromCart(int id,int number) {

         if(!checkBookRemoveCart(id,number)) {
             System.out.println("entre dans la condition");

             for (Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
                 Book book = iterator.next();
                 if (book.getId()==id) {
                     // Remove the current element from the iterator and the list.
                     iterator.remove();
                 }
             }

         }
        }

    /**
     * check brefore delete book
     * @param id
     * @param number
     * @return boolean
     */
    public boolean checkBookRemoveCart(int id,int number) {

        for (Book book : books) {
            System.out.println("quatité to remove " + book.getQuantity());
            if (book.getId() == id && (book.getQuantity() - number >0)) {
                    book.setQuantity(book.getQuantity() - number);
                System.out.println("return true ");
                    return true;
            }else{
                System.out.println("return false ");
                   return false;
            }

        }
        return false;
    }

    /**
     * validate cart content and remove it from BookBean
     */
    public void validateCartOrder(){

        for (Book book :books){
             bookBean.deleteBookOnly((int) book.getId(), book.getQuantity());
        }

    }

    /**
     * display cart content
     * @return list of books
     */
    public List<Book> displayCart() {

        for (Book book : books) {

            System.out.println("ajouter au panier "+book.getTitle());

        }
        return books;
    }

    /**
     * remove books list
     */
    public void clearCart() {
         books.clear();
    }

}