package pm3.servlet;

import pm3.dao.CharacterDao;
import pm3.model.Character;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/characterList")
public class CharacterListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");

        CharacterDao characterDao = CharacterDao.getInstance();
        try {
            List<Character> characters = characterDao.getCharactersByFirstName(firstName);
            request.setAttribute("characters", characters);
            request.getRequestDispatcher("/WEB-INF/character-list.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when fetching characters by first name", e);
        }
    }
}
