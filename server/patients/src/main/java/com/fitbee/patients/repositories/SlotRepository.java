package com.fitbee.patients.repositories;

import com.fitbee.patients.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {
    Slot findByDateAndFromTime(Date date, Date fromTime);
}
