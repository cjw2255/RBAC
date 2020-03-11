package com.woniuxy.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean <T>{
	private Integer totalCount;//������
	private Integer currentPage;//��ǰҳ
	private Integer pageSize;//ÿҳ��ʾ����Ŀ��
	private Integer pages;//��ҳ��
	private List<T> list;//ÿҳ��ʾ������
	private String queryVal;
}
