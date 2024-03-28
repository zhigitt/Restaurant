package java12.repository;

import java12.entities.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepo extends JpaRepository<Cheque, Long> {

}
