# QuoteHub

## Introduction

QuoteHub is a Spring Boot application that offers a REST API for managing quotes. Users with the reader role can
view quotes, while only users with the admin role have the privilege to create new quotes. The application primarily
utilizes Spring Boot and a PostgreSQL database.

## Building and Running the Project with Docker

1. **Move to base directory and give execute permission to scripts:**
   ```sh
   chmod +x build_and_run.sh
   ```

   ```sh
   chmod +x stop_and_clean.sh
   ```

2. **For demo step 2.1 and 2.2 can be skipped as .env file is checked and defaults used, however is mandatory for other
   environments**

   2.1 **Update .env file in base directory and choose appropriate user and password**

   2.2 **Generate Base64 encoded string for user authentication in quotes.http**
   ```sh
   #UserBase64EncoderString
   echo -n "User:Password" | base64
   ```

   ```sh
   #AdminBase64EncoderString
   echo -n "Admin:Password" | base64 
   ```

3. **Build and run app**
   ```sh
   ./build_and_run.sh
   ```

4. **API Endpoints**

   Please refer to the quotes.http file in the project for sample requests and responses.

5. **Access database**

   To access the database, use the URL jdbc:postgresql://0.0.0.0:5432/quotesdb.

6. **Stop and clean app**
   ```sh
   ./stop_and_clean.sh
   ```