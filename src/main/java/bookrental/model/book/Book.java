package bookrental.model.book;

import lombok.*;

import javax.persistence.*;

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
    private String title;
    private String author;
    private String category;
    private boolean available;

    public Book(String title, String author, String category, boolean available) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
    }

    public Book(int bookID) {
        this.id = bookID;
    }
}
