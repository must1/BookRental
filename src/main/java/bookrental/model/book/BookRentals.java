package bookrental.model.book;

import bookrental.model.account.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BookRentals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Book book;
    @OneToOne
    private User user;
    private LocalDateTime dateOfRental;

    public BookRentals(Book book, User user) {
        this.book = book;
        this.user = user;
    }
}

