package com.dshabu.CurrencyConverter.repository;

import com.dshabu.CurrencyConverter.model.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HistoryRepo extends CrudRepository<History, Integer> {
    List<History> findAllByOrderByInsertedDesc();
}
