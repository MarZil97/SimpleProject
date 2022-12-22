package lt.codeacademy.bussines.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.bussines.entities.Uzsakymai;

@Repository
public interface OrderRepository extends JpaRepository<Uzsakymai, Long>{

}
