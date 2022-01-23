package com.espol.proyectopoo2.modelo;

import com.espol.proyectopoo2.App;
import com.espol.proyectopoo2.data.CraterData;
import com.espol.proyectopoo2.data.RegistroData;
import com.espol.proyectopoo2.interfaces.AccionesRover;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private double carga;    
    /**
     * Angulo en radianes donde esta viendo el rover
     */
    private double angulo;    
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
        Image img = new Image(App.class.getResourceAsStream(image_path),60,60,false,true);
        this.imagen = new ImageView(img);
        imagen.setLayoutX(ubicacion.getLongitud());
        imagen.setLayoutY(ubicacion.getLatitud());
        this.carga = carga;
        angulo=0;        
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
     * @return el angulo del rover en grados.
     */
    public double getAngulo() {return angulo*180/Math.PI;}
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
    /**
     * Verifica si el rover se descargara en el proceso
     * @param consumo lo que consumira el rover para moverse
     * @return retorna verdadero si se descargara, falso si no
     */
    public boolean isDescargado(int consumo){
        //se descarga
        
        return (this.carga-consumo)==0;
    }
    @Override
    public void avanzar() 
            throws ComandoInvalidoException{
        if(isDescargado(1))
            throw new ComandoInvalidoException("Carga insuficiente para avanzar");
        setUbicacion(posicionNueva());
        moverImgRover(ubicacion.getLongitud(),ubicacion.getLatitud());    
        setCarga(carga-=1);
        System.out.println("Carga:"+getCarga());
    }
    @Override
    public void girar(double grados){
        grados *= Math.PI/180;
        angulo+=grados;
        moverImgRover(ubicacion.getLongitud(),ubicacion.getLatitud());
    }    
    @Override
    public void desplazarse(Ubicacion ubicacion, boolean cargar) 
            throws ComandoInvalidoException{                
        double x_diff = ubicacion.getLongitud() - this.ubicacion.getLongitud();
        System.out.println(x_diff);
        double y_diff = ubicacion.getLatitud() - this.ubicacion.getLatitud();
        System.out.println(y_diff);
        double newAngulo = Math.atan2(y_diff, x_diff)*180/(Math.PI);
        System.out.println("angulo"+angulo);
        System.out.println("nuevoAngulo"+newAngulo);
        double distancia = Math.sqrt(Math.pow(x_diff,2)+Math.pow(y_diff,2));
        if(isDescargado((int)distancia)||cargar)
            throw new ComandoInvalidoException("Carga insuficiente para desplazarse");
        angulo=0;
        girar(newAngulo);
        System.out.println("angulo"+angulo);
        new HiloAvanzar(
                (int) Math.floor(distancia/10)
                        ).start();
    }    
    @Override
    public String sensar(){
        //Esta en el crater
        System.out.println("Ubicacion Rover:"+ubicacion);
        Crater crater = CraterData.isUbicacionInCrater(ubicacion);
        
        if(null!=crater){
            
            String minerales = CraterData.mineralAleatorioRepetido();
            ArrayList<String> mineralesReporte = new ArrayList();
            mineralesReporte.addAll(Arrays.asList(minerales.split(",")));
            Registro reporte = new Registro(LocalDate.now(),
                                    mineralesReporte,
                                    crater.getNombre());            
            RegistroData.guardarReporte(reporte);
            crater.setExplorado(true);
            System.out.println("Minerales: "+minerales);
            return minerales;
        }
        
        System.out.println("En ningun crater");
        return null;
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
     * Nueva posicion del rover, movido 10 pixeles
     * @return nueva ubicacion
     */
    public Ubicacion posicionNueva(){
        double posX = ubicacion.getLongitud();
        double posY = ubicacion.getLatitud();
        //sacar las componentes rectangulares
        double compX = 10*Math.cos(angulo);
        double compY = 10*Math.sin(angulo);                  
        //settear
        posX+=compX;
        posY+=compY;        
        return new Ubicacion(posX,posY);
    }
    /**
     * Hilo de avanzar
     */
    public class HiloAvanzar extends Thread {
        private int repeticiones;
        /**
         * Constructor del hilo
         * @param repeticiones numero de veces que se repetirá el comando de avanzar
         */
        public HiloAvanzar(int repeticiones){
            super();
            this.repeticiones=repeticiones;
        }
        /**
         * Metodo run del hilo
         */
        @Override
        public void run(){
            for(int i=1; i<=repeticiones;i++){
                avanzar();
                try{
                sleep(250);
                }catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    /**
     * Hilo de girar
     */
    public class HiloGirar extends Thread{
        /**
         * Angulo a girar el rover
         */
        private double angulo;
        /**
         * Constructor del hilo
         * @param angulo Angulo a girar el rover
         */
        public HiloGirar(double angulo){
            super();
            this.angulo=angulo;        
        }
        /**
         * Metodo run del hilo
         */
        @Override
        public void run(){
            girar(-getAngulo());
            girar(angulo);
        }
    }
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        return nombre;
    }
}
