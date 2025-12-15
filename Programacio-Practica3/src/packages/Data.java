package packages;

public class Data {
    private int dia;
    private int mes;
    private int any;

    /**
	 * Constructor per defecte, sense paràmetres inicialitza a una data de
	 * referència
	 */
	public Data() {
		this.dia = 1;
		this.mes = 1;
		this.any = 2000;
	}

    /**
	 * Constructor que rep la data per paràmetre
	 * Ha de validar que la data és correcta. Si rep una data incorrecta inicialitza la instància
	 * amb la data de referència.
	 * @param dia
	 * @param mes
	 * @param any
	 */
    public Data(int dia, int mes, int any) {
        if (esDataCorrecta(dia, mes, any)) { // ens asegurem que és una data valida
			this.dia = dia;
			this.mes = mes;
			this.any = any;
		} else { // posem la data de referència com a senyal d'error
			this.dia = 1;
			this.mes = 1;
			this.any = 2000;
		}
    }

	/**
	 * Mètode que calcula i retorna una instància amb el valor del dia següent
	 * @return la data del dia seguent
	 */
    public Data diaSeguent() {
        int dia = this.dia + 1;
        int mes = this.mes;
        int any = this.any;
        if (dia > diesMes(this.mes, this.any)) {
            dia = 1;
            mes = this.mes + 1;
            if (mes > 12) {
                mes = 1;
                any = this.any + 1;
            }
        }
		Data novaData = new Data(dia, mes, any);
		return novaData;
    }

	public Data setmanaSeguent(){
		int dia=this.dia+7;
		int mes=this.mes;
		int any=this.any;

        if (dia > diesMes(this.mes, this.any)) {
            dia=dia-diesMes(mes, any);
            mes = this.mes + 1;
            if (mes > 12) {
                mes = 1;
                any = this.any + 1;
            }
        }		
	Data novaData = new Data(dia, mes, any);
		return novaData;
	}

    /**
     * Mètode que retorna el resultat de comparar la data data amb una altra per paràmetre
     * @param data la data amb que comparar
     * @return boolea si es igual o no
     */
    public boolean esIgualA(Data data) {
        boolean esIgual = false;
        if (this.dia == data.dia && this.mes == data.mes && this.any == data.any) {
            esIgual = true;
        }
        return esIgual;
    }

	public int comparaAmb(Data data) {
		if (this.any != data.any) {
			return this.any - data.any;
		} else if (this.mes != data.mes) {
			return this.mes - data.mes;
		} else {
			return this.dia - data.dia;
		}
	}


	public boolean dataPassada(Data data){
		if(this.any<data.any) return false;
		else if(this.any>data.any) return true;

		if(this.mes<data.mes) return false;
		else if(this.mes>data.mes) return true;

		if(this.dia<=data.dia) return false;
		else return true;

	}

	/**
	 * Mètode que comprova si la data actual correspon a un any de traspas
	 * @return si és any de traspas
	 */
    public boolean esDataAnyDeTraspas() {
        return esAnyTraspas(any);
    }

	/**
	 * Mètode que transforma el contingut d'un objecte en una cadena de caracters per ser
	 * mostrat per pantalla
	 */
	public String toString() {
		return("\tDATA => dia "+dia+" mes "+mes+" any "+any);
	}

	/**
	 * Mètode que retorna una còpia de la instància actual
	 * @return la còpia de la data
	 */
	public Data copia() {
		Data novaData = new Data(this.dia, this.mes, this.any);
		return novaData;
	}

	//////////////////////////////////////////////////////////////////////////////////
	/// 
    // Mètodes de classe (STATIC).
	// no s'apliquen sobre el contingut d'una instància de data sinó sobre valors
	// que es reben per paràmetre.
	// són mètodes auxiliars i per això estan definits com a private dins la classe
	// no són accessibles des de la classe Aplicacio (App.java)

	private static boolean esDataCorrecta(int dia, int mes, int any) {
		boolean hoEs=true;
		if (dia < 1 || dia > 31) { // dia incorrecte
			hoEs= false;
		}
		else if (mes < 1 || mes > 12) { // mes incorrecte
			hoEs= false;
		}
		else if (dia > diesMes(mes, any)) { // dia del mes incorrecte
			hoEs= false;
		}
		return hoEs;
	}

	private static boolean esAnyTraspas(int any) { // ens estalviem crear una instancia de data
		if ((any % 4 == 0) && ((any % 100 != 0) || (any % 400 == 0))) {
			return true;
		} else {
			return false;
		}
	}

	private static int diesMes(int mes, int any) { // per saber quants dies te un mes d'un any
		int diesMes;
		if (mes == 2) {
			if (esAnyTraspas(any)) {
				diesMes = 29;
			} else {
				diesMes = 28;
			}
		} else {
			if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
				diesMes = 30;
			} else {
				diesMes = 31;
			}
		}
		return diesMes;
	}


}
