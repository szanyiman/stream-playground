package countries;

import java.io.InputStream;
import java.io.IOException;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CountryRepository {

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    private List<Country> countries;

    public CountryRepository() {
        try {
            loadCountries(CountryRepository.class.getResourceAsStream("countries.json"));
        } catch(IOException e) {
            e.printStackTrace();
            throw new AssertionError("Failed to load resource countries.json", e); // Can't happen
        }
    }

    private void loadCountries(InputStream is) throws IOException {
        countries = MAPPER.readValue(is, new TypeReference<List<Country>>() {});
    }

    public List<Country> getAll() {
        return countries;
    }

}
