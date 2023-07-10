package com.revotech.thuctap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revotech.thuctap.models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
