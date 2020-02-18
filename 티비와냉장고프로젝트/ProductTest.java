
public class ProductTest {
	public static void main(String[] args) {
		
		ProductMgrImpl pmgr = new ProductMgrImpl();
		pmgr.open();
//		pmgr.addProduct(new TV(10200141, "sTV", 200, 10, 65));
//		pmgr.addProduct(new TV(20200110, "lTV", 100, 50, 35));
//		pmgr.addProduct(new Refrigerator(50165421, "sREF", 70, 30, 800));
//		pmgr.addProduct(new Refrigerator(90628962, "jRR", 100, 10, 200));
//		pmgr.addProduct(new Refrigerator(88422614, "kRE", 50, 15, 300));
//		pmgr.addProduct(new TV(14360113, "TTVV", 80, 20, 32));
//		pmgr.addProduct(new TV(16212017, "Stv", 130, 5, 50));
		System.out.println("------상품정보 전체 검색---------");
		for(Product p : pmgr.searchProduct()){
			System.out.println(p);
		}
		System.out.println("------상품번호 14360113인것---------");
		System.out.println(pmgr.searchPid(14360113));
		
		System.out.println("------상품명에 TV 들어간것---------");
		for(Product p : pmgr.searchPname("TV")){
			System.out.println(p);
		}
		
		System.out.println("------ TV정보만 검색---------");
		for(Product p : pmgr.searchTV()){
			System.out.println(p);
		}
		
		System.out.println("------ Refrigerator정보만 검색---------");
		for(Product p : pmgr.searchRef()){
			System.out.println(p);
		}
		
		System.out.println("------ volume이 400 이상의 Refrigerator정보만 검색---------");
		for(Product p : pmgr.searchRefVolume(400)){
			System.out.println(p);
		}

		System.out.println("------ inch가  50 이상의 Refrigerator정보만 검색---------");
		for(Product p : pmgr.searchTVInch(50)){
			System.out.println(p);
		}
		
		
		System.out.println("------상품번호 14360113인것 가격 변경---------");
		System.out.println(pmgr.searchPid(14360113));
		pmgr.updatePrice(14360113, 50);
		System.out.println(pmgr.searchPid(14360113));
		
		
		System.out.println("------상품번호 50165421인것 삭제---------");		
		pmgr.deleteProduct(50165421);
		for(Product p : pmgr.searchProduct()){
			System.out.println(p);
		}
		
		System.out.println("------전체 재고 상품금액 합계---------");		
		System.out.println(pmgr.priceAll() + "만원");
		pmgr.close();
	}
}
