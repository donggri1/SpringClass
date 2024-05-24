package org.doit.ik.di;

import java.util.Scanner;

import lombok.Data;

@Data
public class RecordViewImpl implements RecordView{
	
	//���� ��ü �ܺο��� ���� 1) ������ 2) setter 
	private RecordImpl record = null;
	//��������� �ƴ�
	//private RecordImpl record = new RecordImpl();
	
	public RecordViewImpl() {
	}
	
	// 1) �����ڸ� ���� ���Թ޴� �ڵ�
	public RecordViewImpl(RecordImpl  record) {
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
