package edu.elon.NearU.service;


import edu.elon.NearU.entity.Book;
import edu.elon.NearU.exception.ResourceNotFoundException;

import edu.elon.NearU.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor // Lombok generates constructor injection for all final fields
public class BookService {

    private final BookRepository bookRepository;

    // ── Create ────────────────────────────────────────────────

    public Book createBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException(
                    "A book with ISBN '" + book.getIsbn() + "' already exists");
        }
        return bookRepository.save(book);
    }

    // ── Read ──────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
    }

    @Transactional(readOnly = true)
    public Page<Book> getBooksWithPagination(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return bookRepository.findAll(PageRequest.of(page, size, sort));
    }

    @Transactional(readOnly = true)
    public List<Book> searchBooks(String term) {
        return bookRepository.searchByTitleOrAuthor(term);
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksInPriceRange(double min, double max) {
        return bookRepository.findByPriceBetween(min, max);
    }

    // ── Update ────────────────────────────────────────────────

    public Book updateBook(Long id, Book updated) {
        Book existing = getBookById(id);
        if (!existing.getIsbn().equals(updated.getIsbn()) &&
                bookRepository.existsByIsbn(updated.getIsbn())) {
            throw new IllegalArgumentException("ISBN '" + updated.getIsbn() + "' is already in use");
        }
        existing.setTitle(updated.getTitle());
        existing.setAuthor(updated.getAuthor());
        existing.setIsbn(updated.getIsbn());
        existing.setPrice(updated.getPrice());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setGenre(updated.getGenre());
        existing.setIsAvailable(updated.getIsAvailable());
        return bookRepository.save(existing);
    }

    public Book updateStock(Long id, int quantity) {
        Book book = getBookById(id);
        if (book.getStockQuantity() + quantity < 0) {
            throw new IllegalArgumentException(
                    "Insufficient stock. Current: " + book.getStockQuantity());
        }
        book.setStockQuantity(book.getStockQuantity() + quantity);
        book.setIsAvailable(book.getStockQuantity() > 0);
        return bookRepository.save(book);
    }

    // ── Delete ────────────────────────────────────────────────

    public void deleteBook(Long id) {
        bookRepository.delete(getBookById(id));
    }

    // ── Stats ─────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public Map<String, Object> getStats() {
        List<Object[]> genreStats = bookRepository.getGenreStatistics();
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBooks", bookRepository.count());
        stats.put("availableBooks", bookRepository.findByIsAvailableTrue().size());
        stats.put("genreBreakdown", genreStats.stream().map(row -> {
            Map<String, Object> g = new HashMap<>();
            g.put("genre", row[0]); g.put("count", row[1]);
            g.put("avgPrice", row[2]); g.put("minPrice", row[3]); g.put("maxPrice", row[4]);
            return g;
        }).toList());
        return stats;
    }
}
