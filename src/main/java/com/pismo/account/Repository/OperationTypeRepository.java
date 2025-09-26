package com.pismo.account.Repository;

import com.pismo.account.DTO.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType,Long> {
    boolean existsByOperationTypeId(Long operationTypeId);
}
