# API FOR BANK TRANSACTIONS

## Reference Documentation

It is a Spring Boot Microservice with a H2 DB embebed.
###Built it with Maven typing this command in the console from the folder where is the project:
./mvnw clean package

###Run the generated Jar file typing this command in the console from the folder where is the project:
java -jar .\target\fakeapi-0.0.1-SNAPSHOT.jar


## API endpoints

API is now running on port 8080.

The API has three endpoints that only support POST calls. There are:

### Create transactions
http://localhost:8080/createTransaction for create new transactions

The input JSON is similar to this example:
{
"reference":"200",
"account_iban":"E123222222224",
"date":"2019-07-16T16:55:42.000Z",
"amount":1000,
"fee":3,
"description":"fake transaction"
}

### Search transactions
http://localhost:8080/search for obtain a list of existing transactions.

The input JSON body can be empty, but these examples are valid for ordering and filtering:

1º Filtering by account and order from minimum to maximum amount in transactions.
{
	"account_iban":"E1234567",
	"sortCriteria":"ASC"
}

2º Ordering all transactions from maximum to minimum amount.
{
	"sortCriteria":"DESC"
}

### Status of transaction
http://localhost:8080/status for obtain the status of a transaction.

This endpoint accept a JSON like this example:
{
"reference":"12345A", //Reference
"channel":"CLIENT" //Channel
}

### Preloaded data

In the database there are some data preloaded for testing when microservice starts. Data is in /resources/data.sql




