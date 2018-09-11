package com.guitar.db.repository.spring_data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Location;

public interface LocationDataJPARepository extends JpaRepository<Location, Long> {

	/* select * from Location where state like ? */
	List<Location> findByStateLike(String state);

	/* select * from Location where state like ? */
	List<Location> findByStateNotLike(String state);

	/* select * from Location loc where .state like ? */
	List<Location> findByStateStartingWith(String state);

	/* select * from Location loc where state=? or country=? */
	List<Location> findByStateOrCountry(String state, String country);

	/* select * from Location loc where state=? and country=? */
	List<Location> findByStateAndCountry(String state, String country);

	/* select * from Location loc where state<>? and country=? */
	List<Location> findByStateNotAndCountry(String state, String country);

	/* select * from Location loc where upper(loc.state) like upper(?) */
	List<Location> findByStateIgnoreCaseStartingWith(String state);

	/* select * from Location loc where loc.country like ? order by loc.state asc*/
	List<Location> findByCountryLikeOrderByStateAsc(String state);

	/* select * from Location loc where loc.state like ? limit ? */
	List<Location> findTop5ByStateLike(String state);

	/* select distinct * from Location loc where loc.state=? */
	Location findDistinctByState(String state);


}
