public class Refrigerator extends Product {

	private int volume;
	
	public Refrigerator(int pid, String pname, int price, int amount, int volume){
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.amount = amount;
		this.volume = volume;
	}
	

	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}


	public String toString() {
		return String.format("제품 번호 : %08d\t"
				+ "제품 이름 : %s\t"
				+ "가격 : %d 원\t"
				+ "수량 : %d 개\t"
				+ "용량 : %d L\n", pid, pname, price, amount, volume);
	}
}
