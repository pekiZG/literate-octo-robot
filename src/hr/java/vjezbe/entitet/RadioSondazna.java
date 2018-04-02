package hr.java.vjezbe.entitet;

public interface RadioSondazna {
	
	public void podesiVisinuPostaje(Integer visina);
	
	public Integer dohvatiVisinuPostaje();
	
	public static Integer provjeriVisinu(Integer visina) {
		if (visina > 1000) {
			visina = 1000;
		}
		return visina;
	}
	
	default public Integer povecajVisinu(Integer visina) {
		Integer provjerenaVisina = provjeriVisinu(visina);
		return provjerenaVisina + 1;
	}
	
}
