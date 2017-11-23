package com.stackroute.gitsearch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GitRepo {

	@Id
	private String id;

	private String fullName;

	private String language;

	private String forkCount;

	private String homePage;

	private String htmlUrl;

	private String watchersCount;

	private String description;

	private String avatarUrl;

	@Column
	private String userId;

	@ManyToOne
	@JoinColumn(name = "userId", updatable = false, insertable = false)
	private User user;

	public GitRepo() {

	}

	public GitRepo(String id, String fullName, String language, String forkCount, String homePage, String htmlUrl,
			String watchersCount, String description, String avatarUrl, String userId) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.language = language;
		this.forkCount = forkCount;
		this.homePage = homePage;
		this.htmlUrl = htmlUrl;
		this.watchersCount = watchersCount;
		this.description = description;
		this.avatarUrl = avatarUrl;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getForkCount() {
		return forkCount;
	}

	public void setForkCount(String forkCount) {
		this.forkCount = forkCount;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getWatchersCount() {
		return watchersCount;
	}

	public void setWatchersCount(String watchersCount) {
		this.watchersCount = watchersCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
