package JavaKodlama.northwind.Core.utilities.results;

public class DataResult<T> extends result {
	private T data;
	public DataResult(T data,boolean success, String message) {
		super(success, message);
		this.data=data;
				
	}
	public DataResult(T data,boolean success) {
		super(success);
		this.data=data;
				
	}
	public T getData() {
		return this.data;
				
	}
}
