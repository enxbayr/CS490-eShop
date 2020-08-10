package edu.miu.eshop.eshopadmin.repository;

// EB

import edu.miu.eshop.eshopadmin.domain.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, String> {

    Optional<Vendor> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<Vendor> findByPersonId(String personId);
}
