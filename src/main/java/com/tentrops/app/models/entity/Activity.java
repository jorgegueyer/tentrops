package com.tentrops.app.models.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="activities")
public class Activity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	
	private LocalTime duration;	
	
	private Double distance;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sport_id")
	private Sport sport;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getDuration() {
		return duration;
	}

	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}
	
	//TODO: It must Implement successfully
	public Long calculateEffort() {
		return (Long)(this.getDurationMinutes() * this.sport.getKcal());
	}
	
	public String getDurationRepresentation() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		return this.duration.format(dtf);
	}
	
	public Long getDurationMinutes() {
		Long minutes = (long) this.duration.getMinute();
		minutes += this.duration.getHour() * 60;
		return minutes;
	}
}
