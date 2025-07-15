package servidor;

import java.io.IOException;

import ssoo.videos.servidor.Peticion;
import ssoo.videos.servidor.ReceptorPeticiones;
/*****************************************
*
* Alumno: jennifer.ruan@alumnos.upm.es
*
****************************************/
public class HiloPeticion implements Runnable{
	private ReceptorPeticiones receptor;
	private ColaTrabajo queue;
	private CacheTrabajosActivos cache;
	public HiloPeticion(ColaTrabajo cola, CacheTrabajosActivos cache) throws IOException {
		this.receptor = new ReceptorPeticiones();
		this.queue = cola;
		this.cache = cache;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Peticion peticion = receptor.recibirPeticion();
				final Thread hEncargo = new Thread (new HiloEncargo<Object>(peticion.getCliente(),peticion.getEncargo(), queue, cache));
				hEncargo.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
