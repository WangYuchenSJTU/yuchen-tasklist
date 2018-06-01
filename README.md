Task List App
=============
A simple karaf web task list application that publish and use a simple SOAP service in karaf using cxf and blueprint.
It consists of five projects:

- model: Define Task class and TaskService interface
- server: Service implementation and logic to publish the service using SOAP
- proxy: Accesses the SOAP service and publishes it as an OSGi service
- webui: Provides a simple servlet based web ui to list and add tasks using the OSGi service
- features: install a feature descriptor for karaf to the maven repositor, including a main feature:yuchen-tasklist 
            and two sub-features: yuchen-tasklist-core and yuchen-tasklist-ui

Build
-----

> mvn clean install

Deploy the service in Karaf
------------------------

Download Apache Karaf 4 here: http://karaf.apache.org/index/community/download.html
```bash
feature:repo-add cxf 3.1.5
feature:repo-add mvn:net.yuchen.tasklist/tasklist-features/1.0-SNAPSHOT/xml
feature:install http http-whiteboard cxf-jaxws
install -s mvn:javax.annotation/javax.annotation-api/1.2
install -s mvn:org.apache.aries.blueprint/org.apache.aries.blueprint.authz/1.0.0
feature:install yuchen-tasklist
```
Test the service
----------------

The task service should show up in the list of currently installed services that can be found at
http://localhost:8181/system/console/services
and
http://localhost:8181/cxf/ 


Test the web UI
-------------------------

http://localhost:8181/taskui

You should see the list of tasks and be able to add new tasks.


Reference
---------
The backbone of this app comes from the karaf tutorial series (http://liquid-reality.de/display/liquid/Karaf+Tutorials)
 written by Christian Schneider (https://github.com/cschneider). The servlet tutorial on Runoob (http://www.runoob.com/servlet/servlet-tutorial.html) 
 and official karaf guide also helped a lot on realizing this project.
