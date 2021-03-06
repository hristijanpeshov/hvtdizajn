package com.example.proektdians.service.impl;

import com.example.proektdians.model.MoneyObject;
import com.example.proektdians.repository.MoneyObjectRepository;
import com.example.proektdians.service.MoneyService;
import org.springframework.stereotype.Service;
import com.example.proektdians.model.enumeration.Type;

import java.util.List;
import java.util.Optional;

@Service
public class MoneyServiceImpl implements MoneyService {

    private final MoneyObjectRepository repository;


    public MoneyServiceImpl(MoneyObjectRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<MoneyObject> listAll() {
        return repository.findAll();
    }


    @Override
    public List<MoneyObject> listObjectByType(String type) {
        if(type.equals(Type.BANK.toString().toLowerCase()))
            return repository.findObjectsByType(Type.BANK);
        else if(type.equals(Type.ATM.toString().toLowerCase())){
            return repository.findObjectsByType(Type.ATM);
        }
        else return repository.findObjectsByType(Type.EXCHANGE);
    }

    @Override
    public Optional<MoneyObject> findObjectById(long id) {
        return repository.findObjectById(id);
    }
}
