package pm3.servlet;

import pm3.dao.CharacterDao;
import pm3.dao.JobDao;
import pm3.model.Character;
import pm3.model.Job;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Retrieve the searchName and filterJob parameters from the request.
	    String firstName = request.getParameter("searchName");
	    String filterJobId = request.getParameter("filterJob");

	    // Get an instance of the CharacterDao and JobDao.
	    CharacterDao characterDao = CharacterDao.getInstance();
	    JobDao jobDao = JobDao.getInstance();

	    try {
	        List<Character> characters;
	        List<Job> allJobs = jobDao.getAllJobs();

	        // Check conditions for filtering.
	        if ((firstName == null || firstName.trim().isEmpty()) && (filterJobId == null || filterJobId.trim().isEmpty())) {
	            // No filters provided, get all characters.
	            characters = characterDao.getAllCharacters();
	        } else {
	            // Filter based on the provided criteria.
	            characters = characterDao.getCharactersByFilters(firstName, filterJobId);
	        }

	        // Fetch all jobs to populate the dropdown.
	        request.setAttribute("allJobs", allJobs);
	        request.setAttribute("characters", characters);
	        request.getRequestDispatcher("/characterList.jsp").forward(request, response);
	    } catch (SQLException e) {
	        throw new ServletException("SQL error when fetching characters or jobs", e);
	    }
	}


}
