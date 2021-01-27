package hvt.proekt.model;

import hvt.proekt.model.enumeration.Type;
import hvt.proekt.model.util.Location;
import lombok.Data;

@Data
public class MoneyObject {
    private Long id;
    private Type type;
    private Location coordinates;
    private String name;

    public MoneyObject(Long id, Type type, Location coordinates, String name) {
        this.id = id;
        this.type = type;
        this.coordinates = coordinates;
        this.name = name;
    }

    public MoneyObject(Type type, Location coordinates, String name) {
        this.type = type;
        this.coordinates = coordinates;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Location getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Location coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
