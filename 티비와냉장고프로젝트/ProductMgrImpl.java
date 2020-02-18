import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductMgrImpl implements IProductMgr {
	List<Product> plist = new ArrayList<>();

	public ProductMgrImpl() {
		super();
	}

	@Override
	public void addProduct(Product p) {
		for (Product tp : plist) {
			if (p.pid == tp.pid) {
				throw new DuplicateException(p.pid);
			}
		}
		plist.add(p);
	}

	@Override
	public List<Product> searchProduct() {
		return plist;
	}
	
	@Override
	public Product searchPid(int pid) {
		for (Product p : plist) {
			if (p.pid == pid) {
				return p;
			}
		}
		throw new CodeNotFoundException(pid);
	}

	@Override
	public List<Product> searchPname(String pname) {
		List<Product> tp = new ArrayList<>();
		for (Product p : plist) {
			if (p.pname.indexOf(pname) >= 0) {
				tp.add(p);
			}
		}
		return tp;
	}

	@Override
	public List<Product> searchTV() {
		List<Product> tp = new ArrayList<>();
		for (Product p : plist) {
			if (p instanceof TV) {
				tp.add(p);
			}
		}
		
		return tp;
	}

	@Override
	public List<Product> searchRef() {
		List<Product> tp = new ArrayList<>();
		for (Product p : plist) {
			if (p instanceof Refrigerator) {
				tp.add(p);
			}
		}
		
		return tp;
	}

	@Override
	public List<Product> searchRefVolume(int volume) {
		List<Product> tp = new ArrayList<>();
		for (Product p : plist) {
			if (p instanceof Refrigerator) {
				if (((Refrigerator) p).getVolume() >= volume) {
					tp.add(p);
				}
			}
		}
		if(tp.isEmpty()) {
			throw new CodeNotFoundException();
		}
		return tp;
	}

	@Override
	public List<Product> searchTVInch(int inch) {
		List<Product> tp = new ArrayList<>();
		for (Product p : plist) {
			if (p instanceof TV) {
				if (((TV) p).getInch() >= inch) {
					tp.add(p);
				}
			}
		}
		if(tp.isEmpty()) {
			throw new CodeNotFoundException();
		}
		return tp;
	}

	@Override
	public void updatePrice(int pid, int price) {
		for (Product p : plist) {
			if (p.pid == pid) {
				p.price = price;
			}
		}
	}

	@Override
	public void deleteProduct(int pid) {
		for (Product p : plist) {
			if (p.pid == pid) {
				plist.remove(p);
				return;
			}
		}
	}

	@Override
	public int priceAll() {
		int priceSum = 0;
		for(Product p : plist) {
			priceSum += p.price * p.amount;
		}
		return priceSum;
	}

	@Override
	public void open() {
		try {
			ObjectInput oi = new ObjectInputStream(new FileInputStream("product.dat"));
			plist = (ArrayList<Product>)oi.readObject();
			oi.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			ObjectOutput oo = new ObjectOutputStream(new FileOutputStream("product.dat"));
			oo.writeObject(plist);
			oo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
