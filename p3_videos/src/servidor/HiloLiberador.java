package servidor;

import java.util.Iterator;
/*****************************************
*
* Alumno: jennifer.ruan@alumnos.upm.es
*
****************************************/
public class HiloLiberador implements Runnable{

	final private int lInferior, lSuperior;
	final CacheTrabajosActivos cache;

	public HiloLiberador(int lInferior, int lSuperior, CacheTrabajosActivos cache) {
		this.lInferior = lInferior;
		this.lSuperior = lSuperior; 
		this.cache = cache;
		//list = new ArrayList<Trabajo>(lSuperior);
	}

	@Override
	public void run() { 
		while(true) {	
			if(cache.numTrabajos() >= lSuperior){
				do{
					//list = cache.getTrabajos();
					Iterator<Trabajo> iterator = cache.getTrabajos().iterator();
					while (iterator.hasNext()) {
						Trabajo t = iterator.next();
						if (t.getTranscodificado()!=null && t.getContador() <= 0) {
							if(cache.take(t))
								System.out.println("Video quitado: " + t.getVideo().getNombre() + "; Queda en cache: " + cache.numTrabajos());
						}
					}
					//cache.take();

				}while(cache.numTrabajos() >= lInferior);
				System.out.println("Queda en cache: " + cache.numTrabajos());
			}
		}
	}
}