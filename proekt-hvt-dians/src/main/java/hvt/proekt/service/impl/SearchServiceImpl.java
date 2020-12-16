package hvt.proekt.service.impl;


import hvt.proekt.model.MoneyObject;
import hvt.proekt.repository.MoneyObjectRepository;
import hvt.proekt.service.SearchService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final MoneyObjectRepository repository;

    public SearchServiceImpl(MoneyObjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MoneyObject> findAllObjects(String name, String type) throws FileNotFoundException {
        return repository.findObjectByName(name, type);
    }

    @Override
    public List<MoneyObject> findNClosest(int n) throws FileNotFoundException {
        return repository.findObjectByCloseness(n);
    }
}
