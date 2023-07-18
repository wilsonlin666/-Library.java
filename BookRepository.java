public class BookRepository {
    static Shelf[] shelves;

    public BookRepository(){
        shelves = new Shelf[10];
        for(int i = 0; i < 10; i++){
            shelves[i] = new Shelf();
        }
    }

    public void checkInBook(long checkedInISBN,int checkInUserID, Date checkInDate) throws InvalidISBNException {
        Book bookToCheckIn = findBookByISBN(checkedInISBN);
        if(bookToCheckIn.getISBNNumber() == checkedInISBN && bookToCheckIn.getCheckOutUserID() == checkInUserID && bookToCheckIn.getCheckedOutDate().equals(checkInDate)){
            findBookByISBN(checkedInISBN).setCheckedOut(false);
        }
    }

    public void checkOutBook(long checkedOutISBN, int checkOutUserID, Date checkOutDate) throws InvalidISBNException, InvalidUserIDException, BookAlreadyCheckedOutException {
        // Find the shelf that contain the book
            Book book = findBookByISBN(checkedOutISBN);
            if(book.getCheckOutUserID() != checkOutUserID){throw new InvalidUserIDException("Invalid User ID");}
            if(!book.getCheckedOutDate().equals(checkOutDate)){throw new BookAlreadyCheckedOutException("Book already checked out");}
            findBookByISBN(checkedOutISBN).setCheckedOut(true);
    }

    public Book findBookByISBN(long ISBN) throws InvalidISBNException{
        if(ISBN < 1){throw new InvalidISBNException("Invalid ISBN");}
        for (Shelf currentShelf : shelves) {
            Book currentBook = currentShelf.getHeadBook();
            while (currentBook != null) {
                if (currentBook.getISBNNumber() == ISBN) {
                    return currentBook;
                }
                currentBook = currentBook.getNextBook();
            }
        }
        throw new InvalidISBNException("Invalid ISBN");
    }

    public void addBook(long addISBN, String addName, String addAuthor, String addGenre,
    Book.Condition addCondition) throws BookAlreadyExistException, InvalidISBNException {
        if(addISBN < 1){throw new InvalidISBNException("ISBN is invalid" + addISBN);}
        //if(findBook(addISBN) != null){throw new BookAlreadyExistException("Book Already Exist");}

        Book book = new Book(addISBN,addName,addAuthor,addGenre,addCondition);
        char firstLetter = addName.charAt(0);
        int shelfIndex = (int) Character.toUpperCase(firstLetter) - (int) 'A';
        shelves[0].addBook(book);
    }

    private Book findBook(long isbn) throws InvalidISBNException {
        for(Shelf shelf: shelves){
            Book foundBook = shelf.findBook(isbn);
            if(foundBook != null){
                return foundBook;
            }
        }
        return null;
    }

    public void removeBook(long removeISBN) throws InvalidISBNException, BookDoesNotExistException {
        if (removeISBN < 1) {throw new InvalidISBNException("ISBN is invalid" + removeISBN);}
        //Remove book
        char firstLetter = findBookByISBN(removeISBN).getName().charAt(0);
        int shelfIndex = (int) Character.toUpperCase(firstLetter) - (int) 'A';
        shelves[shelfIndex].removeBook(findBookByISBN(removeISBN).getName());
    }

    public void sortShelf(int shelfInd, String sortCriteria) throws InvalidSortCriteriaException{
        // convert sortCriteria from string to enum
        Shelf.SortCriteria criteria = Shelf.SortCriteria.fromString(sortCriteria);
        if(criteria == null){throw new InvalidSortCriteriaException("Invalid criteria");}
        shelves[shelfInd].sort(criteria);
    }

    public static void printTable(int shelve){
        String shelf = String.valueOf(shelves[shelve].condition);
        String[] headers = {shelf,"Checked Out","Check Out Date", "Checkout UserID \n"};
        for(String header: headers){
            System.out.printf("%-15s",header);
        }
        System.out.println("--------------------------------------------------------------");
        //Print each book
        Book headBook = shelves[shelve].headBook;
        for(Book book = headBook; book!= null; book = book.getNextBook()){
            if(shelf.equalsIgnoreCase("ISBN")){
                System.out.printf("%-15d%-15b%-15s%-15d\n",
                        book.getISBNNumber(),
                        book.getCheckedOut(),
                        "NA",
                        book.getCheckOutUserID());
            }else if(shelf.equalsIgnoreCase("TITTLE")){
                System.out.printf("%-15s%-15b%-15s%-15d\n",
                        book.getName(),
                        book.getCheckedOut(),
                        "NA",
                        book.getCheckOutUserID());
            }else if(shelf.equalsIgnoreCase("AUTHOR")){
                System.out.printf("%-15s%-15b%-15s%-15d\n",
                        book.getAuthor(),
                        book.getCheckedOut(),
                        "NA",
                        book.getCheckOutUserID());
            }else if(shelf.equalsIgnoreCase("GENRE")){
                System.out.printf("%-15s%-15b%-15s%-15d\n",
                        book.getGenre(),
                        book.getCheckedOut(),
                        "NA",
                        book.getCheckOutUserID());
            }else{
                System.out.printf("%-15s%-15b%-15s%-15d\n",
                        book.getCondition().toString(),
                        book.getCheckedOut(),
                        "NA",
                        book.getCheckOutUserID());
            }
        }
    }
}

