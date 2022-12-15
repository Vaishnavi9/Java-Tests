package foreignkeyConcept;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // entity collections
        Set<Country> countries = Collections.singleton(new Country(1, "America"));
        Set<State> states = Collections.singleton(new State(30, 1, "Wasington"));
        Set<City> cities = Collections.singleton(new City(500, 30, "Wasington, D.C."));

// intermediate maps
        Map<Integer, City> fkCityMap = cities.stream()
                .collect(Collectors.toMap(City::getStateId, Function.identity()));
        Map<Integer, State> fkStateMap = states.stream()
                .collect(Collectors.toMap(State::getCountryId, Function.identity()));
        Map<Integer, Map<State, City>> fkStateCityMap = fkStateMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> Collections.singletonMap(
                        entry.getValue(), fkCityMap.get(entry.getValue().getStateId()))));

        // result
        Map<Country, Map<State, City>> mapWhatIWant = countries.stream()
                .collect(Collectors.toMap(Function.identity(),
                        country -> fkStateCityMap.get(country.getCountryId())));
        System.out.println(mapWhatIWant);
    }

}

class Country {
    private final Integer countryId; // PK, primary key
    private final String name;

    public Country(Integer countryId, String name) {
        this.countryId = countryId;
        this.name = name;
    }
    // omitting getters

    public Integer getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }
}

class State {
    private final Integer stateId;   // PK
    private final Integer countryId; // FK, foreign key
    private final String name;

    public State(Integer stateId, Integer countryId, String name) {
        this.stateId = stateId;
        this.countryId = countryId;
        this.name = name;
    }

    public Integer getStateId() {
        return stateId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }
}

class City {
    private final Integer cityId;    // PK
    private final Integer stateId;   // FK
    private final String name;

    public City(Integer cityId, Integer stateId, String name) {
        this.cityId = cityId;
        this.stateId = stateId;
        this.name = name;
    }
    // omitting getters

    public Integer getCityId() {
        return cityId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public String getName() {
        return name;
    }
}