# MercedesAssignment
The Assignment consists of two MS - 
  1. for the from end / user end points - runs on port 8001
  2. for storing in file - runs on port 8002
Microservice1 is the user facing MS having three endpoints as below:
  1. http://localhost:8001/store - pass the JSON values in body and params (XML/CSV).
  2. http://localhost:8001/update -  to update values present in the XML/CSV file - takes request body only.
  3. http://localhost:8001/read - reads the data present in the file.

Microservice2 is used for storing the data provided in JSON format. It accepts the data in encoded format from MS1 decodes it and stores in the file specified.

The data transfer between MS1 and MS2 was not supposed to use HTTP, hence, RabbitMQ is used for the data transfer. It is required that user should have RabbitMQ installed.
