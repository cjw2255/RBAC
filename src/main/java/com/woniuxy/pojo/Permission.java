package com.woniuxy.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
	private Integer pid;
	private String name;
	private Integer parentid;
	private String url;
	private Boolean open;
	private Boolean checked;
	private List<Permission> children = new ArrayList<Permission>();
}
