package rikkei.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rikkei.academy.model.SmartPhone;

import java.util.List;
@Repository
public interface ISmartPhoneRepository extends JpaRepository<SmartPhone,Long> {
    List<SmartPhone> findAllByProducerContaining(String producer);
    boolean existsByProducer(String producer); //Hàm kiểm tra DB đã tồn tại hay chưa
}
