package pm3.servlet;

import pm3.dao.*;
import pm3.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/findplayers")
public class FindPlayers extends HttpServlet{
	protected PlayerDao playerDao;
	
	@Override
	public void init() throws ServletException {
		playerDao = PlayerDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        

        Player player = new Player(0);
        System.out.println(req.getParameter("email"));
        String email = req.getParameter("email");
        if(email==null || email.trim().isEmpty()) {
        	messages.put("success", "Please enter a valid email.");
        }
        else {
        	try {
        		player = playerDao.getPlayerByEmail(email);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            } 
        	messages.put("success", "Displaying results for " + email);
        }
        req.setAttribute("player", player);
        req.getRequestDispatcher("/FindPlayers.jsp").forward(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        

        Player player = new Player(0);
        
        String email = req.getParameter("email");
        if(email==null || email.trim().isEmpty()) {
        	messages.put("success", "Please enter a valid email.");
        }
        else {
        	try {
        		player = playerDao.getPlayerByEmail(email);
        		//System.out.println(player.getEmailAddress());
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            } 
        	messages.put("success", "Displaying results for " + email);
        }
        req.setAttribute("player", player);
        req.getRequestDispatcher("/FindPlayers.jsp").forward(req, res);
	}
}