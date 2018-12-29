package bookrental.model.account;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private long amountOfCashToPay;

    public Account(String name, String password, long amountOfCashToPay) {
        this.name = name;
        this.password = password;
        this.amountOfCashToPay = amountOfCashToPay;
    }

    public Account(int accountID) {
        this.id = accountID;
    }
}
