package experience.demo.Dto.Converter;

import experience.demo.Dto.AddressCustomerDto;
import experience.demo.Model.Customer;
import org.springframework.stereotype.Component;

@Component
public class AddressCustomerDtoConverter {

    public AddressCustomerDto convertToDto(Customer from){
        return AddressCustomerDto.builder()
                .id(from.getId())
                .creationDateTime(from.getCreationTimestamp())
                .updateDateTime(from.getUpdateTimestamp())
                .email(from.getEmail())
                .password(from.getPassword())
                .build();
    }
}
