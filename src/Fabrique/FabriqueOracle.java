package Fabrique;

public class FabriqueOracle extends FabriqueDB{

	@Override
	public String getDate() {
		return "to_date(?, 'YYYY-MM-DD')";
	}

}
