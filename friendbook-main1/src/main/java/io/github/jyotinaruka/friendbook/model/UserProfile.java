package io.github.jyotinaruka.friendbook.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_profile")
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 10, max = 500)
	private String bio;
	@NotBlank
	private String currentCity;
	@NotBlank
	private String education;
	@Column(nullable = true)
	private String picFileName;

	@Column(updatable = false)
	private Date createdAt;
    private Date updatedAt;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;

	public UserProfile() {
		
	}
	
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getPicFileName() {
		return picFileName;
	}
	
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	
    @Transient
    public String getProfilePicPath() {
        if (picFileName == null || user == null) {
        	return "/img/avatar.png";
        } else {
        	return "/userphotos/" + user.getId() + "/" + picFileName;
        }
    }

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
