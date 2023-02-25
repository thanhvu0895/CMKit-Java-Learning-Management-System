# CMKit - Learning Management System

[CMKit](codingmentorkit.com) is designed to facilitate online assignment submissions, grading and grade analysis, and secure storage of digital class materials.
![image](https://user-images.githubusercontent.com/75138396/221333862-2a0e123a-00d4-4fd1-9828-7daa1d6a4c6e.png)

- CMKit is written in **Pure Java** (with help from **Java Server Pages** and **Servlet**). 

# Features:

Demo 1 User Managements and CRUD Features:

![](https://imgur.com/yTYdtp4)

Demo 2 - Assign, Grade assignments, Positive and Negative Scoring, Collaboration on Programming Project, Grade Analysis:
bit.ly/cmkitdemo


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


