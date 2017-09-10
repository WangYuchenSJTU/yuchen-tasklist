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
//this class allows the service to be published directly from eclipse, not necessary for karaf
import java.io.IOException;

import net.yuchen.tasklist.model.TaskService;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * Start the service to test in the IDE
 *
 */
public class TaskServiceStarter {
    
    public void startService() {
        TaskServiceImpl taskServiceImpl = new TaskServiceImpl();
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress("http://localhost:8282/task");
        factory.setServiceClass(TaskService.class);
        factory.setServiceBean(taskServiceImpl);
        Server server = factory.create();
        server.start();
    }

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        new TaskServiceStarter().startService();
        System.in.read();
    }

}
