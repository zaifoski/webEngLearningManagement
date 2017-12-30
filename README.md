# webEngLearningManagement
Create a Maven project, and use Spring Boot with H2 database engine for the solution
Solve the tasks below! Create a Maven project, and use Spring Boot with H2 database engine for the solution. Please, prefill the database with some data which can help demonstrate the following tasks!

Each task is worth 10 points. 0-35: 1, 36-41: 2, 42-47: 3, 48-53: 4, 54-60: 5.

Upload the compressed project folder in zip format (without the target directory, just the necessary source codes). Put your name, email address and further informations (if it is necessary, e.g. special installation steps) in a file called README.txt in the project root directory.

Learning management system 4.

In a learning management system there are given users, tasks and submissions. A task may have many submissions, a submission can belong to one task. A user may have many submissions, but a submission belongs to one user.

Main page On the main page list all the available tasks (task name).
Task detail Clicking on a task, a detailed information can be read about the task on a different page: task name, task description. There is a “Solve” button on this page. Only this page and the main page can be accessed unauthenticated.
Login A user can log in with its username and password. (The users are already registered in the database. Please provide some username-password pairs on the page, to accomplish the login.)
Solving a task The authenticated user can solve a task on a different page. Show the task name, task description, and the solution can be given in a multi-line text-box. After submission the user arrives on the success page.
Success page On this page the authenticated user can see who other users have already solved this task listing their submitted solutions too.
Search tasks On the main page put a text-box above the tasks. With the help of that search box one can filter the tasks giving the part of the task name.
