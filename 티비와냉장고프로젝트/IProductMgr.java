 import java.util.List;

public interface IProductMgr {
	void addProduct(Product p);
	List<Product> searchProduct();
	Product searchPid(int pid);
	List<Product> searchPname(String pname);
	List<Product> searchTV();
	List<Product> searchRef();
	List<Product> searchRefVolume(int volume);
	List<Product> searchTVInch(int inch);
	void updatePrice(int pid, int price);
	void deleteProduct(int pid);
	int priceAll();
	void open();
	void close();
}
