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

@WebServlet("/books")
public class BookServlet extends HttpServlet {

  @EJB
  private BookBean bookBean;

  @EJB
  InitBean initBookStore;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    System.out.println("State is "+initBookStore.getState());

    if (request.getSession().getAttribute("email") != null && request.getSession().getAttribute("password") != null) {

      request.setAttribute("books", bookBean.getAllBooks());
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/books.jsp");
      dispatcher.forward(request, response);

    } else {

      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
      dispatcher.forward(request, response);
    }
  }
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    System.out.println("State is "+initBookStore.getState());

    request.setAttribute("books", bookBean.getAllBooks());
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/books.jsp");
    dispatcher.forward(request, response);
  }
}
