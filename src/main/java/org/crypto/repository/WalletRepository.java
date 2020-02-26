package org.crypto.repository;

import org.crypto.model.WalletDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletDto, Long> {
}
