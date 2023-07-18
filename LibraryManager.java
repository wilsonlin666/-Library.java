import java.util.Scanner;
public class LibraryManager {
    private static final ReturnStack stack = new ReturnStack();
    private static final BookRepository bookRepository = new BookRepository();

    public static void main(String... args) throws InvalidISBNException, InvalidUserIDException, InvalidSortCriteriaException, BookCheckedOutBySomeoneElseException, InvalidReturnDateException, BookNotCheckedOutAException {
        System.out.println("Starting...");
        Scanner scanner = new Scanner(System.in);
        char choice1 = '-';
        while (choice1 != 'Q' && choice1 != 'q') {
            System.out.println("Menu:");
            System.out.println("(B) - Manage Book Repository");
            System.out.println("    (C) - Checkout Book");
            System.out.println("    (N) - Add New Book");
            System.out.println("    (R) - Remove Book");
            System.out.println("    (P) - Print Repository");
            System.out.println("    (S) - Sort Shelf:");
            System.out.println("        (I) - ISBN Number");
            System.out.println("        (N) - Name");
            System.out.println("        (A) - Author");
            System.out.println("        (G) - Genre");
            System.out.println("        (Y) - Year");
            System.out.println("        (C) - Condition");
            System.out.println("(R) - Manage Return Stack");
            System.out.println("    (R) - Return Book");
            System.out.println("    (S) - See Last Return");
            System.out.println("    (C) - Check In Last Return");
            System.out.println("    (P) - Print Return Stack");
            System.out.println("(Q) - Quit");
            System.out.print("Please select what to manage: ");
            choice1 = scanner.nextLine().charAt(0);
            switch (choice1) {
                case 'b':
                case 'B':
                    System.out.print("Please select an option:");
                    String choice2 = scanner.nextLine().toUpperCase();
                    switch (choice2) {
                        case "N":
                            Scanner sc = new Scanner(System.in);
                            System.out.print("Please provide an ISBN number:");
                            long choice0 = scanner.nextLong();
                            System.out.print("Please provide a name:");
                            String choice22 = sc.nextLine();
                            System.out.print("Please provide an author:");
                            String choice3 = sc.nextLine();
                            System.out.print("Please provide a genre:");
                            String choice4 = sc.nextLine();
                            System.out.print("Please provide a condition:");
                            String choice5 = sc.nextLine().toUpperCase();
                            try {
                                bookRepository.addBook(choice0, choice22, choice3, choice4, Book.Condition.valueOf(choice5));
                            } catch (BookAlreadyExistException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println("Loading...");
                            System.out.println(choice22 + " has been successfully added to the book repository!");
                            break;
                        case "R":
                            System.out.println("Please provide an ISBN Number:");
                            long choice6 = scanner.nextLong();
                            System.out.println("Loading...");
                            try {
                                bookRepository.removeBook(choice6);
                            } catch (BookDoesNotExistException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(choice6 + "has been successfully removed from the book repository");
                            break;
                        case "C":
                            Scanner s1 = new Scanner(System.in);
                            Scanner s2 = new Scanner(System.in);
                            System.out.println("Please provide a library user ID:");
                            int choice7 = scanner.nextInt();
                            System.out.println("Please provide an ISBN Number:");
                            long choice8 = s2.nextLong();
                            System.out.println("Please provide a return date:");
                            String choice9 = s1.nextLine();
                            String[] split = choice9.split("/");
                            int day = Integer.parseInt(split[0]);
                            int month = Integer.parseInt(split[1]);
                            int year = Integer.parseInt(split[2]);
                            System.out.println("Loading...");
                            try {
                                bookRepository.checkOutBook(choice8, choice7, new Date(day, month, year));
                            } catch (BookAlreadyCheckedOutException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println("book " + choice8 + " has been checked out by " + choice7 + " and must be returned on " + choice9);
                            break;
                        case "P":
                            System.out.print("Please select a shelf:");
                            int shelf = scanner.nextInt();
                            System.out.println("Loading...");
                            BookRepository.printTable(shelf);
                            break;
                        case "S":
                            Scanner scan = new Scanner(System.in);
                            System.out.println("Please select a shelf");
                            int shelf1 = scanner.nextInt();
                            System.out.println("Please slect a sorting criteria");
                            String criteria = scan.nextLine().toUpperCase();
                            switch (criteria) {
                                case "I" -> {
                                    String criter = "ISBN";
                                    bookRepository.sortShelf(shelf1, criter);
                                    System.out.println("Loading...");
                                    System.out.println("Shelf " + shelf1 + " has been sorted by " + criter + "!");
                                }
                                case "N" -> {
                                    String criter1 = "TITLE";
                                    bookRepository.sortShelf(shelf1, criter1);
                                    System.out.println("Loading...");
                                    System.out.println("Shelf " + shelf1 + "has been sorted by" + criter1 + "!");
                                }
                                case "A" -> {
                                    String criter2 = "AUTHOR";
                                    bookRepository.sortShelf(shelf1, criter2);
                                    System.out.println("Loading...");
                                    System.out.println("Shelf " + shelf1 + "has been sorted by" + criter2 + "!");
                                }
                                case "G" -> {
                                    String criter3 = "GENRE";
                                    bookRepository.sortShelf(shelf1, criter3);
                                    System.out.println("Loading...");
                                    System.out.println("Shelf " + shelf1 + "has been sorted by" + criter3 + "!");
                                }
                                case "Y" -> {
                                    String criter4 = "YEAR";
                                    bookRepository.sortShelf(shelf1, criter4);
                                    System.out.println("Loading...");
                                    System.out.println("Shelf " + shelf1 + "has been sorted by" + criter4 + "!");
                                }
                                case "C" -> {
                                    String criter5 = "CONDITION";
                                    bookRepository.sortShelf(shelf1, criter5);
                                    System.out.println("Loading...");
                                    System.out.println("Shelf " + shelf1 + "has been sorted by" + criter5 + "!");
                                }
                            }
                        default:
                            System.out.println("Invalid Choice: please try again.");
                            break;
                    }
                case 'r':
                case 'R':
                    System.out.println("Please select an option:");
                    String choice10 = scanner.nextLine().toUpperCase();
                    switch (choice10) {
                        case "R" -> {
                            Scanner s3 = new Scanner(System.in);
                            Scanner s4 = new Scanner(System.in);
                            System.out.println("Please provide the ISBN of the book you're returning:");
                            long choice11 = scanner.nextLong();
                            System.out.println("Please put your Library UserID");
                            int choice12 = s3.nextInt();
                            System.out.println("Please enter the current date:");
                            String choice13 = s4.nextLine();
                            String[] splitted = choice13.split("/");
                            int day1 = Integer.parseInt(splitted[0]);
                            int month1 = Integer.parseInt(splitted[1]);
                            int year1 = Integer.parseInt(splitted[2]);
                            System.out.println("Loading...");
                            if (stack.pushLog(choice11, choice12, new Date(day1, month1, year1))) {
                                System.out.println("book " + choice11 + " has been returned on time!");
                            } else {
                                System.out.println("book " + choice11 + " has been returned LATE! Checking everything in...");
                            }
                        }
                        case "L" -> {
                            System.out.println("Loading...");
                            System.out.println("book " + stack.getTopLog().getISBN() + " is the next book to be checked in");
                        }
                        case "C" -> {
                            System.out.println("Loading...");
                            System.out.println("book " + stack.pushLog(stack.getTopLog().getISBN(), stack.getTopLog().getUserID(), stack.getTopLog().getReturnDate()) + " has been checked in!");
                        }
                        case "P" -> stack.printTable();
                    }
                case 'q':
                case 'Q':
                    break;
            }
        }
    }
}
