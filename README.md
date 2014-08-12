Mapow 
=====
Transformation framework with a touch of funk.

Usage
=====
The [org.mapow.example.order.OrderMapper](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L40) class contains a set of methods which demonstrate the following mapping scenarios:
* [Java to Java](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L56)
* [Java to XML](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L89)
* [Java to JSON](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L72)
* [JSON to Java](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L72)
* [JSON to JSON](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L155)
* [JSON to XML](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L155)
* [XML to Java](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L155)
* [XML to JSON](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L155)
* [XML to XML](https://github.com/kcbabo/mapow/blob/master/src/test/java/org/mapow/example/order/OrderMapping.java#L155)

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
