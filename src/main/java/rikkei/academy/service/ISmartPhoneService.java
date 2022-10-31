package rikkei.academy.service;

import rikkei.academy.model.SmartPhone;

import java.util.List;
import java.util.Optional;

public interface ISmartPhoneService {
    List<SmartPhone> findAll();
    Optional<SmartPhone> findById(Long id);
    void deleteById(Long id);
    SmartPhone save(SmartPhone smartPhone);
    List<SmartPhone> findAllByProducerContaining(String producer);
    boolean existsByProducer(String producer);
    //    Page<SmartPhone> findAll(Pageable pageable);
}
