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
package net.yuchen.tasklist.model;
//the ObjectFactory is responsible for creating Task objects, it tells JAXB what xml element to use for the Task class
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
//@XmlRegistry is used to mark a class that has @XmlElementDecl annotations.
@XmlRegistry
public class ObjectFactory {
    private final static QName _TASK_QNAME = new QName("http://task.jms2rest.camel.net", "task");
    
    //since the value of the property is going to be a JAXBElement, @XmlElementDecl is needed
    @XmlElementDecl(namespace = "http://task.jms2rest.camel.karaf.tutorial.lr.net", name = "task")
    public JAXBElement<Task> createTask(Task task) {
        return new JAXBElement<Task>(_TASK_QNAME, Task.class, task);
    }
}
