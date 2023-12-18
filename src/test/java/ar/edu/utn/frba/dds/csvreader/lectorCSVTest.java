package ar.edu.utn.frba.dds.csvreader;

import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.EntidadPrestadora;
import ar.edu.utn.frba.dds.modelo.entidades.importadorDeEntidades.LectorCsv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class lectorCSVTest {

    @Test
    public void testLectorCSVEntidadPrestadora() {
        String csv = "1,BancoPatagonia\n2,Prueba2";
        InputStream inputStream = new ByteArrayInputStream(csv.getBytes());

        List<EntidadPrestadora> instituciones = new LectorCsv().obtenerEntidadesPrestadoresCSV(inputStream);
        Assertions.assertEquals(instituciones.get(0).getNombre(), "BancoPatagonia");
        Assertions.assertEquals(instituciones.get(1).getNombre(), "Prueba2");
    }
}