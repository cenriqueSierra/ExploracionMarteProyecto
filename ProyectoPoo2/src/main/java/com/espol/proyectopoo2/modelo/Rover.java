package com.espol.proyectopoo2.modelo;

import com.espol.proyectopoo2.App;
import com.espol.proyectopoo2.data.CraterData;
import com.espol.proyectopoo2.data.RegistroData;
import com.espol.proyectopoo2.interfaces.AccionesRover;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 * Clase que representa el rover
 * @author Dome user
 */
public abstract class Rover implements AccionesRover {
    /**
     * Nombre del Rover
     */
    private String nombre ;
    
    /**
     * Ubicacion del rover;
     */
    Ubicacion ubicacion; 
    
    /**
     * Path de la imagen del rover
     */
    private ImageView imagen;  
    
    /**
     * Carga del rover.
     */
    private volatile double carga;
    
    /**
     * Angulo en grados donde esta viendo el rover
     */
    private double angulo;
    
    /**
     * Hilo de las tareas del rover
     */
    protected HiloTareas tareas;
    
    /**
     * Crea un objeto de tipo rover
     * @param nombre Nombre del rover
     * @param ubicacion Ubicacion del rover
     * @param image_path Path de la imagen del river
     * @param carga Carga del rover
     * @throws IOException 
     */ 
    public Rover(String nombre, Ubicacion ubicacion, String image_path, double carga)
            throws IOException{
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        URL url = App.class.getResource(image_path);
        BufferedImage imgBF = ImageIO.read(url);
        //Image img = new Image(App.class.getResourceAsStream(image_path),60,60,false,true);
        Image img = SwingFXUtils.toFXImage(imgBF, null);        
        this.imagen = new ImageView(img);
        this.imagen.setFitWidth(60);
        this.imagen.setFitHeight(60);
        this.imagen.setPreserveRatio(true);
        this.imagen.setSmooth(true);
        imagen.setLayoutX(ubicacion.getLongitud());
        imagen.setLayoutY(ubicacion.getLatitud());
        this.carga = carga;
        angulo=0;

    }   
    
    /**
     * Inicio del hilo de rover
     */
    public void start(){
        if(tareas!=null)
            stop();
        tareas = new HiloTareas();
        tareas.corriendo=true;
        tareas.start();
    }
    
    /**
     * 
     */
    public void stop(){
        tareas.corriendo=false;
        tareas.cola.clear();
    }
    /**
     * @return  nombre de rover
     */
    public String getNombre() {return nombre;}
    
    /**
     * @return el path de la imagen.
     */
    public ImageView getImagen() {return imagen;}
    
    /**
     * @return la carga del rover.
     */
    public double getCarga() {return carga;}
    
    /**
     * @return el angulo del rover en grados
     */
    public double getAngulo() {return angulo/*this.imgAngulo.get()*/;}
    
    /**
     * @return el angulo del rover en radianes
     */
    public double getAnguloRadianes() {return Math.toRadians(getAngulo());}
    
    /**
     * @return la ubicacion del rover
     */
    public Ubicacion getUbicacion(){return ubicacion;}
        
    /**
     * @param carga la nueva carga del rover
     */
    public void setCarga(double carga) {this.carga = carga;}
    
    /**
     * @param ubicacion ubicacion a fijar
     */
    public void setUbicacion(Ubicacion ubicacion){this.ubicacion=ubicacion;}  
    
    /**
     * Carga el rover
     */
    public abstract void cargar();
    
    //private void setAngulo( double angulo){ this.imgAngulo.set(angulo);}
    /**
     * Verifica si el rover se descargara en el proceso
     * @param consumo lo que consumira el rover para moverse
     * @return retorna verdadero si se descargara, falso si no
     */
    public boolean isDescargado(int consumo){
        return (this.carga-consumo)<=0;
    }
    
    @Override
    public void avanzar() 
            throws ComandoInvalidoException{
        
        if(isDescargado(1)){
            Platform.runLater(()->{Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Notificacion");
                a.setHeaderText("Acciones");
                a.setContentText("Carga insuficiente");
                a.show();});
            return;
            }

        setUbicacion(posicionNueva());
        moverImgRover(ubicacion.getLongitud(),ubicacion.getLatitud());    
        setCarga(carga-=1);
    }
    
    @Override
    public void girar(double grados){
        angulo+=grados;
        if(angulo<0)
            angulo+=360;
        
        angulo%=360;
        
        moverImgRover(ubicacion.getLongitud(),ubicacion.getLatitud());

    }   
    
