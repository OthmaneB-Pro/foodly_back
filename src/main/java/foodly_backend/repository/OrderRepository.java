package foodly_backend.repository;

import foodly_backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    Optional<OrderEntity> findByUserIdAndStatus(int userId, String inCart);
}
