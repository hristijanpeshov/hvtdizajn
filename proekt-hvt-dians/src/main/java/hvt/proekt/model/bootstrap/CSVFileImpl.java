package hvt.proekt.model.bootstrap;

import hvt.proekt.model.MoneyObject;
import hvt.proekt.model.enumeration.Type;
import hvt.proekt.model.util.Location;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CSVFileImpl implements IFileDatabase {


   private final Type type;
   private final File file;

   private static DecimalFormat df = new DecimalFormat("#.#######",
            DecimalFormatSymbols.getInstance(Locale.US)); // formater za da gi zapisuva decimalite so tocka
                                                            // namesto so zapirka


    public CSVFileImpl(Type type, String fileLocation) {
        this.type = type;
        this.file = new File(fileLocation);
    }


    //gi cita objektite od baza
    //izlezni argumenti: lista so moneyObjects vo zavisnost od tipot na objektot
    @Override
    public List<MoneyObject> read() throws FileNotFoundException {
        List<MoneyObject> objects = new ArrayList<>();
        Scanner scanner = new Scanner(this.file);
        scanner.nextLine();
        while (scanner.hasNextLine()){
            String inputLine = scanner.nextLine();
            MoneyObject object = stringToObject(inputLine, type);
            objects.add(object);
        }

        scanner.close();
        return objects;
    }

    //sekoja linija od csv file-ot ja razdeluva na delovi po zapirka i potoa go sozdava moneyObject-ot
    //vlezni argumenti: linija od csv file
    //izlezen argument: konvertiran MoneyObject
    private MoneyObject stringToObject(String inputLine, Type type){
        String [] parts = inputLine.split(",");
        Long id = Long.valueOf(parts[0]);

        double lat = Double.parseDouble(parts[2]);
        double lon = Double.parseDouble(parts[3]);
        String name = parts[4];

        Location loc = new Location(lat, lon);
        return new MoneyObject(id, type, loc, name);
    }

    //zacuvuva lista od MoneyObjects vo file-ot, vo zvisnost od tipot na file
    //vlezni argumenti: objektite sto treba da se zacuvaat
    @Override
    public void save(List<MoneyObject> objects) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println("id,type,lat,lon,name");
        objects.stream()
                .filter(s -> s.getType().equals(type))
                .forEach(object -> printWriter.println(objectToString(object)));

        printWriter.flush();
        printWriter.close();
    }

    //konvertira MoneyObject vo string za da se zacuva vo csv file
    private String objectToString(MoneyObject moneyObject) {
        return String.format("%d,%s,%s,%s,%s", moneyObject.getId(), moneyObject.getType().toString().toLowerCase(),
                df.format(moneyObject.getCoordinates().getY()), df.format(moneyObject.getCoordinates().getX()),
                moneyObject.getName());
    }
}
