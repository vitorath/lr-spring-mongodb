package pro.dev.vitorat.springmongo.controllers;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.dev.vitorat.springmongo.documents.Hotel;
import pro.dev.vitorat.springmongo.documents.QHotel;
import pro.dev.vitorat.springmongo.repositories.HotelRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/hotels")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HotelController {

    private final HotelRepository hotelRepository;

    @GetMapping("/all")
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @PostMapping
    public void insert(@RequestBody Hotel hotel) {
        hotelRepository.insert(hotel);
    }

    @PutMapping
    public void update(@RequestBody Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        hotelRepository.deleteById(id);
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getByPricePerNight(@PathVariable("maxPrice") Integer maxPrice) {
        return hotelRepository.findByPricePerNightLessThan(maxPrice);
    }

    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city) {
        return hotelRepository.findByCity(city);
    }

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable("country") String country) {
        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterByCountry =qHotel.address.country.eq(country);
        return (List<Hotel>) hotelRepository.findAll(filterByCountry);
    }

    @GetMapping("/recommended")
    public List<Hotel> getRecommended() {
        final int maxPrice = 100;
        final int minRatirng = 7;
        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRatirng);
        return (List<Hotel>) hotelRepository.findAll(filterByPrice.and(filterByRating));
    }

}
