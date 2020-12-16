package hvt.proekt.model.bootstrap;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.enumeration.Type;
import hvt.proekt.model.util.Location;
import lombok.Getter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<MoneyObject> services = new ArrayList<>();

    @PostConstruct
    public void init(){
        services.add(new MoneyObject( 0L, Type.ATM, new Location(41.9936657, 21.4429736), "НБРМ"));
        services.add(new MoneyObject( 1L, Type.ATM, new Location(41.9921343,21.4266335), "Шпаркасе"));
        services.add(new MoneyObject( 2L, Type.BANK, new Location(41.9936657,21.4428736), "НБРМ"));
        services.add(new MoneyObject( 3L, Type.BANK, new Location(41.9881468,21.4552843), "Стопанска банка"));
        services.add(new MoneyObject( 4L, Type.EXCHANGE, new Location(41.9969234,21.4383163), "Exchange 1"));
        services.add(new MoneyObject( 5L, Type.EXCHANGE, new Location(41.9869314,21.4308515), "Exchange 2"));
    }
}
