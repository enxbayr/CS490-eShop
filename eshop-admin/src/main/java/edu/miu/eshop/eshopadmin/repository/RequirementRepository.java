package edu.miu.eshop.eshopadmin.repository;

import edu.miu.eshop.eshopadmin.domain.Requirement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends MongoRepository<Requirement, String> {

    @Query("{status : {$ne : DELETED}}")
    List<Requirement> findByVendorId(String vendorId);

    @Query("{status : {$ne : DELETED}}")
    List<Requirement> findByEngineerId(String engineerId);
}
