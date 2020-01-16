package com.ef.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.ef.entity.Ticket;
import com.ef.repository.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
	TicketRepository ticketRepository;
	
	
	public Ticket getActual() {
		
		return ticketRepository.findActual();
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public Ticket createNew() {
		
		Optional<Long> maxQueueNumber = ticketRepository.findMaxQueueNumber();
		return ticketRepository.save(new Ticket(0, LocalDateTime.now(), maxQueueNumber.isPresent() ? maxQueueNumber.get() + 1 : 0L));
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void deleteActual() {
		
		Ticket actualTicket = ticketRepository.findActual();
		
		if (actualTicket != null) {
			ticketRepository.delete(actualTicket);
		    List<Ticket> updatedTickets = ticketRepository.findAll().stream().map(t -> {
		    	t.setQueueNumber(t.getQueueNumber() - 1);
		    	return t;
		    }).collect(Collectors.toList());
		    ticketRepository.saveAll(updatedTickets);
		}
	}

}
