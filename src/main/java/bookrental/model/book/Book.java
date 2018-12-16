package bookrental.model.book;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String category;
    private boolean available;

    public Book(String title, String author, String category, boolean available) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
    }
}