    @Override
    public void desplazarse(Ubicacion ubicacion, boolean cargar) {            
        double distancia = this.ubicacion.distancia(ubicacion).get(0);
        double newAngulo = Math.toDegrees(this.ubicacion.distancia(ubicacion).get(1));
        if(isDescargado((int)distancia)&&!cargar){
            Platform.runLater(()->{Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Notificacion");
                a.setHeaderText("Acciones");
                a.setContentText("Carga insuficiente para avanzar");
                a.show();});
            return;
        }        
        tareas.cola.add(()->{
            System.out.println("Dentro del angulo");
            angulo=0;
            girar(newAngulo);
            System.out.println("angulo"+angulo);});
        tareas.cola.add(
            new HiloAvanzar(
                (int) Math.floor(distancia/10),
                cargar));       
    }  
    
    @Override
    public String sensar(List<Crater> crateres) 
            throws ComandoInvalidoException{
        //Esta en el crater
        System.out.println("Ubicacion Rover:"+ubicacion);
        Crater crater = CraterData.isUbicacionInCrater(ubicacion,crateres);        
        if(null!=crater){            
            String minerales = CraterData.mineralAleatorioRepetido();
            ArrayList<String> mineralesReporte = new ArrayList();
            mineralesReporte.addAll(Arrays.asList(minerales.split(",")));
            Registro reporte = new Registro(LocalDate.now(),
                                    mineralesReporte,
                                    crater.getNombre());            
            RegistroData.guardarReporte(reporte);
            crater.setExplorado(true);
            System.out.println(crater.getId());
            System.out.println("Minerales: "+minerales);
            return minerales;
        }else
            throw new ComandoInvalidoException("No se encuentran crateres en la ubicacion"+
                    "actual");
    }
    
    /**
     * Fija la posicion y angulo de la imagen del rover
     * @param x posicion en x a gijar
     * @param y posicion en y a gijar
     */    
    public void moverImgRover(double x, double y){
        imagen.setLayoutX(x);
        imagen.setLayoutY(y);
        imagen.setRotate(getAngulo());
    }
    
    /**
     * Nueva posicion del rover, movido 10 pixeles en la direccion que esta 
     * apuntando
     * @return nueva ubicacion
     */
    public Ubicacion posicionNueva(){
        double posX = ubicacion.getLongitud();
        double posY = ubicacion.getLatitud();
        double compX = Math.round(10*Math.cos(getAnguloRadianes()));
        double compY = Math.round(10*Math.sin(getAnguloRadianes()));
        posX+=compX;
        posY+=compY;
        return new Ubicacion(posX,posY);
    }
    
    /**
     * Hilo de avanzar
     */
    public class HiloAvanzar extends Thread {
        /**
         * Veces que se repetira el comando avanzar
         */
        private int repeticiones;
        
        /**
         * Estado de ir a cargar del rover
         */
        private boolean cargar;
        /**
         * Constructor del hilo
         * @param repeticiones numero de veces que se repetirá el comando de avanzar
         * @param cargar estado de ir a cargar del rover
         */
        public HiloAvanzar(int repeticiones, boolean cargar){
            super();
            this.repeticiones=repeticiones;
            this.cargar=cargar;
        }
       
        @Override
        public void run(){
            if(carga<=1)
                return;
            
            for(int i=1; i<=repeticiones;i++){
                if(carga<=1){
                   this.repeticiones-=i;
                    tareas.cola.add(this);
                   return;
                }
                
                if(cargar)
                    setCarga(carga+1);
                avanzar();
                
                try{
                    sleep(250);
                }catch(InterruptedException ex) {
                    System.out.println("Problemas tecnicos. Estamos resolviendo..");
                    //ex.printStackTrace();
                }
            }
        }
    }
    
    
    /**
     * Hilo de tareas a ejecutar
     */
    public class HiloTareas extends Thread{
        /**
         * Cola de tareas a ejecutar
         */
        protected volatile Queue<Runnable> cola;
        
        /**
         * Estado de corriendo del hilo
         */
        protected volatile boolean corriendo;
        
        /**
         * Constructor del hilo
         */
        public HiloTareas(){
            this.cola= new LinkedList<>();
        }
        
        @Override
        public void run(){
            while(corriendo){
                if(!cola.isEmpty()){
                    Runnable r = cola.poll();
                    if(r!=null)
                        r.run();
                }
                    
            }            
        }
    }
    /**
     * Provee el nombre del rover
     * @return nombre del rover
     */
    @Override
    public String toString(){
        return  nombre;
    }
    

}
