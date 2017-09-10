package net.yuchen.tasklist.model;

//define the task service interface as JAX-WS service
import javax.jws.WebService;

//by annotating the class with @WebService, the Web Services is created with JAX-WS
@WebService
public interface TaskService {
    public Task[] getAll();
    public Task getTask(String id);
    public void updateTask(String id, Task task);
    public void addTask(Task task);
}
