package hvt.proekt.service.impl;


import hvt.proekt.model.MoneyObject;
import hvt.proekt.repository.MoneyObjectRepository;
import hvt.proekt.service.SearchService;
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
