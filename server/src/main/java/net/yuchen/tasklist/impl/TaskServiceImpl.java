/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.yuchen.tasklist.impl;
//implementation of taskservice interface defined in tasklist-model
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.PathParam;

import net.yuchen.tasklist.model.Task;
import net.yuchen.tasklist.model.TaskService;

public class TaskServiceImpl implements TaskService {
    Map<String, Task> taskMap;
    
    public TaskServiceImpl() {
        taskMap = new HashMap<String, Task>();
        Task task = createExampleTask();
        taskMap.put("1", task);
    }

    private Task createExampleTask() {
        Task task = new Task();
        task.setId("1");
        task.setName("Learn OSGi");
        task.setDeadline("2017-09-10T00:00");
        return task;
    }
    
    @RolesAllowed("admin")
    public Task[] getAll() {
        return taskMap.values().toArray(new Task[]{});
    }
    
    public Task getTask(@PathParam("id") String id) {
        return taskMap.get(id);
    }

    public void updateTask(@PathParam("id") String id, Task task) {
        task.setId(id);
        System.out.println("Update request received for " + task.getId() + " name:" + task.getName());
        taskMap.put(id, task);
    }
    
    public void addTask(Task task) {
        System.out.println("Add request received for " + task.getId() + " name:" + task.getName());
        taskMap.put(task.getId(), task);
    }
    
}
