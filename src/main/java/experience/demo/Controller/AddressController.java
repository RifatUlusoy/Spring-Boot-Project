package experience.demo.Controller;


import experience.demo.Dto.AddressDto;
import experience.demo.Dto.Request.CreateAddressRequest;
import experience.demo.Dto.Request.UpdateAddressRequest;
import experience.demo.Service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @GetMapping("/hello")
    public String hello(){
        return "Hello from address";
    }
    @PostMapping("/create")
    public ResponseEntity<AddressDto> createAddress(@RequestBody CreateAddressRequest createAddressRequest){
        return ResponseEntity.ok(addressService.createAddress(createAddressRequest));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @RequestBody UpdateAddressRequest updateAddressRequest){
        return ResponseEntity.ok(addressService.updateAddress(id,updateAddressRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddress(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<AddressDto>> getAllAddress(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok().body("Address deleted successfully");
    }
}
