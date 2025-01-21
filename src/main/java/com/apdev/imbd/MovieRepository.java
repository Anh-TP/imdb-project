//movies repository
package com.apdev.imbd;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findByPrimaryTitleContainingIgnoreCase(String primaryTitle);

}
