package de.ludi.weekPlanerAPI.dao;

import de.ludi.weekPlanerAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

  }
