import java.io.Serializable;

public class Product implements Serializable {
	protected int pid;
	protected String pname;
	protected int price;
	protected int amount;
	
	public Product() {
		
	}
	
	public Product(int pid, String pname, int price, int amount) {
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.amount = amount;
	}
}
