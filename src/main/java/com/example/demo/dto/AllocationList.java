package com.example.demo.dto;

import java.util.List;

public class AllocationList {
	private List<Allocation> allocaties;

	public AllocationList() {
		super();
	}

	public AllocationList(List<Allocation> allocaties) {
		super();
		this.allocaties = allocaties;
	}

	public List<Allocation> getAllocaties() {
		return allocaties;
	}

	public void setAllocaties(List<Allocation> allocaties) {
		this.allocaties = allocaties;
	}

}
