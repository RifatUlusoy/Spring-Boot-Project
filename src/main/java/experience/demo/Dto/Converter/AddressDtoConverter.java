package experience.demo.Dto.Converter;

import experience.demo.Dto.AddressDto;
import experience.demo.Model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDtoConverter {
    private final AddressCustomerDtoConverter addressCustomerDtoConverter;

    public AddressDto convertToAddressDto(Address from){

        return AddressDto.builder()
                .id(from.getId())
                .creationDateTime(from.getCreationTimestamp())
                .updateDateTime(from.getUpdateTimestamp())
                .name(from.getName())
                .city(from.getCity())
                .district(from.getDistrict())
                .customers(addressCustomerDtoConverter.convertToDto(from.getCustomer()))
                .build();
    }
}
