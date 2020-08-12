package edu.miu.eshop.bankapi.Controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.miu.eshop.bankapi.Service.CardService;
import edu.miu.eshop.bankapi.Service.PaymentMethodService;
import edu.miu.eshop.bankapi.Service.TransactionHistService;
import edu.miu.eshop.bankapi.domain.Card;
import edu.miu.eshop.bankapi.domain.PaymentMethod;
import edu.miu.eshop.bankapi.domain.Transaction;
import edu.miu.eshop.bankapi.domain.TransactionHist;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService service;

    @Autowired
    private TransactionHistService histservice;

    @Autowired
    private PaymentMethodService payment;

    @PostMapping("/addcard")
    public ResponseEntity<?> addCard(@RequestBody Card card) {
        String resp = service.addNewCard(card);
        return new ResponseEntity<String>(resp, HttpStatus.OK);

    }

    @GetMapping("/getcard/{cardnumber}")
    public ResponseEntity<?> getCard(@PathVariable("cardnumber") BigInteger cardnumber) {
        Card card = service.getCard(cardnumber);
        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }

    @PostMapping("/process")
    public ResponseEntity<?> processTransaction(@RequestBody Transaction transaction) {
        RestTemplate restTemplate = new RestTemplate();
        String cardnumber = transaction.getCard().getCardNumber();
        String cardtype = service.getCardType(cardnumber);
        PaymentMethod paymentmethod = payment.findPaymentMethodByname(cardtype);
        String url = paymentmethod.getUrl();
        Boolean response = restTemplate.postForObject(url, transaction, Boolean.class);
        LocalDate date = LocalDate.now();
        ZoneId zone = ZoneId.of("America/Chicago");
        LocalTime time = LocalTime.now(zone);
        histservice.saveTransactionHist(transaction, response, date, time);
        return new ResponseEntity<Boolean>(response, HttpStatus.OK);

    }

    @GetMapping("/paymentmethod/{id}")
    public ResponseEntity<?> getPaymentMethod(@PathVariable("id") String id) {
        PaymentMethod paymentmethod = payment.findPaymentMethodById(id);
        return new ResponseEntity<PaymentMethod>(paymentmethod, HttpStatus.OK);

    }

    @GetMapping("/deletepaymentmethod/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable("id") String id) {
        payment.deletePaymentMethod(id);
        return new ResponseEntity<String>("Payment method deleted successfully", HttpStatus.OK);

    }

    @GetMapping("/allpaymentmethod")
    public ResponseEntity<?> getAllPaymentMethod() {
        List<PaymentMethod> paymentmethod = payment.getAllPaymentMethod();
//	for (PaymentMethod payment : paymentmethod) {
//		payment.set
//	}
        return new ResponseEntity<List<PaymentMethod>>(paymentmethod, HttpStatus.OK);
    }

    @PostMapping("/addpaymentmethod")
    public ResponseEntity<?> createPaymentMethod(@RequestBody PaymentMethod paymentmethod) {
        payment.addPaymentMethod(paymentmethod);
        return new ResponseEntity<String>("Payment Method Created Successfully", HttpStatus.OK);

    }


    @GetMapping("/totalsuccess")
    public ResponseEntity<?> totalSuccessfull() {
        double totalAmount = histservice.getTotalTransaction(true);
        return new ResponseEntity<Double>(totalAmount, HttpStatus.OK);
    }


    @GetMapping("/totalfailure")
    public ResponseEntity<?> totalFailure() {
        double totalAmount = histservice.getTotalTransaction(false);
        return new ResponseEntity<Double>(totalAmount, HttpStatus.OK);
    }


    @GetMapping("/allTransaction")
    public ResponseEntity<?> allTransactionHist() {
        List<TransactionHist> transaction = histservice.getAllTransactionHist();
        return new ResponseEntity<List<TransactionHist>>(transaction, HttpStatus.OK);

    }
}
