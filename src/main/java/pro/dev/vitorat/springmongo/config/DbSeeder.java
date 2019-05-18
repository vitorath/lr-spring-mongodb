package pro.dev.vitorat.springmongo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pro.dev.vitorat.springmongo.documents.Address;
import pro.dev.vitorat.springmongo.documents.Hotel;
import pro.dev.vitorat.springmongo.documents.Review;
import pro.dev.vitorat.springmongo.repositories.HotelRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbSeeder implements CommandLineRunner {

    private final HotelRepository hotelRepository;

    @Override
    public void run(String... args) throws Exception {
        Address marriotAddress = new Address("Paris", "France");
        List<Review> marriotReviews = Arrays.asList(
                new Review("John", 8, false),
                new Review("Mary", 7, true)
        );
        Hotel marriot = new Hotel("Marriot", 130, marriotAddress, marriotReviews);

        Address ibisAddress = new Address("Bucharest", "Romania");
        List<Review> ibisReviews = Arrays.asList(
                new Review("Teddy", 9, true)
        );
        Hotel ibis = new Hotel("Ibis", 90, ibisAddress, ibisReviews);

        Address sofitelAddress = new Address("Roma", "Italy");
        List<Review> sofitelReviews = new ArrayList<>();
        Hotel sofitel = new Hotel("Sofitel", 200, sofitelAddress, sofitelReviews);

        this.hotelRepository.deleteAll();

        List<Hotel> hotels = Arrays.asList(marriot, sofitel, ibis);
        this.hotelRepository.saveAll(hotels);

    }

}
