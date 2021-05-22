# Tournament management

This application is available on heroku: https://tournamentmng.herokuapp.com
</br>To login - type /login | Admin account- login: admin password: admin

## Contents
* [About](#About)
* [Technologies](#technologies)
* [Functions](#Functions)


## About
In college, there was a local computer game competition between several teams that many students participated in. Everything was done via email, so I thought it would be 
interesting to create an application that would allow organizing such tournaments. The administrator would additionally have more permissions to this application, which he could 
manage. He also doesn't have to constantly send many emails to everyone. He can add informations directly from the application which are visible to everyone. This application 
allowed me to test and consolidate my programming skills.
 
## Technologies
* Java
* Spring Boot
* Spring Security
* Hibernate
* H2
* Thymeleaf
* Bootstrap

#### Used tools:
* Maven


## Functions
- <b>Everyone</b> has access to browse the application.
- User can submit a new team.
- Validation is provided by server, not the html view.
- Sorting by 'Name', 'Rank', 'Wins', 'Score'.
- Added pagination.
- <b>Administrator</b> can login to the panel by adding /login to the address (No need to be visible for everyone)
- He is able to edit and delete a team.
- He can add wins, loses or draws to each team. It will automaticly increase or decrease the score.
- Administrator can add, edit and delete informations which are visible for everyone.
