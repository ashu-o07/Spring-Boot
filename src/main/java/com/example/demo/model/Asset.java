package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long asset_id;

	private String name;
	private String date;
	private String notes;

	@Enumerated(EnumType.STRING)
	private AssetStatus status;

	@ManyToOne
	private Category category;

	@ManyToOne
	private Employee assigned_to;

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Asset() {
		super();
		this.status = AssetStatus.Available;
		this.assigned_to = null;
	}

	public Asset(Long Id, String name, String date, String notes, Category category) {
		super();
		this.asset_id = Id;
		this.name = name;
		this.date = date;
		this.notes = notes;
		this.status = AssetStatus.Available;
		this.category = category;
		this.assigned_to = null;
	}

	public Employee getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(Employee assigned_to) {
		this.assigned_to = assigned_to;
	}

	public long getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(long asset_id) {
		this.asset_id = asset_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public AssetStatus getStatus() {
		return status;
	}

	public void setStatus(AssetStatus status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
