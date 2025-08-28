import org.example.Book;
import org.example.BookManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookManagerTest {
    private BookManager bookManager;

    @BeforeEach
    void setUp() {
        bookManager = new BookManager();
    }

    @Test
    @DisplayName("Test menambahkan buku")
    void testAddBook() {
        Book buku = new Book("Pemrograman", "Andi", 2020);
        bookManager.addBook(buku);
        assertEquals(1, bookManager.getBookCount());
    }

    @Test
    @DisplayName("Test menghapus buku yang ada")
    void testRemoveExistingBook() {
        Book buku = new Book("Basis Data", "Erlangga", 2021);
        bookManager.addBook(buku);

        boolean removed = bookManager.removeBook("Basis Data");
        assertTrue(removed);
        assertEquals(0, bookManager.getBookCount());
    }

    // Kemungkinan Unit Test dibawah untuk buku yang memang tidak tersapat pada List
    @Test
    @DisplayName("Test menghapus buku yang tidak ada")
    void testRemoveNonExistingBook() {
        // Arrange - Tambahkan satu buku
        Book buku = new Book("Java Programming", "Oracle", 2022);
        bookManager.addBook(buku);

        // Act - Coba hapus buku yang tidak ada
        boolean removed = bookManager.removeBook("Buku Yang Tidak Ada");

        // Assert - Pastikan penghapusan gagal dan jumlah buku tetap 1
        assertFalse(removed);
        assertEquals(1, bookManager.getBookCount());
    }

    // Kemungkinan Unit Test dibawah untuk mencari buku berdasarkan penulis
    @Test
    @DisplayName("Test mencari buku berdasarkan author")
    void testFindBooksByAuthor() {
        // Arrange - Tambahkan beberapa buku dengan penulis yang sama dan berbeda
        Book buku1 = new Book("Java Basics", "John Doe", 2020);
        Book buku2 = new Book("Advanced Java", "John Doe", 2021);
        Book buku3 = new Book("Python Guide", "Jane Smith", 2022);

        bookManager.addBook(buku1);
        bookManager.addBook(buku2);
        bookManager.addBook(buku3);

        // Act - Cari buku berdasarkan penulis "John Doe"
        List<Book> bukuJohnDoe = bookManager.findBooksByAuthor("John Doe");
        List<Book> bukuJaneSmith = bookManager.findBooksByAuthor("Jane Smith");
        List<Book> bukuTidakAda = bookManager.findBooksByAuthor("Unknown Author");

        // Assert - Verifikasi hasil pencarian
        assertEquals(2, bukuJohnDoe.size());
        assertEquals(1, bukuJaneSmith.size());
        assertEquals(0, bukuTidakAda.size());

        // Verifikasi bahwa buku yang ditemukan adalah buku yang benar
        assertTrue(bukuJohnDoe.contains(buku1));
        assertTrue(bukuJohnDoe.contains(buku2));
        assertTrue(bukuJaneSmith.contains(buku3));
    }

    // Kemungkinan Unit Test dibawah untuk seluruh buku yang ada di dalam List
    @Test
    @DisplayName("Test mendapatkan semua buku")
    void testGetAllBooks() {
        // Arrange - Tambahkan beberapa buku
        Book buku1 = new Book("Database Design", "Alice", 2019);
        Book buku2 = new Book("Web Development", "Bob", 2020);
        Book buku3 = new Book("Mobile Apps", "Charlie", 2021);

        bookManager.addBook(buku1);
        bookManager.addBook(buku2);
        bookManager.addBook(buku3);

        // Act - Dapatkan semua buku
        List<Book> semuaBuku = bookManager.getAllBooks();

        // Assert - Verifikasi bahwa semua buku dikembalikan
        assertEquals(3, semuaBuku.size());
        assertTrue(semuaBuku.contains(buku1));
        assertTrue(semuaBuku.contains(buku2));
        assertTrue(semuaBuku.contains(buku3));

        // Verifikasi bahwa list yang dikembalikan adalah copy (tidak mengubah original)
        semuaBuku.clear();
        assertEquals(3, bookManager.getBookCount()); // Original masih utuh
    }
}