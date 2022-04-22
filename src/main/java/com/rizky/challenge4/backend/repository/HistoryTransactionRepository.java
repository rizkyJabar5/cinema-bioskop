package com.rizky.challenge4.backend.repository;

import com.rizky.challenge4.backend.model.entity.HistoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryTransactionRepository extends JpaRepository<HistoryTransaction, Long> {
}
