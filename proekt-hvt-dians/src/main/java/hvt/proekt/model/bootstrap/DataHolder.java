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
    public static File atmFile = new File("C:\\Users\\Hristijan\\IdeaProjects\\proekt-hvt-dians\\src\\main\\java\\hvt\\proekt\\model\\bootstrap\\db\\atm.csv");
    public static File bankFile = new File("C:\\Users\\Hristijan\\IdeaProjects\\proekt-hvt-dians\\src\\main\\java\\hvt\\proekt\\model\\bootstrap\\db\\bank.csv");;
    public static File exchangeFile = new File("C:\\Users\\Hristijan\\IdeaProjects\\proekt-hvt-dians\\src\\main\\java\\hvt\\proekt\\model\\bootstrap\\db\\exchange.csv");;

//    @PostConstruct
//    public void init(){
//        services.add(new MoneyObject( 0L, Type.ATM, new Location(41.9936657, 21.4428736), "НБРМ"));
//        services.add(new MoneyObject( 1L, Type.ATM, new Location(41.9921343,21.4266335), "Шпаркасе"));
//        services.add(new MoneyObject( 2L, Type.BANK, new Location(41.9936657,21.4428736), "НБРМ"));
//        services.add(new MoneyObject( 3L, Type.BANK, new Location(41.9881468,21.4552843), "Стопанска банка"));
//        services.add(new MoneyObject( 4L, Type.EXCHANGE, new Location(41.9969234,21.4383163), "Exchange 1"));
//        services.add(new MoneyObject( 5L, Type.EXCHANGE, new Location(41.9869314,21.4308515), "Exchange 2"));
//    }
}
