package experience.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    private LocalDateTime creationDateTime;
    private LocalDateTime updateDateTime;
    private String email;
    private String password;
    private Set<CustomerAddressDto> addresses;
}
