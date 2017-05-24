package car.tp4.servlet;

import car.tp4.entity.BookBean;
import car.tp4.entity.CartBean;
import car.tp4.entity.InitBean;
import car.tp4.entity.JdbcBean;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.lang.Object;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
/**
 * Class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
     * serial Id session
     */
    private static final long serialVersionUID = -7448569586957481548L;


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

        System.out.println("le mail "+request.getSession().getAttribute("email"));
        System.out.println("la session "+request.getSession().getId());


        if (null != request.getSession(false)) {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/books");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String pwd = request.getParameter("password");


        JdbcBean check = new JdbcBean();
        try {
                if (check.loginCheck(email, pwd)) {


                    Cookie loginCookie = new Cookie("email", email);
                    loginCookie.setMaxAge(30 * 60);
                    response.addCookie(loginCookie);

                    //Cookie passCookie = new Cookie("password", pwd);
                    //passCookie.setMaxAge(30 * 60);
                    //response.addCookie(passCookie);


                    request.getSession().setAttribute("email", email);
                    request.getSession().setAttribute("user", email.split("\\@")[0].toUpperCase());
                    request.getSession().setAttribute("password", pwd);

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/books");
                    dispatcher.forward(request, response);

                } else {

                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }



    }
}
