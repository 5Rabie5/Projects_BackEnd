package at.nacs.drhouseaccountancy.communication;

import at.nacs.drhouseaccountancy.DTO.PatientDTO;
import at.nacs.drhouseaccountancy.logic.Accountant;
import at.nacs.drhouseaccountancy.persistence.domain.Invoice;
import at.nacs.drhouseaccountancy.persistence.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AccountEndPoints {

    private final Accountant accountant;

    @PostMapping("/patients")
    Patient merge(@RequestBody PatientDTO patientDTO) {
        System.out.println("i am here post ");
        return accountant.merge(patientDTO);
    }

    @GetMapping("/invoices")
    List<Invoice> displlayAllInvoices() {
        return accountant.displlyAllInvoice();
    }

    @PutMapping("/invoices/{id}/paid")
    void setAspaid(@PathVariable Long id) {
        accountant.setAspaid(id);
    }
    }


