package lt.codeacademy.bussines.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.bussines.entities.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
