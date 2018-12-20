package bookrental.model.account;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private long amountOfCashToPay;

    public User(String name, String password, long amountOfCashToPay) {
        this.name = name;
        this.password = password;
        this.amountOfCashToPay = amountOfCashToPay;
    }

    public User(int userID) {
        this.id = userID;
    }
}
