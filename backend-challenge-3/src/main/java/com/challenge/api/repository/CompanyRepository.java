package com.challenge.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.api.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
