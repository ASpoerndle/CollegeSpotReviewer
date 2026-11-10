package edu.elon.NearU.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "books",
        indexes = {
                @Index(name = "idx_book_isbn",   columnList = "isbn",   unique = true),
                @Index(name = "idx_book_author", columnList = "author")
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @NotBlank(message = "Author is required")
    @Column(name = "author", nullable = false, length = 150)
    private String author;

    @NotBlank(message = "ISBN is required")
    @Column(name = "isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock cannot be negative")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "Genre is required")
    @Column(name = "genre", nullable = false, length = 50)
    private String genre;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "publisher", length = 150)
    private String publisher;

    @Column(name = "language", length = 30)
    private String language = "English";

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}