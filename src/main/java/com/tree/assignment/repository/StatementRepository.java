package com.tree.assignment.repository;

import com.tree.assignment.entity.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
    // Add custom query methods for statement retrieval.
}
