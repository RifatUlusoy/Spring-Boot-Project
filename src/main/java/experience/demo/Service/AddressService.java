package experience.demo.Service;
import experience.demo.Dto.AddressDto;
import experience.demo.Dto.Converter.AddressDtoConverter;
import experience.demo.Dto.Request.CreateAddressRequest;
import experience.demo.Dto.Request.UpdateAddressRequest;
import experience.demo.Model.Address;
import experience.demo.Model.Customer;
import experience.demo.Repository.AddressRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerService customerService;
    private final AddressDtoConverter addressDtoConverter;
    public Boolean existsByName(String name){
        return addressRepository.existsByName(name);
    }
    public AddressDto createAddress(CreateAddressRequest createAddressRequest){
        var isAlreadyExists = addressRepository.existsByName(createAddressRequest.getName());

        if(isAlreadyExists){
            throw new EntityExistsException("Is already exists");
        }
        Customer customer = customerService.getCustomerInTokenContext();
        Address address = new Address();
        address.setName(createAddressRequest.getName());
        address.setCity(createAddressRequest.getCity());
        address.setDistrict(createAddressRequest.getDistrict());
        address.setCustomer(customer);

        customer.getAddresses().add(address);

        return addressDtoConverter.convertToAddressDto(addressRepository.save(address));
    }
    protected Address findAddressById(Long id){
        return addressRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("Id:"+id+" address not found"));
    }
    public void deleteAddress(Long id){
        Customer customer = customerService.getCustomerInTokenContext();
        Address address = findAddressById(id);

        if(address.getCustomer().getId() != customer.getId()){
            throw new IllegalArgumentException("Customer id doesn't match the address owner");
        }
    }
    public AddressDto getAddress(Long id){
        Customer customer = customerService.getCustomerInTokenContext();
        Address address = findAddressById(id);

        if(address.getCustomer().getId()!=customer.getId()){
            throw new IllegalArgumentException("You can only get your own address");
        }
        return addressDtoConverter.convertToAddressDto(address);
    }
    public List<AddressDto> getAllAddress(){
        Customer customer = customerService.getCustomerInTokenContext();
        return customer.getAddresses().stream().map(addressDtoConverter::convertToAddressDto).collect(Collectors.toList());
    }
    public AddressDto updateAddress(Long id, UpdateAddressRequest updateAddressRequest){
        Customer customer = customerService.getCustomerInTokenContext();
        Address address = findAddressById(id);
        if(address.getCustomer().getId() != customer.getId()){
            throw new IllegalArgumentException("You can only update your own address");
        }
        var isAlreadyExists = existsByName(updateAddressRequest.getName());
        if(isAlreadyExists){
            throw new EntityExistsException("Is already exists");
        }
        address.setName(updateAddressRequest.getName());
        address.setCity(updateAddressRequest.getCity());
        address.setDistrict(updateAddressRequest.getDistrict());
        address.setCustomer(customer);
        return addressDtoConverter.convertToAddressDto(addressRepository.save(address));
    }
}
