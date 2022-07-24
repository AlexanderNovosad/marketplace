package com.challenge.marketplace.repo;

import com.challenge.marketplace.entity.Product;
import com.challenge.marketplace.entity.Sale;
import com.challenge.marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findAllByUser(User user);
    List<Sale> findAllByProduct(Product product);
    void deleteAllByProduct (Product product);
    void deleteAllByUser (User user);
}
