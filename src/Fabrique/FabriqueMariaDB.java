package Fabrique;

public class FabriqueMariaDB extends FabriqueDB{

	@Override
	public String getDate() {
		return "to_char_date(?,%y%m%d)";
	}

}
