class User:
    def __init__(self, id, name):
        self.id=id
        self.name=name
        self.borrowlist=[]

    def  addNewUser(self,id,name):
        new_user=User(id,name)
        return new_user
    
    
    def bookborrow(self,Book):
        if Book.check_availability():
            self.borrowlist.append(Book)
            Book.update_copies(-1)
            print(f"{self.name} has borrowed {Book.title}.")
        else:
            print("Book is not available for borrowing.")

    def bookreturn(self,Book):
        for b in self.borrowlist:
            if b.id == Book.id:
                self.borrowlist.remove(Book)
                Book.update_copies(1)
                print(f"{self.name} has returned {Book.title}.")
                return
        print(f"{Book.title} not borrowed .")

    def display_borrowlist(self):
        if self.borrowlist:
            print(f"{self.name} has borrowed the following books:")
            for Book in self.borrowlist:
                print(f"* {Book.title}")
        else:
            print(f"no books available")
    
class Book:
    def __init__(self, id, title, author, CopiesCount):
        self.id = id
        self.title =title
        self.author=author
        self.CopiesCount=CopiesCount

    def display_book(self):
        print(f"Id: {self.id}, Author: {self.author}, Title: {self.title}, Copies Count: {self.CopiesCount}")

    def check_availability(self):
        if self.CopiesCount > 0:
            return True
        else:
            return False
    
    def update_copies(self, count):
        self.CopiesCount += count
class Library:

    def __init__ (self):
        self.books =[]
        self.users =[]

    def find_user(self, user_id):
        for user in self.users:
            if user.id == user_id:
                return user
        return None
    
    def addbook(self,Book):
        self.books.append(Book)
    
    def adduser(self,user):
        self.users.append(user)
    def searchbook(self,title):
        for Book in self.books:
            if Book.title == title:
                return Book
        return None
    
    def listAllBooks(self):
        if self.books:
            for books in self.books:
                books.display_book()
        else:
            print("No books available")
    
    def add_newbook(self):
        id = input("Enter book id: ")
        title = input("Enter book title: ")
        author = input("Enter book author: ")
        CopiesCount = int(input("Enter number of copies: "))
        new_book = Book(id, title, author, CopiesCount)
        self.addbook(new_book)
        print(f"Book '{title}' added successfully.")

def main():
    lib = Library()

    book1 = Book("1", "The Great Gatsby", "F. Scott Fitzgerald", 3)
    book2 = Book("2", "4", "Harper Lee", 2)
    book3 = Book("3", "1984", "George Orwell", 4)
    book4 = Book("4", "Pride and Prejudice", "Jane Austen", 1)

    lib.addbook(book1)  
    lib.addbook(book2)
    lib.addbook(book3)
    lib.addbook(book4)
    while True:
        print("\nLibrary Management System")
        print("1. Add New Book")
        print("2. Add New User")
        print("3. List All Books")
        print("4. Borrow Book")
        print("5. Return Book")
        print("6. Display Borrowed Books")
        print("7. Search Book")
        print("8. Exit")
        choice = input("Enter your choice: ")
        
        if choice == '1':
            lib.add_newbook()
        elif choice == '2':
            lib.adduser(User(input("Enter user id: "), input("Enter user name: ")))
        elif choice == '3':
            lib.listAllBooks()
        elif choice == '4':
            userid = input("Enter user id:")
            user = lib.find_user(userid)
          
            title = input("Enter book title: ")
           
            book = lib.searchbook(title)
            if user and book:
                user.bookborrow(book)
            else:
                print("User or book not found.")
        elif choice == '5':
            userid = input("Enter user id:")
            user = lib.find_user(userid)
            title = input("Enter book title: ")
            book = lib.searchbook(title)
            if user and book:
                user.bookreturn(book)
            else:
                print("User or book not found.")
                    
        elif choice == '6':
            userid = input("Enter user id:")
            user =None
            for u in lib.users:
                if u.id == userid:
                    user = u
                    break
            if user:
                user.display_borrowlist()
            else:
                print("User not found.")
        elif choice == '7':
            title = input("Enter book title: ")
            book = lib.searchbook(title)
            if book:
                book.display_book()
            else:
                print("Book not found.")
        elif choice == '8':
            print("Application End")
            break
if __name__ == "__main__":
    main()