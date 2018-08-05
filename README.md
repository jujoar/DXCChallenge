# DXCChallenge
This is my code for the DXCChallenge named People API.

Every HTTP method you will see is a GET method except for the last one (number seven), wich is a PUT method.

## Steps to run this project:

- Download the .war file located in wsjava/dist/ called "wsjava.war".
- Move the .war file to webapps directory located in your tomcat folder.
- Use a REST Client (or browser) and paste this url: http://localhost:8080/wsjava/jujoar/PeopleAPI/

The API I developed has 7 HTTP methods, one for each user story set for the challenge, to acces each one of this methods, use this format next to the URL above:

- User Story 1: "ID=value"
  - Where value can be anything (I used integers for ID). This will give a JSON with the mocked data that matches the ID.
  
- User Story 2: "allInfo"
  - This will give a JSON with all the mocked data I used.
  
- User Story 3: "last_name={value}"
  - Where value must be a String. This will give a JSON with the mocked data that matches the last name initials.
  
- User Story 4: "delSSN={value}"
  - Where value is a SSN. This will give a JSON with the mocked data that don't match the SSN. Doesn't delete the real data, just emulates the action of deleting it.

- User Story 5: "last_nameInfo"
  - This will give a JSON with the mocked data sorted by last name.
  
- User Story 6: "ssn={value}"
  - Where value is a SSN. This will give a JSON with the mocked data that matches the SSN, it also calculates the age of the person.
  
- User Story 7: "id={IDvalue}-last_name={LNvalue}"
  - Where IDvalue is the ID of the person's last name you want to update, while LNvalue is the person's new last name. This will give a JSON with the mocked data of this person in case there's an ID match.
  

  


  
    
  
