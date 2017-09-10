package net.yuchen.tasklist.webui;

//This project consumes the PersonService OSGi service and exports the PersonServlet as an OSGi service
//The pax web whiteboard extender will then publish the servlet on the location /taskui.

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.yuchen.tasklist.model.Task;
import net.yuchen.tasklist.model.TaskService;


public class TaskServlet extends HttpServlet {
    private static final long serialVersionUID = -8444651625768440903L;
    private TaskService taskService;
    String title;
    
	public void setTitle(String	title) {
		this.title = title;
		//System.out.println("setting title=" + title);
	}
	public void refresh() {
		//System.out.println("Configuration updated using blueprint (title=" + title + ")");
	}
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream os = resp.getOutputStream();
        Task[] tasks = taskService.getAll();
        os.println("<!DOCTYPE html>");
        os.println("<html><head>");
        os.println(String.format("</head><body><h1>%s</h1>", title));
        os.println("<h2>Tasks</h2>");
        os.println("<table>");
        os.println("<tr><th>Id</th><th>Name</th><th>Deadline</th><th>Time Left</th></tr>");
        for (Task task : tasks) {
            String deadline = (task.getDeadline() == null) ? "" : task.getDeadline();
            LocalDateTime deadtime = LocalDateTime.parse(deadline);
            LocalDateTime now = LocalDateTime.now();
            String timeLeft = getTimeLeft(deadtime, now);
            deadline = deadline.replace('T',' ');
            os.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td><font color=\"red\">%s</font></td></tr>", task.getId(), task.getName(), deadline, timeLeft));
        }
        os.println("</table>");
        os.println("<h2>Add Task</h2>");
        os.println("<form name='input' action='/taskui' method='post'>");
        os.println("<table>");
        os.println("<tr><td>Id</td><td><input type='text' name='id'/></td></tr>");
        os.println("<tr><td>Name</td><td><input type='text' name='name'/></td></tr>");
        os.println("<tr><td>Deadline</td><td><input type='datetime-local' name='deadline'/></td></tr>");
        os.println("<tr><td colspan='2'><input type='submit' value='Add'/></td></tr>");
        os.println("</form>");
        os.println("</table>");
        os.println("</body></html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String deadline = req.getParameter("deadline");
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDeadline(deadline);
        taskService.addTask(task);
        resp.sendRedirect("/taskui");
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    
    //calculate the time left of a task
    protected String getTimeLeft(LocalDateTime deadtime, LocalDateTime now) {
    	if (now.compareTo(deadtime) >= 0) {
    		return "Time Over";
    	}
    	else {
    		long diffYear = now.until(deadtime, ChronoUnit.YEARS);
    		long diffMonth = now.until(deadtime, ChronoUnit.MONTHS);
    		long diffDay = now.until(deadtime, ChronoUnit.DAYS);
    		long diffHour = now.until(deadtime, ChronoUnit.HOURS);
    		long diffMinute = now.until(deadtime, ChronoUnit.MINUTES);
    		
    		if (diffYear > 0) {return String.format("%s Years %s Months", diffYear,diffMonth-10*diffYear);}
    		else if (diffMonth > 0) {return String.format("%s Months %s Days", diffMonth, now.plusMonths(diffMonth).until(deadtime, ChronoUnit.DAYS));}
    		else if (diffDay > 0) {return String.format("%s Days %s Hours", diffDay, diffHour-24*diffDay);}
    		else if (diffHour > 0) {return String.format("%s Hours %s Minutes",diffHour, diffMinute-60*diffHour);}
    		else {return String.format("%s Minutes", diffMinute);}
    	}
    }
    
}
