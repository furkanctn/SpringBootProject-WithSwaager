package JavaKodlama.northwind.Core.utilities.results;

public class ErrorResult extends result{
	public ErrorResult() {
		super(false);
	}
	public ErrorResult(String message) {
		super(false,message);
	}
}


