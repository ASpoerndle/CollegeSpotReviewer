package edu.elon.NearU.controllers;


import edu.elon.NearU.entity.Book;
import edu.elon.NearU.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getBookById(Long.parseLong(isbn)));
    }

    // Paginated endpoint — GET /api/books/paginated?page=0&size=10&sortBy=price&direction=asc
    @GetMapping("/paginated")
    public ResponseEntity<Page<Book>> getPaginated(
            @RequestParam(defaultValue = "0")          int    page,
            @RequestParam(defaultValue = "10")         int    size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc")      String direction) {
        return ResponseEntity.ok(bookService.getBooksWithPagination(page, size, sortBy, direction));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> search(@RequestParam String q) {
        return ResponseEntity.ok(bookService.searchBooks(q));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<Book>> priceRange(
            @RequestParam double min, @RequestParam double max) {
        return ResponseEntity.ok(bookService.getBooksInPriceRange(min, max));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(bookService.getStats());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Book> updateStock(
            @PathVariable Long id, @RequestBody Map<String, Integer> body) {
        return ResponseEntity.ok(bookService.updateStock(id, body.get("quantity")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(Map.of("message", "Book with ID " + id + " deleted successfully"));
    }
}
