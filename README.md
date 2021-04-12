# About

This application is a simple calculator which could be used to do basic operators like addition, subtraction, multiplication, division. 


## Funtionality

It is capable of evaluating multi operand expressions as follows.
```
"4*5+6"
"25.0/5"
"4*5+6*7-33/9+18/3*44+6-33+8-59/7+14*7-45/7-2/5+13/1+16*6+7"
```

## Features

```
It follows bodmas rule. 
It supports decimal numbers.
Invalid expressions are be handled.
```

## Test cases
The unit test cases could be found in "WatchcartServiceTests" file and it covers happy path, corner cases and exceptions.

## Build command
Install maven and run "mvn clean install" which will build the project, run the test cases and generate a JAR file in the target folder. This project is mvn build tested.

## 2 REST endpoints:

```
GET "/"           - Prints the message "Welcome to calculator application"
POST "/calculate" - Accepts an input expression as string and computes and returns the result
```

## How to start it:
This application could be imported in Spring tool suite and run as "Spring boot application"

## How to test it:
Once the application is started, we could test the following things via Postman:
```
1) Hitting "localhost:8080" as GET method should return "Welcome to calculator application" as response
2) Hitting "localhost:8080/calculate" as POST method with "4*5+6*7-33/9+18/3*44+6-33+8-59/7+14*7-45/7-2/5+13/1+16*6+7" passed as text in body 
should return "502.07619047619045" as response
```
Alternatively, the test cases in "CalculatorApplicationTests" file in "com.ameen.calculator.services" package also could be run to test various positive and negative test cases
