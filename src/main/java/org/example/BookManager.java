package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookManager {
    private final List<Book> Buku;

    public BookManager() {
        this.Buku = new ArrayList<>();
    }

    public void addBook(Book buku) {
        if (buku == null) {
            throw new IllegalArgumentException("Buku tidak boleh kosong");
        }
        Buku.add(buku);
    }

    public boolean removeBook(String judul) {
        if (judul == null || judul.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul tidak boleh kosong");
        }
        return Buku.removeIf(buku ->
                buku.getJudul_Buku().equalsIgnoreCase(judul.trim()));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(Buku);
    }

    public List<Book> findBooksByAuthor(String penulis) {
        if (penulis == null || penulis.trim().isEmpty()) {
            throw new IllegalArgumentException("Penulis tidak boleh kosong");
        }
        return Buku.stream()
                .filter(book -> book.getPenulis().equalsIgnoreCase(penulis.trim()))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByYear(int buku) {
        if (buku < 0 || buku > 2100) {
            throw new IllegalArgumentException("Tahun hanya bisa diisi dari tahun 2000 sampai 2100");
        }
        return Buku.stream()
                .filter(book -> book.getTahun() == buku)
                .collect(Collectors.toList());
    }

    public int getBookCount() {
        return Buku.size();
    }

    public boolean containsBook(String judul) {
        if (judul == null || judul.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul tidak boleh kosong");
        }
        return Buku.stream()
                .anyMatch(book -> book.getJudul_Buku().equalsIgnoreCase(judul.trim()));
    }

    public void clearAllBooks() {
        Buku.clear();
    }
}