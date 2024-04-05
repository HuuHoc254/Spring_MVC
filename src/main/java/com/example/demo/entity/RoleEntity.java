package com.example.demo.entity;

public class RoleEntity {
    private Integer roleId;
    private String roleName;

    public RoleEntity(Integer roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public RoleEntity() {
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    
    
}