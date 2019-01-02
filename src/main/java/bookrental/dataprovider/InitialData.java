package bookrental.dataprovider;

import bookrental.account.Account;
import bookrental.account.AccountRepository;
import bookrental.book.Book;
import bookrental.book.BookRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitialData {

    private final BookRepository bookRepository;
    private final AccountRepository userRepository;

    @Autowired
    public InitialData(BookRepository bookRepository, AccountRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addBooksToDB() {
        log.info("Persisted book data to database");
        bookRepository.save(
                new Book("Krzyżacy", "Henryk Sienkiewicz", "powieść historyczna", LocalDateTime.now(), true));

        bookRepository.save(
                new Book("Nowe przygody Pana Samochodzika", "Zbigniew Nienacki", "powieść dla młodzieży", LocalDateTime.now(), true));

        bookRepository.save(
                new Book("Pan Tadeusz", "Adam Mickiewicz", "poemat poetycki", LocalDateTime.now(), true));

        bookRepository.save(
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", "dramat", LocalDateTime.now(), true));
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addUserToDB() {
        log.info("Persisted account data to database");
        userRepository.save(
                new Account("admin", "123", 0));

        userRepository.save(
                new Account("piotri", "123,", 0));
    }
}