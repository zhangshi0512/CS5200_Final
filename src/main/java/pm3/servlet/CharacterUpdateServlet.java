package pm3.servlet;

import pm3.dao.CharacterDao;
import pm3.model.Character;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateCharacter")
public class CharacterUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int characterId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        CharacterDao characterDao = CharacterDao.getInstance();
        try {
            Character character = characterDao.getCharacterById(characterId);
            character.setFirstName(firstName);
            character.setLastName(lastName);
            characterDao.updateCharacter(character);
            response.sendRedirect("characterDetail?id=" + characterId);
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }
    }
}
