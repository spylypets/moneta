package com.ef.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ef.entity.Ticket;
import com.ef.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	@GetMapping("actual")
	@ResponseBody
	public Ticket getActual() {
		
		return ticketService.getActual();
	}
	
	@GetMapping("new")
	@ResponseBody 
	public Ticket createNew() {
		
		return ticketService.createNew();
	}
	
	@GetMapping("delete")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void delete() {
		
		ticketService.deleteActual();
	}

}
