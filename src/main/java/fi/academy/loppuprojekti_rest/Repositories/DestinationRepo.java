package fi.academy.loppuprojekti_rest.Repositories;

import fi.academy.loppuprojekti_rest.Entities.Destination;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DestinationRepo extends CrudRepository <Destination, Integer> {
    Optional<Destination> findByName(String name);
    Iterable<Destination> findByNameContains(String name);

    @Query
    ("SELECT d FROM Destination d where d.country like concat ('%', :searchword, '%') " +
            "or d.city like concat('%', :searchword, '%') or d.name like concat('%', :searchword, '%')" +
            "or d.category like concat('%', :searchword, '%')")
    Iterable<Destination> findBySearchWord(@Param("searchword") String word);
}
