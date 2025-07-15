package servidor;

import ssoo.videos.Video;
/*****************************************
*
* Alumno: jennifer.ruan@alumnos.upm.es
*
****************************************/
public class Trabajo {
	private Video vOrigi;
	private Video vTrans;
	private int contador;
	
	public Trabajo(Video video) {
		vOrigi = video;
		vTrans = null;
		contador = 1;
	}
	
	public Video getVideo() {
		return vOrigi;
	}
	
	public synchronized void esperar() throws InterruptedException {
		this.wait();
	}
	
	public synchronized void notificar() {
		this.notifyAll();
	}
	
	public void setTranscodificado(Video v) {
		vTrans = v;
	}
	
	public Video getTranscodificado(){
		return vTrans;
	}
	
	public  void repetido() { //si el video esta repetido
		contador++;
	}
	
	public void usado() {	//si el video esta usado por el encargo
		contador--;
	}
	
	public synchronized int getContador() {
		return contador;
	}
	
	public void setTrabajo(Video v) {
		this.vTrans = v;
	}
}
