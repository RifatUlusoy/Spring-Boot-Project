package experience.demo.Dto.Converter;

import experience.demo.Dto.CustomerAddressDto;
import experience.demo.Model.Address;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressDtoConverter {

    public CustomerAddressDto convertToDto(Address from) {
        return CustomerAddressDto.builder()
                .id(from.getId())
                .creationDateTime(from.getCreationTimestamp())
                .updateDateTime(from.getUpdateTimestamp())
                .name(from.getName())
                .city(from.getCity())
                .district(from.getDistrict())
                .build();
    }
}
