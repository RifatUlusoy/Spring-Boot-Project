package experience.demo.Service;



import experience.demo.Dto.Converter.CustomerDtoConverter;
import experience.demo.Dto.CustomerDto;
import experience.demo.Model.Customer;
import experience.demo.Repository.CustomerRepository;

import experience.demo.Security.SecurityCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService  {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;



    public Customer saveRepository(Customer customer){
        return customerRepository.save(customer);
    }
    public Boolean existsByEmail(String email){
        return customerRepository.existsByEmail(email);
    }


    public Customer findCustomerByEmail(String email){
        return customerRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
    public CustomerDto getCustomerDtoByEmail(String email){
       return converter.convertToCustomerDto(findCustomerByEmail(email));
    }
    public Customer getCustomerInTokenContext(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return findCustomerByEmail(userDetails.getUsername());
    }
}
