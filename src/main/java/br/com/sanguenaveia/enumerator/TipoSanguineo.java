package br.com.sanguenaveia.enumerator;

import java.util.ArrayList;
import java.util.List;

public enum TipoSanguineo {
	A_POSITIVO("A+", "A+ e AB+", "A+, A-, O+ e O-"),
    B_POSITIVO("B+", "B+ e AB+", "B+, B-, O+ e O-"),
    AB_POSITIVO("AB+", "AB+", "A+, A-, B+, B-, AB+, AB-, O+ e O-"),
    O_POSITIVO("O+", "A+, B+, AB+ e O+", "O+ e O-"),
    A_NEGATIVO("A-", "A+, A-, AB+ e AB-", "A- e O-"),
    B_NEGATIVO("B-", "B+, B-, AB+ e AB-", "B- e O-"),
    AB_NEGATIVO("AB-", "AB+ e AB-", "A-, B-, AB- e O-"),
    O_NEGATIVO("O-", "A+, A-, B+, B-, AB+, AB-, O+ e O-", "O-");

	private String tipoSanguineo;
	private String doaPara;
	private String recebeDe;

	private TipoSanguineo() {}
    
    private TipoSanguineo(String tipoSanguineo, String doaPara, String recebeDe) {
        this.tipoSanguineo = tipoSanguineo;
        this.doaPara = doaPara;
        this.recebeDe = recebeDe;
    }
    
    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public String getDoaPara() {
        return doaPara;
    }

    public String getRecebeDe() {
        return recebeDe;
    }
    
    public static List<TipoSanguineo> getParaQualTipoDoa(TipoSanguineo tipoSanguineo) {
        List<TipoSanguineo> lista = new ArrayList<>();
        String tiposSanguineos = tipoSanguineo.getDoaPara().replaceAll("e", ",");
        String tipos[] = tiposSanguineos.split(",");
        for(String tipo : tipos) {
            lista.add(getTipoSanguineo(tipo.trim()));
        }
        return lista;
    }
    
    public static List<TipoSanguineo> getDeQualTipoRecebe(TipoSanguineo tipoSanguineo) {
        List<TipoSanguineo> lista = new ArrayList<>();
        String tiposSanguineos = tipoSanguineo.getRecebeDe().replaceAll("e", ",");
        String tipos[] = tiposSanguineos.split(",");
        for(String tipo : tipos) {
            lista.add(getTipoSanguineo(tipo.trim()));
        }
        return lista;
    }
    
    private static TipoSanguineo getTipoSanguineo(String tipoSanguineo) {
        for(TipoSanguineo ts : TipoSanguineo.values()) {
            if(ts.getTipoSanguineo().equalsIgnoreCase(tipoSanguineo)) {
                return ts;
            }
        }
        return null;
    }
}
