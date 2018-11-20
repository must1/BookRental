package bookrental.configuration;

import bookrental.model.account.User;
import bookrental.model.book.Book;
import bookrental.repository.account.UserRepository;
import bookrental.repository.book.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitialData {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public InitialData(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addBooksToDB() {
        log.info("Persisted book data to database");
        bookRepository.save(
                new Book("Krzyżacy", "Henryk Sienkiewicz", "powieść historyczna", true));

        bookRepository.save(
                new Book("Nowe przygody Pana Samochodzika", "Zbigniew Nienacki", "powieść dla młodzieży", true));

        bookRepository.save(
                new Book("Pan Tadeusz", "Adam Mickiewicz", "poemat poetycki", true));

        bookRepository.save(
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", true));
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addUserToDB() {
        log.info("Persisted user data to database");
        userRepository.save(
                new User("admin", "123"));

        userRepository.save(
                new User("piotri", "123"));
    }
}
