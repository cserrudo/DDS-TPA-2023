package ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LectorCsv {

	public List<EntidadPrestadora> obtenerEntidadesPrestadoresCSV(InputStream inputStream) {
		List<EntidadPrestadora> instituciones = new ArrayList<>();

		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
			String linea = buffer.readLine();
			while (linea != null) {
				instituciones.add(leerInstitucionesEntidadPrestadora(linea));
				linea = buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return instituciones;
	}

	public List<OrganismoDeControl> obtenerOrganismoDeControlCSV(InputStream inputStream) {
		List<OrganismoDeControl> instituciones = new ArrayList<>();

		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
			String linea = buffer.readLine();
			while (linea != null) {
				instituciones.add(leerInstitucionesOrganismoDeControl(linea));
				linea = buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instituciones;
	}

	private EntidadPrestadora leerInstitucionesEntidadPrestadora(String linea) {
		String[] data = linea.split(",");
		return new EntidadPrestadora(data[0], data[1]);
	}

	private OrganismoDeControl leerInstitucionesOrganismoDeControl(String linea) {
		String[] data = linea.split(",");
		return new OrganismoDeControl(data[1]);
	}
}
