package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.dto.ResponseMessage;
import rikkei.academy.model.SmartPhone;
import rikkei.academy.service.ISmartPhoneService;

import java.util.Optional;

@RestController
@RequestMapping("phone")
@CrossOrigin(origins = "*") // Để ghép 2 nền tảng khác nhau, tức là FE với BE với 2 đường link khác nhau
public class SmartPhoneController {
    @Autowired
    ISmartPhoneService smartPhoneService;
    @PostMapping
    public ResponseEntity<?> createSmartPhone(@RequestBody SmartPhone smartPhone){
        if (smartPhoneService.existsByProducer(smartPhone.getProducer())){
            return new ResponseEntity<>(new ResponseMessage("no_producer"), HttpStatus.OK);
        }
        smartPhoneService.save(smartPhone);
        return new ResponseEntity<>(new ResponseMessage("create_success"), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getListSmartPhone(){
        return new ResponseEntity<>(smartPhoneService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/search/{producer}")
    public ResponseEntity<?> findAllByProducer(@PathVariable String producer){
        return new ResponseEntity<>(smartPhoneService.findAllByProducerContaining(producer),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailSmartPhone(@PathVariable Long id){
        Optional<SmartPhone> smartPhone = smartPhoneService.findById(id);
        if (!smartPhone.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(smartPhone.get(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSmartPhone(@PathVariable Long id){
        Optional<SmartPhone> smartPhone = smartPhoneService.findById(id);
        if (!smartPhone.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhoneService.deleteById(smartPhone.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success!"),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updateSmartPhone(@PathVariable Long id,@RequestBody SmartPhone smartPhone){
        Optional<SmartPhone> smartPhone1 = smartPhoneService.findById(id);
        if (!smartPhone1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartPhone1.get().setProducer(smartPhone.getProducer());
        smartPhone1.get().setModel(smartPhone.getModel());
        smartPhone1.get().setPrice(smartPhone.getPrice());
        smartPhoneService.save(smartPhone1.get());
        return new ResponseEntity<>(new ResponseMessage("update_success!"),HttpStatus.OK);
    }
}
