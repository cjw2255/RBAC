package com.woniuxy.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean <T>{
	private Integer totalCount;//总行数
	private Integer currentPage;//当前页
	private Integer pageSize;//每页显示的条目数
	private Integer pages;//总页数
	private List<T> list;//每页显示的数据
	private String queryVal;
}
