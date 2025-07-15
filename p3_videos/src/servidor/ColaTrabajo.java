package servidor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import ssoo.videos.Numerable;
/*****************************************
*
* Alumno: jennifer.ruan@alumnos.upm.es
*
****************************************/
public class ColaTrabajo implements Numerable{
	
	final private BlockingQueue<Trabajo> queue;
	
	public ColaTrabajo (int tamaño) {
		queue = new ArrayBlockingQueue<Trabajo>(tamaño);
	}
	
	public void put(Trabajo v) {
		try {
			queue.put(v);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Trabajo take() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//
	public void setSize(int size) {
		
	}
	@Override
	public int numTrabajos() {
		return queue.size();
	}
	
}
