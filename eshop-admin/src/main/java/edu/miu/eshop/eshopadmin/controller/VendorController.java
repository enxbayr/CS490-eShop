package edu.miu.eshop.eshopadmin.controller;

// EB

import edu.miu.eshop.eshopadmin.domain.Dto.BankCardDto;
import edu.miu.eshop.eshopadmin.domain.Dto.PasswordDto;
import edu.miu.eshop.eshopadmin.domain.Dto.VendorDto;
import edu.miu.eshop.eshopadmin.domain.Vendor;
import edu.miu.eshop.eshopadmin.exception.CustomerNotFoundException;
import edu.miu.eshop.eshopadmin.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<VendorDto> getAllCustomer(){
        return vendorService.getAll().stream().map(VendorDto::build).collect(Collectors.toList());
    }

    @GetMapping("/{vendorId}")
    public VendorDto findCustomerById(@PathVariable("vendorId") String vendorId){
        try {
            return VendorDto.build(vendorService.findById(vendorId));
        } catch (CustomerNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Vendor ID not found!", ex);
        }
    }

    // -- Related to the BANK CARD --
    @GetMapping("/{vendorId}/cards")
    public Set<BankCardDto> getCards(@PathVariable("vendorId") String vendorId){
        return vendorService.findById(vendorId).getCards();
    }

    @PatchMapping("/{vendorId}/addedcard")
    public void addCard(@PathVariable("vendorId") String vendorId, @RequestBody BankCardDto bankCardDto){
        try {
            Vendor vendor = vendorService.findById(vendorId);
            vendor.addCard(bankCardDto);
            vendorService.save(vendor);
        }catch(CustomerNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Vendor not found!", ex);
        }
    }

    @PatchMapping("/{vendorId}/removedcard")
    public void removeCard(@PathVariable("vendorId") String vendorId, @RequestBody BankCardDto bankCardDto){
        vendorService.removeCard(vendorId, bankCardDto);
    }
    // -- End of BANK CARD --

    @PutMapping("/{vendorId}")
    public VendorDto updateCustomer(@PathVariable("vendorId") String vendorId, @RequestBody VendorDto newCustomer){
        return VendorDto.build(vendorService.update(vendorId, newCustomer));
    }

    @DeleteMapping("/{vendorId}")
    public void deleteCustomer(@PathVariable("vendorId") String vendorId) {
        vendorService.deleteById(vendorId);
    }

    @PatchMapping("/{vendorId}/reset")
    private void resetPassword(@PathVariable String vendorId, @RequestBody PasswordDto passwordDto){
        vendorService.resetPassword(vendorId, passwordDto.getPassword());
    }

///users/{}/reset https://www.baeldung.com/spring-security-registration-i-forgot-my-password
}
