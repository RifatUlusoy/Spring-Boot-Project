package experience.demo.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCustomerDto {
    private Long id;
    private LocalDateTime creationDateTime;
    private LocalDateTime updateDateTime;
    private String email;
    private String password;
}
