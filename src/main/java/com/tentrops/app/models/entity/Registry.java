package com.tentrops.app.models.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="registries")
public class Registry implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	@NotEmpty	
	private String description;
	
	private String observation;
	
	@Column(name="date_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date date;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE) 
	private Date createAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "registry_id")
	private List<Activity> activities;
	
	public Registry() {
		this.activities = new ArrayList<Activity>();
	}
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (this.date != null)
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		this.setId(date.getTime());
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
	
	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}
	
	public Long getTotalEffort() {
		Long total = 0L;
		for(Activity act: this.activities) {
			total += act.calculateEffort();
		}
		return total;
	}
	
	public String getTotalDuration() {
		LocalTime total = LocalTime.of(0, 0, 0);
		for(Activity act: this.activities) {
			total = total.plusHours(act.getDuration().getHour());
			total = total.plusMinutes(act.getDuration().getMinute());
			total = total.plusSeconds(act.getDuration().getSecond());			
		}
		return total.toString();
	}
}
