Mapow 
=====
Transformation framework with a touch of funk.

Usage
=====
The org.mapow.example.order.OrderMapper class contains a set of methods which demonstrate the following mapping scenarios:
* Java to Java
* Java to XML
* Java to JSON
* JSON to Java
* JSON to JSON
* JSON to XML
* XML to Java
* XML to JSON
* XML to XML

Running Examples
================
Running the examples via Maven is pretty easy.  From the root of the project:
```
mvn install
mvn -Prun -Dexec.args=javaToJava
```
You only need to run 'mvn install' once to build the project, it's not necessary to build for each example you run.  Change the exec.args parameter to target a specific test.
```
#############
## You must pass method name as argument. Available options:
##	javaToJava
##	javaToJSON
##	javaToXML
##	xmlToJava
##	jsonToJava
##	xmlToXml
##	jsonToJson
##	xmlToJson
##	jsonToXml
##
## An example from Maven:
##	 mvn -Prun -Dexec.args=javaToXML
#############
```
