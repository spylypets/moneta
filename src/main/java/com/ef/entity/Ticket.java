package com.ef.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TICKETS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	
	@Id
	@SequenceGenerator(name = "CollectionItems_Generator", sequenceName = "Collection_Items_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CollectionItems_Generator")
	long id;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	LocalDateTime timestamp;
	
	@Column(name = "QUEUE_NUMBER")
	long queueNumber;

}
