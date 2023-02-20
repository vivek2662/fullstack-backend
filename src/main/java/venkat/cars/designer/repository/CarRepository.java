package venkat.cars.designer.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import venkat.cars.designer.model.Cars;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long> {

}
