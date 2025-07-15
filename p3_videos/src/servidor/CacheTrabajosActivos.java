package servidor;


import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import ssoo.videos.Numerable;
import ssoo.videos.Video;
/*****************************************
*
* Alumno: jennifer.ruan@alumnos.upm.es
*
****************************************/
public class CacheTrabajosActivos implements Numerable{

	final private ConcurrentMap<String,Trabajo> cache;

	public CacheTrabajosActivos(int tamano) {
		cache = new ConcurrentHashMap<String,Trabajo>(tamano);
	}

	@Override
	public int numTrabajos() {
		return cache.size();
	}
	
	public Trabajo put(Trabajo t){
		return cache.putIfAbsent(t.getVideo().getNombre(),t);
	}
	
	public boolean take (Trabajo t) {
		
    	return cache.remove(t.getVideo().getNombre(),t);		
    	//cache.remove(t.getTranscodificado().getNombre());
	}
	
	public Trabajo getTrabajo(Video v) {
		return cache.get(v.getNombre());
	}
	
	//devuelve la lista de trabajos en cache
	public ArrayList<Trabajo> getTrabajos() {
		return new ArrayList<Trabajo> (cache.values());
	}
}