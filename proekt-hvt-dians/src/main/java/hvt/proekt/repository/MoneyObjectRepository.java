package hvt.proekt.repository;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.bootstrap.DataHolder;
import hvt.proekt.model.enumeration.Type;
import hvt.proekt.model.util.Location;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MoneyObjectRepository {

    private boolean cachedObjectsFlag = false;
    private List<MoneyObject> cachedObjects = new ArrayList<>();

    public List<MoneyObject> findAll() throws FileNotFoundException {
        if(cachedObjectsFlag)
            return cachedObjects;
        List<MoneyObject> objects = new ArrayList<>();

        Scanner scanner = new Scanner(DataHolder.atmFile);
        read(objects, scanner);
        scanner = new Scanner(DataHolder.bankFile);
        read(objects, scanner);
        scanner = new Scanner(DataHolder.exchangeFile);
        read(objects, scanner);

        cachedObjectsFlag = true;
        this.cachedObjects = objects;
        return objects;
    }

    private void read(List<MoneyObject> objects, Scanner scanner) {
        scanner.nextLine();
        while (scanner.hasNextLine()){
            String[] parts = scanner.nextLine().split(",");
            MoneyObject object = convert(parts);
            objects.add(object);
        }
    }

    private MoneyObject convert(String[] parts){
        Long id = Long.valueOf(parts[0]);
        String type  = parts[1];
        Type t;
        if(type.equals("atm"))
            t = Type.ATM;
        else if (type.equals("bank"))
            t = Type.BANK;
        else t = Type.EXCHANGE;
        double lat = Double.parseDouble(parts[2]);
        double lon = Double.parseDouble(parts[3]);
        String name = parts[4];

        Location loc = new Location(lat, lon);
        return new MoneyObject(id, t, loc, name);
    }

    public List<MoneyObject> init() throws FileNotFoundException {
        if(cachedObjectsFlag)
            return cachedObjects;
        return findAll();
    }

    public List<MoneyObject> findObjectsByType(Type type) throws FileNotFoundException {
        List<MoneyObject> objects = init();
        return objects.stream().filter(s-> s.getType().equals(type)).collect(Collectors.toList());
    }

    public Optional<MoneyObject> findObjectById(Long id) throws FileNotFoundException {
        List<MoneyObject> objects = init();
        return objects.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    public List<MoneyObject> findObjectByNameAndType(String name, String type) throws FileNotFoundException {
        List<MoneyObject> objects = init();
        return objects.stream().filter(s-> s.getName().toLowerCase().contains(name.toLowerCase()) && s.getType().toString().equals(type.toUpperCase()))
                .collect(Collectors.toList());
    }
    public void save(MoneyObject object)
    {
        Comparator<MoneyObject> comparator = Comparator.comparing(MoneyObject::getId);
        try {
            Long id = findAll().stream().max(comparator).get().getId();
            object.setId(id+1);
            File f = null;
            if(object.getType().equals(Type.ATM))
            {
                f = DataHolder.atmFile;
            }
            else if (object.getType().equals(Type.BANK)){
                f = DataHolder.bankFile;
            }
            else
            {
                f = DataHolder.exchangeFile;
            }

            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
            pw.println(object.toString());
            pw.flush();
            pw.close();
            cachedObjectsFlag=false;
            System.out.println("??????");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
