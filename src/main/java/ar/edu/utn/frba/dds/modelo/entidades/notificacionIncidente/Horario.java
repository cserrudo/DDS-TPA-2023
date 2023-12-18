package ar.edu.utn.frba.dds.modelo.entidades.notificacionIncidente;

import ar.edu.utn.frba.dds.modelo.entidades.Persistente;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table(name = "horario")
public class Horario extends Persistente {
		@Enumerated(EnumType.STRING)
		@Column(name = "dia")
    private DayOfWeek dia;
		@Column(name = "hora")
    private String hora;

    public Horario(DayOfWeek dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }

	public Horario() {

	}

	public DayOfWeek getDia() {
		return dia;
	}
	
	public void setDia(DayOfWeek dia) {
		this.dia = dia;
	}
	
	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
}
