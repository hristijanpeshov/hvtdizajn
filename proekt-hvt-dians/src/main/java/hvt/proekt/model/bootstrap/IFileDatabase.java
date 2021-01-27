package hvt.proekt.model.bootstrap;

import hvt.proekt.model.MoneyObject;

import java.io.FileNotFoundException;
import java.util.List;

public interface IFileDatabase {

    List<MoneyObject> read() throws FileNotFoundException;

    void save(List<MoneyObject> objects) throws FileNotFoundException;

}
