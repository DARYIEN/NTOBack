package com.example.nto.repository;

import com.example.nto.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodeRepository extends JpaRepository<Code, Long> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Code c WHERE c.id = :id AND c.value = :value")
    boolean checkCode(long id, long value);
}
