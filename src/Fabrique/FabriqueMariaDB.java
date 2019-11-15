package Fabrique;

public class FabriqueMariaDB extends FabriqueDB{

	@Override
	public String getDate() {
		return "str_to_date(?, '%Y-%m-%d')";
	}

}
