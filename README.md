# I. Introduction

CMKit - A Learning Management System

[CMKit](codingmentorkit.com) is designed to facilitate online assignment submissions, grading and grade analysis, and secure storage of digital class materials.

- CMKit is written in **Pure Java** (with help from **Java Server Pages** and **Servlet**). 

# II. Getting Started:

### 1. Signing up for a CMKit account

When school begins, professors often create a spreadsheet of student emails (provided [here](https://docs.google.com/spreadsheets/d/18tLlYL6Ftcdbk3ESXvmq6mcj5V_AyjW4V-mDUHLGiyk/edit#gid=0))
![image](https://user-images.githubusercontent.com/75138396/221344040-07e4b542-3ac7-48aa-aaf3-205f74d4a720.png)

Professors can then use CMKit to add these students to a class:
![](https://i.imgur.com/gAw8ZAV.gif)

The invitation links will be sent to the student's emails so they can create an account. Similarly, you can add graders and professors to the system. 

- Students will receive assignments assigned by professors
- Proffesors can create, assign assignments then grade assignments
- Graders can grade assignments from students (Graders usually are Teaching Assistants)

### 2. Navigating the CMKit interface

![](https://i.imgur.com/YsTt7sa.jpg)

![](https://i.imgur.com/Q8lGsfj.jpg)

![](https://i.imgur.com/8mTLYMU.jpg)

![](https://i.imgur.com/QE2G9Nj.jpg)


### 3. Customizing your CMKit profile

# III. Accessing Courses on CMKit:

- Find a course:  
![image](https://user-images.githubusercontent.com/75138396/221346966-7ed1d63f-cb29-4d0c-a89f-74abc874ef28.png)

- Enrolling in a course:  
Students will be added to a course using Add Students feature mentioned in section I

- Accessing course materials on CMKit:  
Instructors can add materials to a course/ a class as following:  
![image](https://user-images.githubusercontent.com/75138396/221347258-79db8124-12fe-4639-a271-faf38eaa1b0e.png)

# IV. Interacting with Course Content on CMKit

- Viewing course content on CMKit:  

Materials added to a course will be visible to all enrolled students when they click on their class from home page:  
![image](https://user-images.githubusercontent.com/75138396/221347440-835e823d-82cb-4605-8f8c-23cfe6a94dba.png)

The shared materials look like this to a student:  
![image](https://user-images.githubusercontent.com/75138396/221347154-bd7ff856-672a-404a-9193-ea5651e7dc51.png)

- Professor assigning and Student completing assignments on CMKit:  
![](https://i.imgur.com/zvVtYPJ.gif)

- Participating in group assignments on CMKit:  

# V. Tracking Progress on CMKit

- Monitoring course progress on CMKit:  
![image](https://user-images.githubusercontent.com/75138396/221346807-30dc16cf-6f08-4caa-b8c5-af9a906a7ca2.png)

- Checking grades and feedback on CMKit:  

- Reviewing course history on CMKit:  

[Demo 2](https://drive.google.com/file/d/1HmIBZJtRYmAtaYE_QU23V37QcD_5UOAY/view) - Assign, Grade assignments, Positive and Negative Scoring, Collaboration on Programming Project, Grade Analysis

# Front End:

- Similar to **React.js**, Kit utilizes Java Server Page **Custom Tag** to break the user interface down into small, reusable components that allows developers to build scalable, maintainable front-end code.
- CMKit also uses **AJAX (Asynchronous JavaScript and XML)** which allows a web page to update dynamically without requiring a full page refresh. This means that **Ajax** can handle user input **without waiting for the database** because it sends an asynchronous request to the server and then continues to process other user actions while waiting for the response from the server.

# Back End:

- CMKit uses Singleton design patter to help improve efficiency by managing resources, optimizing memory usage, providing thread safety, enabling global access, and offering flexibility. 
- Model-View-Control Architecture: Servlet + JSP + JDBC + CRUD
![image](https://user-images.githubusercontent.com/75138396/221334562-8e5d23de-6d76-49e9-a14a-d492fa3bbfe5.png)
- Log4J (info.log, debug.log, warn.log, error.log) 
![image](https://user-images.githubusercontent.com/75138396/221334584-7a94e4f9-b5d7-4140-968a-e43fa7a7a35a.png)

- Git doesn't provide any built-in user management features, making it challenging for project administrators to control who can access and modify repositories. CMKit solves this problem by providing a centralized way to manage Git repositories and their associated access rights.

# Database

Kit stores data in a **MySQL server**. All tables are indexed (full-text + key index) to reduce query time, and data are written in Third normal form (3nf) to reduce the duplication of data, avoid data anomalies


