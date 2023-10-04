# Sample API Testing Project

This is a sample project for testing a RESTful API using Java, JUnit, and RestAssured. The project includes test cases for various endpoints of the API, specifically focusing on the endpoints described below:

* GET /instruments: Get a list of all available trade instruments.
* GET /order-book/{instrument_code}: Get a given instrument's order book.
* GET /fees: Returns details of all general Fee Groups.
* GET /currencies: Get a list of available currencies.
* GET /candlesticks/{instrument_code}: Get instrument's candlesticks for a specified time period.

## Prerequisites
Before running the tests, make sure you have the following installed:
* Java Development Kit (JDK)
* Maven

## How to Run Tests
Clone this repository to your local machine.

git clone <repository_url>

Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).

Configure the project to use JUnit and RestAssured. Build the pom.xml.

`mvn test`

## Test Descriptions

InstrumentsApiTest: Tests the /instruments endpoint to get a list of available trade instruments.

OrderBookApiTest: Tests the /order-book/{instrument_code} endpoint to get a given instrument's order book. It includes tests for different scenarios:
Getting a level 3 order book.
Getting the default (level 3) order book.
Getting an order book with a specified depth limit.

FeesApiTest: Tests the /fees endpoint to return details of all general Fee Groups.

CurrenciesApiTest: Tests the /currencies endpoint to get a list of available currencies.

CandlesticksApiTest: Tests the /candlesticks/{instrument_code} endpoint to get instrument's candlesticks for a specified time period.
On candlesticks there is an error for future dates, code 200 is returned 