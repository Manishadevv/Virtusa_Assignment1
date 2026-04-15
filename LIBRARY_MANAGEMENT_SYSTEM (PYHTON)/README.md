**Library Management System**

The Library Management System is a Python-based CLI application that allows users to manage books, users, and borrowing operations efficiently.

It supports:

Adding books and users
Borrowing & returning books
Searching books
Viewing borrowed books

** Features**

> Add new books

>  Add new users

> Borrow books (with availability check)

> Return books

> Search books by title

> Display all books

> View user borrow history



**System Design (ER Diagram)**
+----------------+        +----------------+        +----------------------+
|     User       |        |     Book       |        |       Library        |
+----------------+        +----------------+        +----------------------+
| id (PK)        |        | id (PK)        |        | books (list)         |
| name           |        | title          |        | users (list)         |
| borrowlist     |<------>| author         |        +----------------------+
+----------------+        | CopiesCount    |
        |                 +----------------+
        |
        | borrows
        |
        v
   (Many-to-Many via borrowlist)


**Relationships**

A User can borrow multiple Books

A Book can be borrowed by multiple Users

Library manages both Users and Books


**Project Structure**

 library-management-system
 ┣  Library.py
 ┗  README.md

**Menu Options**

1. Add New Book
2. Add New User
3. List All Books
4. Borrow Book
5. Return Book
6. Display Borrowed Books
7. Search Book
8. Exit.


** Example Workflow**

Add a user
Add books
Borrow a book
Return a book
Check borrowed list


**Future Enhancements**

Authentication system
Admin/User roles
Book reservation system
REST API version

**Output**

<img width="1048" height="465" alt="output1" src="https://github.com/user-attachments/assets/beb7b5b4-57a4-49dd-8df7-79fac852b941" />


<img width="1048" height="465" alt="output1" src="https://github.com/user-attachments/assets/c1399b79-66f6-46b9-a9a4-7bc7bd3ac063" />



Assignment by:
Manisha Devi S
Group-1
Rajalakshmi Insitute of Technology
