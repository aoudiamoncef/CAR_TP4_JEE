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
 * Class FinderServlet
 */
@WebServlet("/finder")
public class FinderServlet extends HttpServlet {

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

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/finder.jsp");
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

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String addedSearch = request.getParameter("finder");


        if(addedSearch!=""){

            request.setAttribute("finder", bookBean.findTitle(addedSearch));

        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/finder.jsp");
        dispatcher.forward(request, response);
    }
}
