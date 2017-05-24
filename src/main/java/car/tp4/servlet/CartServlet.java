package car.tp4.servlet;

import car.tp4.entity.*;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    /**
     * EJB BookBean
     */
    @EJB
    private BookBean bookBean;
    /**
     * EJB CartBean
     */
    @EJB
    private CartBean cartBean;
    /**
     * doGet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        if (request.getSession().getAttribute("email") != null && request.getSession().getAttribute("password") != null) {

            if (cartBean.displayCart()!=null) {

                request.setAttribute("cart", cartBean.displayCart());

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
                dispatcher.forward(request, response);

            } else {

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/books.jsp");
                dispatcher.forward(request, response);
        }

        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * doPost
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        System.out.println("btndelete is: "+request.getParameter("btndelete"));

        System.out.println("btnadd is: "+request.getParameter("btnadd"));
        System.out.println("idbook is: "+request.getParameter("idbook"));
        System.out.println("number is "+request.getParameter("number"));
        System.out.println("btn add to cart is "+request.getParameter("btnaddbook"));
        System.out.println("btnorder is: "+request.getParameter("btnorder"));

        String btnDelete = request.getParameter("btndelete");
        String btnAdd = request.getParameter("btnadd");
        String btnaddbook = request.getParameter("btnaddbook");
        String btnorder = request.getParameter("btnorder");;

        if (btnorder!=null){

            cartBean.validateCartOrder();

            request.setAttribute("order",cartBean.displayCart());

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/order.jsp");
            dispatcher.forward(request, response);

            cartBean.clearCart();
        }else{



        int bookId = Integer.parseInt(request.getParameter("idbook"));
        int booksNumber = Integer.parseInt(request.getParameter("number"));


        if( btnDelete==null && btnorder==null && btnAdd!=null && bookId!=0 && booksNumber!=0 || btnaddbook!=null){

            cartBean.addBookToCart(bookId,booksNumber);
            System.out.println("ajouter au panier");


        }

        if( btnAdd==null && btnorder==null && btnDelete!=null && bookId!=0 && booksNumber!=0){

            cartBean.removeBookFromCart(bookId,booksNumber);
            System.out.println("supprimer au panier");

        }

        if (btnaddbook!=null){

            request.setAttribute("books", bookBean.getAllBooks());

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/books.jsp");
            dispatcher.forward(request, response);

        }else {

            request.setAttribute("cart", cartBean.displayCart());

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
            dispatcher.forward(request, response);

        }
    }
    }
}
