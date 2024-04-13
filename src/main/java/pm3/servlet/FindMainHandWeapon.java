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
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findmainhandweapon")
public class FindMainHandWeapon extends HttpServlet{
	protected WeaponDao weaponDao;
	protected ItemDao itemDao;
	protected WeaponTypeDao weaponTypeDao;
	protected WeaponTypeJobDao weaponTypeJobDao;
	
	@Override
	public void init() throws ServletException {
		weaponDao = WeaponDao.getInstance();
		itemDao = ItemDao.getInstance();
		weaponTypeDao = WeaponTypeDao.getInstance();
		weaponTypeJobDao = WeaponTypeJobDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Job> jobList = new ArrayList<Job>();
        Weapon weapon = new Weapon(); 
        		
        Item item = new Item();
        Integer weaponID = Integer.valueOf(req.getParameter("weaponID"));
        if(weaponID==null) {
        	messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		
        		weapon = weaponDao.getWeaponByID(weaponID);
        		item = itemDao.getItemByID(weaponID);
        		if(weapon!=null) {
        			jobList = weaponTypeJobDao.getJobByWeaponType(weapon.getWeaponType());
        		}
        	}catch (SQLException | NullPointerException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	if(weapon!=null) {
        		messages.put("success", "Displaying results for " + weapon.getName());
        	}
        	else {
        		messages.put("success", "No such weapon");
        	}
        }
        
        req.setAttribute("item", item);
        req.setAttribute("weapon", weapon);
        req.setAttribute("jobList", jobList);
        
        req.getRequestDispatcher("/FindMainHandWeapon.jsp").forward(req, resp);
	}
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Job> jobList = new ArrayList<Job>();
        Weapon weapon = new Weapon(); 
        		
        Item item = new Item();
        Integer weaponID = Integer.valueOf(req.getParameter("weaponID"));
        if(weaponID==null) {
        	messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		
        		weapon = weaponDao.getWeaponByID(weaponID);
        		item = itemDao.getItemByID(weaponID);
        		if(weapon!=null) {
        			jobList = weaponTypeJobDao.getJobByWeaponType(weapon.getWeaponType());
        		}
        	}catch (SQLException | NullPointerException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	if(weapon!=null) {
        		messages.put("success", "Displaying results for " + weapon.getName());
        	}
        	else {
        		messages.put("success", "No such weapon");
        	}
        }
        
        req.setAttribute("item", item);
        req.setAttribute("weapon", weapon);
        req.setAttribute("jobList", jobList);
        
        req.getRequestDispatcher("/FindMainHandWeapon.jsp").forward(req, resp);
	}
}