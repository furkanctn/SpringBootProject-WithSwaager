package JavaKodlama.northwind.Core.utilities.results;

public class SuccessResult extends result{
	public SuccessResult() {
		super(true);
	}
	public SuccessResult(String message) {
		super(true,message);
	}
}
