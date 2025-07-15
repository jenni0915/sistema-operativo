package servidor;

import ssoo.videos.Transcodificador;
import ssoo.videos.Video;
/*****************************************
*
* Alumno: jennifer.ruan@alumnos.upm.es
*
****************************************/
public class HiloTranscodificador implements Runnable {

	private ColaTrabajo queue;
	private final Transcodificador t;

	public HiloTranscodificador(ColaTrabajo cola) {
		queue = cola;
		t = new Transcodificador();
	}
	
	@Override
	public void run() {
		Trabajo trabajo;
		while(true) {
			trabajo = queue.take();
				Video v = t.transcodificar(trabajo.getVideo());
				if(v!=null) {
					trabajo.setTranscodificado(v); 
					trabajo.notificar();
					System.out.printf("Video transcodificado: "+v.getNombre()+"\n");
				}
		}
		
	}
	
}	
