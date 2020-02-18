public class TV extends Product{
	private int inch;
	
	public TV(int pid, String pname, int price, int amount, int inch) {
		super(pid, pname, price, amount);
		this.inch = inch;
	}

	

	public int getInch() {
		return inch;
	}



	public void setInch(int inch) {
		this.inch = inch;
	}



	public String toString() {
		return String.format("제품 번호 : %08d\t"
				+ "제품 이름 : %s\t"
				+ "가격 : %d 원\t"
				+ "수량 : %d 개\t"
				+ "인치 : %d inch\n" , pid, pname, price, amount, inch);
	}
}
