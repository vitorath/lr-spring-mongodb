package pro.dev.vitorat.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Repository;
import pro.dev.vitorat.springmongo.documents.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String>, QuerydslPredicateExecutor<Hotel> {

    List<Hotel> findByPricePerNightLessThan(Integer maxPrice);

    @Query(value = "{'address.city':?0}")
    List<Hotel> findByCity(String city);

}
