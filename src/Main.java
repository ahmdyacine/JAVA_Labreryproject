import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Book> books = new ArrayList<>();
    private static List<BorrowingProcess> borrowings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== قائمة المكتبة =====");
            System.out.println("1. إضافة كتاب");
            System.out.println("2. إضافة مستعير");
            System.out.println("3. إعارة كتاب");
            System.out.println("4. استرجاع كتاب");
            System.out.println("5. عرض كل الكتب");
            System.out.println("6. عرض كل المستعيرين");
            System.out.println("7. عرض الكتب المستعارة لمستعير");
            System.out.println("8. خروج");
            System.out.print("اختر خياراً: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // تنظيف السطر

            switch (choice) {
                case 1:
                    System.out.print("عنوان الكتاب: ");
                    String title = scanner.nextLine();
                    System.out.print("المؤلف: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    books.add(new Book(title, author, isbn));
                    System.out.println("تمت إضافة الكتاب بنجاح.");
                    break;

                case 2:
                    System.out.print("اسم المستعير: ");
                    String name = scanner.nextLine();
                    System.out.print("الرقم الجامعي: ");
                    int id = scanner.nextInt();
                    Borrower.addBorrower(new Borrower(name, id));
                    System.out.println("تمت إضافة المستعير.");
                    break;

                case 3:
                    System.out.print("رقم الطالب: ");
                    int borrowerId = scanner.nextInt();
                    scanner.nextLine();
                    Borrower borrower = findBorrowerById(borrowerId);
                    if (borrower == null) {
                        System.out.println("لم يتم العثور على المستعير.");
                        break;
                    }

                    System.out.print("ISBN الكتاب: ");
                    String borrowIsbn = scanner.nextLine();
                    Book bookToBorrow = findBookByIsbn(borrowIsbn);
                    if (bookToBorrow == null || !bookToBorrow.isAvailable()) {
                        System.out.println("الكتاب غير متاح.");
                        break;
                    }

                    borrower.borrowBook(bookToBorrow);
                    borrowings.add(new BorrowingProcess(bookToBorrow, borrower));
                    System.out.println("تمت إعارة الكتاب.");
                    break;

                case 4:
                    System.out.print("ISBN الكتاب المسترجع: ");
                    String returnIsbn = scanner.nextLine();
                    for (BorrowingProcess bp : borrowings) {
                        if (bp.toString().contains(returnIsbn) && bp.toString().contains("not returned")) {
                            bp.returnBook();
                            System.out.println("تم استرجاع الكتاب.");
                            break;
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n=== قائمة الكتب ===");
                    for (Book b : books) {
                        System.out.println(b);
                    }
                    break;

                case 6:
                    System.out.println("\n=== قائمة المستعيرين ===");
                    for (Borrower b : Borrower.getAllBorrowers()) {
                        System.out.println(b);
                    }
                    break;

                case 7:
                    System.out.print("رقم الطالب: ");
                    int sid = scanner.nextInt();
                    Borrower b = findBorrowerById(sid);
                    if (b != null) {
                        System.out.println("الكتب المستعارة:");
                        for (Book bk : b.getBorrowedBooks()) {
                            System.out.println("- " + bk);
                        }
                    } else {
                        System.out.println("المستعير غير موجود.");
                    }
                    break;

                case 8:
                    running = false;
                    System.out.println("تم إنهاء البرنامج.");
                    break;

                default:
                    System.out.println("خيار غير صحيح، حاول مرة أخرى.");
            }
        }

        scanner.close();
    }

    // البحث عن مستعير حسب رقم الطالب
    private static Borrower findBorrowerById(int id) {
        for (Borrower b : Borrower.getAllBorrowers()) {
            if (b.getStudentId() == id) {
                return b;
            }
        }
        return null;
    }

    // البحث عن كتاب حسب الـ ISBN
    private static Book findBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null;
    }
}
