package car.tp4.entity;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

import javax.ejb.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import java.util.List;


@Startup
@Singleton
public class InitBean {

        public enum States {BEFORESTARTED, STARTED, PAUSED, SHUTTINGDOWN};
        private States state;

    @EJB
    private BookBean bookBean;


    @PostConstruct
        public void initialize() {
            state = States.BEFORESTARTED;
            // Perform intialization
            state = States.STARTED;
            System.out.println("Service Started");

            bookBean.addBook(new Book("The Lord of the Rings", "J. R. R. Tolkien",1980,10));
            bookBean.addBook(new Book("Leonardo Da Vinci", "Codex Urbinas",1651,10));
            bookBean.addBook(new Book("Lmulud At Maammer", "La Colline oubliée", 1952,10));
            bookBean.addBook(new Book("Lmulud At Maammer","Les Isefra",  1978,15));
            bookBean.addBook(new Book("Lmulud At Maammer", "Poèmes kabyles anciens", 1970,50));
            bookBean.addBook(new Book("Yacine Kateb", "Nedjma", 1956,12));
            bookBean.addBook(new Book("Marguerite AMROUCHE","Grain Magique",  1966,10));
            bookBean.addBook(new Book("Tahar Djaout", "un poète peut-il mourir ?", 2012,22));

    }
        @PreDestroy
        public void terminate() {
            state = States.SHUTTINGDOWN;
            // Perform termination
            System.out.println("Shut down in progress");
        }
        public States getState() {
            return state;
        }
        public void setState(States state) {
            this.state = state;
        }
    }