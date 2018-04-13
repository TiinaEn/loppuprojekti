package fi.academy.loppuprojekti_rest.Repositories;

import fi.academy.loppuprojekti_rest.Entities.Destination;
import fi.academy.loppuprojekti_rest.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DestinationRepo extends CrudRepository <Destination, Integer> {

    Optional<Destination> findByName(String name);

    Iterable<Destination> findByNameContains(String name);

    @Query
            ("SELECT d FROM Destination d where (d.country like concat ('%', :searchword, '%') " +
                    "or d.city like concat('%', :searchword, '%') or d.name like concat('%', :searchword, '%')" +
                    "or d.category like concat('%', :searchword, '%')) and d.user = :user")
    Iterable<Destination> findBySearchWord(@Param("searchword") String word, @Param("user") User user);

    @Query
            ("SELECT d.country FROM Destination d where d.user = :user")
    Iterable<Destination> findCountries(@Param("user") User user);

    @Query
            ("SELECT d.city FROM Destination d where d.country = :country and d.user = :user")
    Iterable<Destination> findCitiesByCountry(@Param("country") String country, @Param("user") User user);

    @Query
            ("SELECT d.name FROM Destination d where d.country = :country and d.city = :city and d.user = :user")
    Iterable<Destination> findDestinationsByCity(@Param("country") String country, @Param("city") String city, @Param("user") User user);

    @Query
            ("SELECT d FROM Destination d where d.country = :country and d.city = :city and d.name = :name and d.user = :user")
    Iterable<Destination> findDestination(@Param("country") String country, @Param("city") String city, @Param("name") String name, @Param("user") User user);

    @Query
            ("SELECT d FROM Destination d where d.user = :user")
    Iterable<Destination> findAllByUser(@Param("user") User user);

    @Query
            ("SELECT d FROM Destination d order by d.country asc")
    Iterable<Destination> findByCountry();


}

