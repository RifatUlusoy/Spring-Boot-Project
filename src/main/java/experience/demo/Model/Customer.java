package experience.demo.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@ToString
@Table(name="customers")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {

    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Address> addresses;
}
