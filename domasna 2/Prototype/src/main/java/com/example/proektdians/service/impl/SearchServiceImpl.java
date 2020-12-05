package com.example.proektdians.service.impl;

import com.example.proektdians.model.MoneyObject;
import com.example.proektdians.repository.MoneyObjectRepository;
import com.example.proektdians.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final MoneyObjectRepository repository;

    public SearchServiceImpl(MoneyObjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MoneyObject> findAllObjects(String name) {
        return repository.findObjectByName(name);
    }

    @Override
    public List<MoneyObject> findNClosest(int n) {
        return repository.findObjectByCloseness(n);
    }
}
