package hvt.proekt.model.bootstrap;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.enumeration.Type;
import hvt.proekt.model.util.Location;
import lombok.Getter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static File atmFile = new File("src/main/java/hvt/proekt/model/bootstrap/db/atm.csv");
    public static File bankFile = new File("src/main/java/hvt/proekt/model/bootstrap/db/bank.csv");;
    public static File exchangeFile = new File("src/main/java/hvt/proekt/model/bootstrap/db/exchange.csv");;

}
