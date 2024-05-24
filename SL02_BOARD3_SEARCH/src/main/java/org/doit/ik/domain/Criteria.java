package org.doit.ik.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// ����¡ ���� + �˻� ����
@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum; // ���� ������ ��ȣ
	
	private int amount ; // ���������� ����� �Խñ� ��

	private String type; // �˻�����
	private String keyword; // �˻���
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// pageNum=2&amount=10&type=T&keyword=ȫ�浿(QueryString) �ڵ����� ������ִ� Ŭ����������
	// org.springframework.web.util.UriComponentsBuilder;
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
		return builder.toUriString();
		
	}//getListLink

	// �˻����� "TWC" => new String [] { "T", "W", "C" }
	public String [] getTypeArr() {
		return type ==null? new String []{} :  this.type.split("");
		
	}
	
	
	
	
	
}//class
