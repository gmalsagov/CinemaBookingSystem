Cinema Booking System

This project aimed to design and implement a cinema booking management system as a JavaFX GUI application with the following minimum requirements:

1. The system handles two different profiles; the cinema employee and ordinary customers. Both types of users need to login before being able to use the management system
2. Application should display a different view depending on the logged-in user

Cinema employee profile
3. The cinema employee should be able to login to the cinema management system using a username/password pair.
4. The cinema employee can add films with their respective dates and screening times. A film is represented by a title, a small image, and a brief description.
5. For a given film/date/time, application should provide an up-to-date graphical representation of the cinema room setting. It should graphically distinguish between booked seats and available ones. It should also show labels (e.g G13) and approximate position of the seats within the cinema.
6. The employee view also shows basic information about the total number of seats, booked ones and available ones for a given film/date/time.
7. The employee can export a list of films (i.e. titles), dates, times and number of booked and available seats. The list is a comma separated values text file.
8. The cinema employee can logout from the application.

Cinema customer profile
9. After login, a customer should be able to update his/her profile to use for future bookings. The profile is composed of basic information such as surname, first name, email address. There is also booking history that is updated automatically when the user makes a booking or delete an existing one.
10. He/she can pick up a date, get a list of films available on the selected date with their respective information and available screening times.
11. The customer can book a seat (for a given film/date/time) by clicking on the graphical representation of the seat. If the seat is available, the seat icon changes to ‘booked’. If the seat is not available, an error message is displayed.
12. When the customer clicks on the ‘Confirm booking’ button, a summary of his booking is displayed and the booking added to his history.
13. A customer can delete an existing booking from his list if it is a future booking only, otherwise an error message is displayed.
14. The cinema customer can logout from the application.
