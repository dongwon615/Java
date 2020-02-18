
public class CodeNotFoundException extends RuntimeException {
	public CodeNotFoundException() {
		super("해당 조건으로 검색된 상품이 존재하지 않습니다.");
	}
	public CodeNotFoundException(int msg) {
		super(msg + "상품이 존재하지 않습니다.");
	}
}
