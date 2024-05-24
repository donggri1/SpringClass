package org.doit.ik.di4;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class RecordViewImpl4 implements RecordView4{
	

	@Inject
	@Named(value = "record1")
	private RecordImpl4 record = null;
	
	public RecordViewImpl4() {
	}
	
	// 1) 생성자를 통해 주입받는 코드
	public RecordViewImpl4(RecordImpl4  record) {
		this.record= record;
	}
	// 2) setter @Data 벌써 생성 되어있음
	
	
		
	
	
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
