package com.ef.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ef.entity.Ticket;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketRepositoryTest {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Test
	public void testFindActual() {
		
		ticketRepository.deleteAll();
		
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 1));
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 0));
		
		Assert.assertEquals(2, ticketRepository.count());
		
		Ticket testTicket = ticketRepository.findActual();
		
		Assert.assertEquals(0, testTicket.getQueueNumber());
	}
	
	@Test
	public void testFindMaxQueueNumber() {
		
		ticketRepository.deleteAll();
		
		Assert.assertFalse(ticketRepository.findMaxQueueNumber().isPresent());
		
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 1));
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 2));
		
		Optional<Long> result = ticketRepository.findMaxQueueNumber();
		
		Assert.assertTrue(result.isPresent());
		Assert.assertEquals(2L, result.get().longValue());
	}

	//@Test
	public void testDeleteActual() {
		
		ticketRepository.deleteAll();
		
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 1));
		ticketRepository.save(new Ticket(0, LocalDateTime.now(), 0));
		
		Assert.assertEquals(2, ticketRepository.count());
		
		Ticket testTicket = ticketRepository.findActual();
		ticketRepository.delete(testTicket);
		
		Assert.assertEquals(1, ticketRepository.count());
		
		Iterator<Ticket> allTickets = ticketRepository.findAll().iterator();
		
		Assert.assertEquals(1, allTickets.next().getQueueNumber());
		Assert.assertFalse(allTickets.hasNext());
	}

}
