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

@WebServlet("/findjob")
public class FindJob extends HttpServlet{
	protected JobDao jobDao;
	protected CharacterJobDao characterJobDao;
	protected WeaponTypeJobDao weaponTypeJobDao;
	protected GearTypeJobDao gearTypeJobDao;
	
	@Override
	public void init() throws ServletException{
		jobDao = JobDao.getInstance();
		characterJobDao = CharacterJobDao.getInstance();
		weaponTypeJobDao = WeaponTypeJobDao.getInstance();
		gearTypeJobDao = GearTypeJobDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Job job = new Job(); 
        List<WeaponType> weaponTypeList = new ArrayList<WeaponType>(); 
        List<GearType> gearTypeList = new ArrayList<GearType>(); 
        
        
        Integer jobID;
        if(req.getParameter("jobID")==null) {
        	messages.put("success", "Please enter a valid name.");
        } else {
        	jobID = Integer.parseInt(req.getParameter("jobID"));
        	try {
        		job = jobDao.getJobById(jobID);
        		weaponTypeList = weaponTypeJobDao.getWeaponTypesByJobId(jobID);
        		gearTypeList = gearTypeJobDao.getGearTypesByJobId(jobID);
        	}catch (SQLException | NullPointerException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + job.getName());
        }
        
        req.setAttribute("job", job);
        req.setAttribute("weaponTypeList", weaponTypeList);
        req.setAttribute("gearTypeList", gearTypeList);
        
        req.getRequestDispatcher("/FindJob.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Job job = new Job(); 
        List<WeaponType> weaponTypeList = new ArrayList<WeaponType>(); 
        List<GearType> gearTypeList = new ArrayList<GearType>(); 
        
        Integer jobID;
        if(req.getParameter("jobID")==null) {
        	messages.put("success", "Please enter a valid name.");
        } else {
        	jobID = Integer.parseInt(req.getParameter("jobID"));
        	try {
        		job = jobDao.getJobById(jobID);
        		weaponTypeList = weaponTypeJobDao.getWeaponTypesByJobId(jobID);
        		
        		gearTypeList = gearTypeJobDao.getGearTypesByJobId(jobID);
        	}catch (SQLException | NullPointerException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	if(job!=null) {
        		messages.put("success", "Displaying results for " + job.getName());
        	}
        	else {
        		messages.put("success", "No such job");
        	}
        }
        
        req.setAttribute("job", job);
        req.setAttribute("weaponTypeList", weaponTypeList);
        req.setAttribute("gearTypeList", gearTypeList);
        
        req.getRequestDispatcher("/FindJob.jsp").forward(req, resp);
	}
}
