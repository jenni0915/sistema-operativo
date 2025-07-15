package servidor;

import java.io.IOException;

import ssoo.videos.PanelVisualizador;
/*****************************************
*
* Alumno: jennifer.ruan@alumnos.upm.es
*
****************************************/
public class ServidorVideos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final ColaTrabajo cola = new ColaTrabajo(20); //tamano incial
		final CacheTrabajosActivos cache = new CacheTrabajosActivos(10); //tamano inicial
		final int nProcesos = Runtime.getRuntime().availableProcessors();
		try {
			final Thread hPeticion = new Thread(new HiloPeticion(cola,cache));
			final Thread hLiberador = new Thread(new HiloLiberador(25,50,cache));
			System.out.println("El servidor atendiendo...");		
			//System.out.println("Número de procesos: " + nProcesos);
			for(int j=0; j<nProcesos;j++) {
				final Thread hTranscodificador = new Thread(new HiloTranscodificador(cola));
				hTranscodificador.start();;
			}
			
			hPeticion.start();
			hLiberador.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PanelVisualizador.getPanel().registrarColaPeticiones(cola);
		PanelVisualizador.getPanel().registrarCache(cache);
	}

}
