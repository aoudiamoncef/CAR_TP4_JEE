package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class DeleteAddBookServlet
 */
@WebServlet("/deleteaddbook")
public class DeleteAddBookServlet extends HttpServlet {
    /**
     * EJB BookBean
     */
    @EJB
    private BookBean bookBean;

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


            request.setAttribute("stock", bookBean.deleteBook(1,2));

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/stock.jsp");
            dispatcher.forward(request, response);

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

        String btnDelete = request.getParameter("btndelete");

        String btnAdd = request.getParameter("btnadd");

        int bookId = Integer.parseInt(request.getParameter("idbook"));
        int booksNumber = Integer.parseInt(request.getParameter("number"));


        if( btnDelete!=null && btnAdd==null && bookId!=0 && booksNumber!=0){

            bookBean.deleteBook(bookId,booksNumber );

        }

        if( btnAdd!=null && btnDelete==null && bookId!=0 && booksNumber!=0){

            bookBean.AddBookQuantity(bookId,booksNumber);

        }



        request.setAttribute("stock", bookBean.getAllBooks());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/stock.jsp");
        dispatcher.forward(request, response);
    }
}
