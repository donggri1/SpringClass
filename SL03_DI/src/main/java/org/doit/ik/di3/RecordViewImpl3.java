package org.doit.ik.di3;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;

import lombok.Data;

@Data
public class RecordViewImpl3 implements RecordView3{
	
	//���� ��ü �ܺο��� ���� 1) ������ 2) setter 
	//@Autowired(required = false)
	//@ImportResource(name="record1") java 9 �̻�  X
	@Inject
	@Named(value = "record1")
	private RecordImpl3 record = null;
	
	
	
	
	public RecordViewImpl3() {
	}
	
	// 1) �����ڸ� ���� ���Թ޴� �ڵ�
	public RecordViewImpl3(RecordImpl3  record) {
		this.record= record;
	}
	// 2) setter @Data ���� ���� �Ǿ�����
	
	
		
	
	
	@Override
	public void input() {
		try(Scanner scanner = new Scanner(System.in);) {
			
			System.out.println("> kor,eng,mat input ? ");
			int kor = scanner.nextInt();
			int eng= scanner.nextInt();
			int mat = scanner.nextInt();
			
			int tot = kor+eng+mat;
			
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//input

	@Override
	public void output() {
		
		System.out.printf("> kor = %d, eng = %d, mat = %d, tot = %d , avg = %.2f \n",
							this.record.getKor()
							,this.record.getEng()
							,this.record.getMat()
							,this.record.total()
							,this.record.avg());
		
		
	}//output

}
