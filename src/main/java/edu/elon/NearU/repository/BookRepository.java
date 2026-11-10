package edu.elon.NearU.repository;


import edu.elon.NearU.entity.Book;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // ── Derived query methods ─────────────────────────────────────
    // Spring Data generates SQL from the method name — no implementation needed

    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(String genre);
    List<Book> findByIsAvailableTrue();
    List<Book> findByStockQuantityGreaterThan(int quantity);
    List<Book> findByPriceBetween(double min, double max);
    List<Book> findByAuthorAndGenre(String author, String genre);
    List<Book> findAllByOrderByCreatedAtDesc();
    List<Book> findByGenreOrderByPriceAsc(String genre);
    boolean  existsByIsbn(String isbn);

    // Pagination
    Page<Book> findByGenre(String genre, Pageable pageable);
    Page<Book> findByIsAvailableTrue(Pageable pageable);

    // ── JPQL queries (entity class names, not table names) ────────
    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Book> searchByTitleOrAuthor(@Param("term") String term);

    @Query("SELECT b.genre, COUNT(b), AVG(b.price), MIN(b.price), MAX(b.price) " +
            "FROM Book b GROUP BY b.genre ORDER BY COUNT(b) DESC")
    List<Object[]> getGenreStatistics();

    // ── Native SQL (database-specific features) ───────────────────
    @Query(value = "SELECT * FROM books WHERE title LIKE CONCAT('%',:t,'%') OR " +
            "author LIKE CONCAT('%',:t,'%') OR description LIKE CONCAT('%',:t,'%')",
            nativeQuery = true)
    List<Book> fullTextSearch(@Param("t") String term);

    // ── Modifying queries (UPDATE / DELETE) — require @Modifying ──
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.stockQuantity = b.stockQuantity - :qty WHERE b.id = :id")
    int decreaseStock(@Param("id") Long id, @Param("qty") int quantity);
}