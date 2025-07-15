package servidor;

import java.util.ArrayList;
import java.util.List;

import ssoo.videos.Encargo;
import ssoo.videos.Video;
import ssoo.videos.MenuRaiz;
import ssoo.videos.Dvd;
import ssoo.videos.servidor.Cliente;
/*****************************************
*
* Alumno: jennifer.ruan@alumnos.upm.es
*
****************************************/
public class HiloEncargo<Thead> implements Runnable{
	
	final private Cliente cliente;
	final private Encargo encargo;
	private ColaTrabajo queue;
	private CacheTrabajosActivos cache;
	private List<Video> listVideo;
	public HiloEncargo(Cliente cliente, Encargo encargo, ColaTrabajo cola, CacheTrabajosActivos cache) {
		this.cliente = cliente;
		this.encargo = encargo;
		this.queue = cola;
		this.cache = cache;
		listVideo = encargo.getVideos();
	}

	@Override
	public void run() {
		Trabajo t ;
		ArrayList<Trabajo> listTrabajo = new ArrayList<Trabajo>(listVideo.size());
		System.out.println(Thread.currentThread().getName());
		try {
			for(Video v : listVideo) {
				t = new Trabajo(v);
				Trabajo tr = cache.getTrabajo(v);
				if(tr == null) {
					//t = new Trabajo(v);
					queue.put(t); 
					cache.put(t);	//put trabajo nuevo
					listTrabajo.add(t);
					System.out.println("Video añadido: " + encargo.getTitulo() + "-" + v.getNombre());
				}else {
					t.repetido();	//contador++
					listTrabajo.add(tr);
					System.out.println("Video repetido: " + v.getNombre());
				}
		}
		
			for(int i=0; i<listTrabajo.size(); i++) {
				t = listTrabajo.get(i);
				if(t.getTranscodificado() == null) {
					t.esperar();
				}
				listVideo.set(i, t.getTranscodificado());
				t.usado();		//contador--
				cache.put(t);	//put trabajo transcodificado
				System.out.println("Video listo: " + encargo.getTitulo()+"-"+t.getVideo().getNombre());	
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MenuRaiz menu = new MenuRaiz(encargo.getVideos());
		Dvd dvd= new Dvd (encargo.getTitulo(),menu, encargo.getVideos());
		cliente.enviar(dvd);
		
		
	}

}