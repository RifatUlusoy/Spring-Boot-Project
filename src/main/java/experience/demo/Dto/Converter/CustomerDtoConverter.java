package experience.demo.Dto.Converter;

import experience.demo.Dto.CustomerDto;
import experience.demo.Model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomerDtoConverter {
    private final CustomerAddressDtoConverter customerAddressDtoConverter;
    public CustomerDto convertToCustomerDto(Customer from){

        return CustomerDto.builder()
                .id(from.getId())
                .creationDateTime(from.getCreationTimestamp())
                .updateDateTime(from.getUpdateTimestamp())
                .email(from.getEmail())
                .password(from.getPassword())
                .addresses(from.getAddresses() == null ? null:
                        from.getAddresses().stream()
                                .map(customerAddressDtoConverter::convertToDto)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
