package com.ef.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ef.entity.Ticket;
import com.ef.repository.TicketRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Test
	public void testCreateNew() {
		
		ticketRepository.deleteAll();
		
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 0));
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 1));
		
		Ticket newTicket = ticketService.createNew();
		Assert.assertEquals(2, newTicket.getQueueNumber());
		Assert.assertEquals(3, ticketRepository.count());
	}
	
	@Test
	public void testDeleteActual() {
		
		ticketRepository.deleteAll();
		
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 0));
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 1));
		
		ticketService.deleteActual();
		
		Assert.assertEquals(1, ticketRepository.count());
		Assert.assertEquals(0, ticketRepository.findAll().get(0).getQueueNumber());
	}

}
