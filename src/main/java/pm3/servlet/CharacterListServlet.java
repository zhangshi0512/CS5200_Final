package pm3.servlet;

import pm3.dao.CharacterDao;
import pm3.model.Character;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/characterList")
public class CharacterListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the searchName parameter from the request.
        String firstName = request.getParameter("searchName");
        
        // Get an instance of the CharacterDao.
        CharacterDao characterDao = CharacterDao.getInstance();
        try {
            List<Character> characters;
            // Check if the searchName is not provided or is empty.
            if (firstName == null || firstName.trim().isEmpty()) {
                // If so, get all characters.
                characters = characterDao.getAllCharacters();
            } else {
                // If a searchName is provided, filter characters by that name.
                characters = characterDao.getCharactersByFirstName(firstName);
            }
            // Set the characters as a request attribute and forward to the JSP.
            request.setAttribute("characters", characters);
            request.getRequestDispatcher("/characterList.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when fetching characters", e);
        }
    }
}

