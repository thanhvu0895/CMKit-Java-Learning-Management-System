# CMKit - A Learning Management System

[CMKit](codingmentorkit.com) is designed to facilitate online assignment submissions, grading and grade analysis, and secure storage of digital class materials.

- CMKit is written in **Pure Java** (with help from **Java Server Pages** and **Servlet**). 

# Features:

1. Signing up for a CMKit account

When school begins, professors often create a spreadsheet of student emails (provided [here](https://docs.google.com/spreadsheets/d/18tLlYL6Ftcdbk3ESXvmq6mcj5V_AyjW4V-mDUHLGiyk/edit#gid=0))
![image](https://user-images.githubusercontent.com/75138396/221344040-07e4b542-3ac7-48aa-aaf3-205f74d4a720.png)

Professor can then use CMKit to add these students to a class:
![](https://i.imgur.com/gAw8ZAV.gif)

The invitation links will be sent to the student's emails so they can create an account. Similarly, you can add graders and professors to the system. 

- Students will receive assignments assigned by professors
- Proffesors can create, assign assignments then grade assignments
- Graders can grade assignments from students (Graders usually are Teaching Assistants)

[Demo 2](bit.ly/cmkitdemo) - Assign, Grade assignments, Positive and Negative Scoring, Collaboration on Programming Project, Grade Analysis:

# Front End:

- Similar to **React.js**, Kit utilizes Java Server Page **Custom Tag** to break the user interface down into small, reusable components that allows developers to build scalable, maintainable front-end code.
- CMKit also uses **AJAX (Asynchronous JavaScript and XML)** which allows a web page to update dynamically without requiring a full page refresh. This means that **Ajax** can handle user input **without waiting for the database** because it sends an asynchronous request to the server and then continues to process other user actions while waiting for the response from the server.

# Back End:

- CMKit uses Singleton design patter to help improve efficiency by managing resources, optimizing memory usage, providing thread safety, enabling global access, and offering flexibility. 
- Model-View-Control Architecture: Servlet + JSP + JDBC + CRUD
![image](https://user-images.githubusercontent.com/75138396/221334562-8e5d23de-6d76-49e9-a14a-d492fa3bbfe5.png)
- Log4J (info.log, debug.log, warn.log, error.log) 
![image](https://user-images.githubusercontent.com/75138396/221334584-7a94e4f9-b5d7-4140-968a-e43fa7a7a35a.png)


# Database

Kit stores data in a **MySQL server**. All tables are indexed (full-text + key index) to reduce query time, and data are written in Third normal form (3nf) to reduce the duplication of data, avoid data anomalies


