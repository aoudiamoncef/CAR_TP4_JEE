package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;
import car.tp4.entity.InitBean;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Clas SortByYearDescServlet
 */
@WebServlet("/sortbyyeardesc")
public class SortByYearDescServlet extends HttpServlet {
    /**
     * BookBean
     */
    @EJB
    private BookBean bookBean;
    /**
     * initBean
     */
    @EJB
    InitBean initBookStore;

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

        System.out.println("State is "+initBookStore.getState());

        if (request.getSession().getAttribute("email") != null && request.getSession().getAttribute("password") != null) {

            request.setAttribute("sortByYearDesc", bookBean.getSortedBooksDesc());

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/sortByYearDesc.jsp");
            dispatcher.forward(request, response);

        } else {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }

}

