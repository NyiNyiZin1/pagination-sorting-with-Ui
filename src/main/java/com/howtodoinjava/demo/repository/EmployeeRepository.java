package com.howtodoinjava.demo.repository;

import java.util.Date;

import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.EmployeeEntity;
import org.springframework.data.domain.Pageable;

@Repository
public interface EmployeeRepository
        extends PagingAndSortingRepository<EmployeeEntity, Long> {

	Slice<EmployeeEntity> findByDateDataBetweenAndFirstNameAndLastName(Date fromDate, Date toDate,String firstName,String lastName, Pageable paging);
 
}