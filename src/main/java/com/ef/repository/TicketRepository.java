package com.ef.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ef.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	@Query("SELECT t FROM Ticket t WHERE t.queueNumber = 0")
	public Ticket findActual();
	
	@Query("SELECT MAX(t.queueNumber) FROM Ticket t")
	public Optional<Long> findMaxQueueNumber();

}
