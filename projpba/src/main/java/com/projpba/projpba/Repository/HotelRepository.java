package com.projpba.projpba.Repository;

import com.projpba.projpba.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
