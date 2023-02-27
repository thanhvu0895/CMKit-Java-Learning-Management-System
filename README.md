# V- Participating in group assignments on CMKit



Professor assigning collaborative assignment which allows 2 contributors:
![assign-collaborative](https://user-images.githubusercontent.com/75138396/221622838-f3c76c02-4e0b-4f1b-b39f-ee10a9bb8d6e.gif)

For student to collaborate on the assignments, they must first set up their environment. For help with setting up on Windows, use the provided scripts: [Setup Scripts.zip](https://github.com/thanhvu0895/CMKit-Java-Learning-Management-System/files/10841670/Setup.Scripts.zip)

If Git for Windows is installed, please install latest version from [Git For Windows](https://git-scm.com/download/win).  


Student 1 and 2 works on the project by adding commits:





# Table of Contents:

- **[I.Introduction](#i-introduction)**  
- **[II. Getting Started](#ii-getting-started)**  
- **[III. Accessing Courses on CMKit](#iii-accessing-courses-on-cmkit)**  
- **[IV. Interacting with Course Content on CMKit](#iv-interacting-with-course-content-on-cmkit)**   
- **[V. Participating in group assignments on CMKit](#v--participating-in-group-assignments-on-cmkit)**  
- **[VI. Tracking Progress on CMKit](#vi-tracking-progress-on-cmkit)**  
- **[VII. Conclusion](#vii-conclusion)**  
- **[VIII. Kit Presentation](#viii-kit-presentation)**  



# I. Introduction

CMKit is a Learning Management System that simplifies the online submission of assignments, grading, and grade analysis, and provides secure storage of digital class materials. It is written in Pure Java, along with Java Server Pages and Servlet.

### Front End:

- CMKit uses JSP Custom Tag feature, similar to React.js, to break the user interface down into small, reusable components, enabling developers to build scalable, maintainable front-end code. 
- CMKit also uses AJAX (Asynchronous JavaScript and XML), which allows a web page to update dynamically without requiring a full page refresh. This means that Ajax can handle user input without waiting for the database.  

### Back End:

- CMKit uses Singleton design pattern to help improve efficiency by managing resources, optimizing memory usage, providing thread safety, enabling global access, and offering flexibility. 
- Model-View-Control Architecture: Servlet + JSP + JDBC + CRUD. Log4J (info.log, debug.log, warn.log, error.log). 
![image](https://user-images.githubusercontent.com/75138396/221334562-8e5d23de-6d76-49e9-a14a-d492fa3bbfe5.png)
- CMKit runs Gitosis to provide a centralized Server to manage Git repositories and their associated access rights.
![image](https://user-images.githubusercontent.com/75138396/221334584-7a94e4f9-b5d7-4140-968a-e43fa7a7a35a.png)


### Database

CMKit stores data in a MySQL server. All tables are indexed (full-text + key index) to reduce query time, and data are written in Third normal form (3nf) to reduce the duplication of data and avoid data anomalies.

# II. Getting Started:

### 1. Signing up for a CMKit account

Professors often have a spreadsheet of student emails, which you can find [here](https://docs.google.com/spreadsheets/d/18tLlYL6Ftcdbk3ESXvmq6mcj5V_AyjW4V-mDUHLGiyk/edit#gid=0). They can use CMKit to add students from the list to a class. Invitation links are sent to the student's emails so they can create an account. Similarly, graders and professors can be added to the system.
![add-students-cropped](https://user-images.githubusercontent.com/75138396/221392328-bc2f81d1-e07e-46e8-8be9-3f3e8ba350f8.gif)

### 2. Navigating the CMKit interface

Students can view the courses they are enrolled in and access course materials, view assignments and their grades, and participate in group assignments.
![student home](https://i.imgur.com/YsTt7sa.jpg)
![klass view](https://i.imgur.com/Q8lGsfj.jpg)
![assignment view](https://user-images.githubusercontent.com/75138396/221348916-6495c393-08df-420f-8140-806f0d9b99b2.png)

# III. Accessing Courses on CMKit:

Students can find and enroll in a course by accepting the invitation link sent to their account. Instructors can add materials to a course/class, which will be visible to all enrolled students when they click on their class from the home page.

**- Find a course:**  
![image](https://user-images.githubusercontent.com/75138396/221346966-7ed1d63f-cb29-4d0c-a89f-74abc874ef28.png)

**- Enrolling in a course:**  
Students will be enrolled to a course after they accept the invitation link sent to their account:
![image](https://user-images.githubusercontent.com/75138396/221348527-94cedb18-2320-4e43-a919-e0dc08e37510.png)

After clinking on the invitation link, students will then enter their account information on Kit:  
![image](https://user-images.githubusercontent.com/75138396/221348557-2e9be32d-572f-4b5a-a7ce-11b7942e77ce.png)

**- Accessing course materials on CMKit:**
Instructors can add materials to a course/ a class as following:  
![image](https://user-images.githubusercontent.com/75138396/221347258-79db8124-12fe-4639-a271-faf38eaa1b0e.png)

# IV. Interacting with Course Content on CMKit
Students can view materials and assignments added to a course/class and can complete assignments, which are then graded by the professor or the grader.


**- Viewing course content on CMKit:**   
![image](https://user-images.githubusercontent.com/75138396/221347440-835e823d-82cb-4605-8f8c-23cfe6a94dba.png)

The shared materials look like this to a student:  
![image](https://user-images.githubusercontent.com/75138396/221347154-bd7ff856-672a-404a-9193-ea5651e7dc51.png)


**- Assigning and Completing assignments:**
![](https://i.imgur.com/zvVtYPJ.gif)

# VI. Tracking Progress on CMKit

**- Monitoring course progress on CMKit:**
![image](https://user-images.githubusercontent.com/75138396/221346807-30dc16cf-6f08-4caa-b8c5-af9a906a7ca2.png)
![](https://i.imgur.com/8mTLYMU.jpg)
![](https://i.imgur.com/QE2G9Nj.jpg)


**- Checking grades and feedback on CMKit:**  
Students can view feedback for a certain assignment by clicking on that assignment while viewing the class:
![Feedback-cropped](https://user-images.githubusercontent.com/75138396/221392232-3f88e719-24e1-4adf-aa4b-58cea890fe32.gif)

**- Reviewing course history on CMKit:**
Students can access material and assignment submissions from previous courses by navigating to Past Classes Section from the home page:
![image](https://user-images.githubusercontent.com/75138396/221349089-ec661f57-af97-4bf8-96a7-9c42b0e6c708.png)

# VII. Conclusion

Summary of key takeaways for using CMKit:

- CMKit provides a platform for students to collaborate on code projects in a Git controlled environment, with version control and easy access to feedback and support.

- The Git environment provides an opportunity for students to develop skills in code management and collaborative coding practices, which are highly valued in industry settings.

Final thoughts and recommendations for using CMKit
By providing a structured and collaborative environment for students to learn and practice coding skills, instructors can help to prepare students for success in future careers in the field.

# VIII Kit Presentation 
[![Presentation link](https://i.imgur.com/tnuMkYt.png)](https://drive.google.com/file/d/1HmIBZJtRYmAtaYE_QU23V37QcD_5UOAY/view)
