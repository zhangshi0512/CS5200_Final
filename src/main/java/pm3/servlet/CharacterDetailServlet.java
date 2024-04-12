package pm3.servlet;

import pm3.dao.CharacterDao;
import pm3.model.Character;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/characterDetail")
public class CharacterDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int characterId = Integer.parseInt(request.getParameter("id"));
        CharacterDao characterDao = CharacterDao.getInstance();
        try {
            Character character = characterDao.getCharacterById(characterId);
            request.setAttribute("character", character);
            request.getRequestDispatcher("/WEB-INF/character-detail.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }
    }
}
