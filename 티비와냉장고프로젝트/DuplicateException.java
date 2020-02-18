
public class DuplicateException extends RuntimeException {
	public DuplicateException(int msg) {
		 super(msg + "는 이미 존재하는 상품입니다.");
	}
}
