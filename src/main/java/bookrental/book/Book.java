package bookrental.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String category;
    private boolean available;
    @JsonIgnore
    private LocalDateTime dateOfCreation;

    public Book(String title, String author, String category, LocalDateTime dateOfCreation, boolean available) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.dateOfCreation = dateOfCreation;
        this.available = available;
    }

    public Book(String title, String author, String category, boolean available) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
    }

    public Book(int id, String title, String author, String category, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
    }
}
