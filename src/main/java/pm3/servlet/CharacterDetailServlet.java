package pm3.servlet;

import pm3.dao.CharacterDao;
import pm3.model.Character;
import java.io.IOException;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("/characterDetail")
public class CharacterDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int characterId = Integer.parseInt(idStr);
            CharacterDao characterDao = CharacterDao.getInstance();
            try {
                Character character = characterDao.getCharacterById(characterId);
                request.setAttribute("character", character);
                request.getRequestDispatcher("/characterDetail.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Database error", e);
            }
        } else {
            response.sendRedirect("characterList.jsp");
        }
    }
}
