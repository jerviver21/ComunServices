package com.vi.comun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Jerson Viveros
 */
public class FilesUtils {

    public static boolean comprimirArchivo(List<String> rutaArchivos, String rutaZip){
        boolean exito = false;
        byte [] bufer = new byte[ 1024 ];

        if(!rutaArchivos.isEmpty()){
            ZipOutputStream zipOutputStream  = null;
            FileInputStream archivoAnexo = null;
            try {
                zipOutputStream = new ZipOutputStream( new FileOutputStream(  rutaZip ) );
                for(String rutaArchivo : rutaArchivos){
                    File archivo = new File(rutaArchivo);
                    if(archivo.exists()){
                        System.out.println("Ruta archivo: "+rutaArchivo);
                        archivoAnexo = new FileInputStream(  archivo);
                        zipOutputStream.putNextEntry( new ZipEntry( archivo.getName() ) );
                        int longitud;
                        while( ( longitud = archivoAnexo.read( bufer ) ) > 0 ) {
                            zipOutputStream.write( bufer, 0, longitud );
                        }
                    }
                }
                zipOutputStream.closeEntry( );
            } catch( IOException ex ) {
                ex.printStackTrace();
                return false;
            } finally {
                try {
                    if( zipOutputStream != null ){
                        zipOutputStream.close();
                    }
                    if(archivoAnexo != null){
                        archivoAnexo.close( );
                    }
                } catch (IOException e) {
                        e.printStackTrace();
                }
            }
        }else{
            Log.getLogger().log(Level.ALL, "NO HAY ARCHIVOS PARA COMPRIMIR");
        }
        return exito;
    }
    
    public String crearArchivo(String ruta, String nombre, InputStream bytes)throws IOException{
        File dirOut = new File(ruta);
        if(dirOut.exists() && dirOut.isFile()){
            dirOut.delete();
        }
        if(!dirOut.exists()){
            dirOut.mkdirs();
        }
        String nombreArchivo = ruta+File.separator+nombre;
        File archivo = new File(ruta+File.separator+nombre);
        archivo.deleteOnExit();
        FileOutputStream out = new FileOutputStream(archivo);
        int c;
        while((c=bytes.read()) != -1){
            out.write(c);
        }
        out.close(); 
        return nombreArchivo;
    }

    public static void moverArchivo(String rutaArch, String rutaArchDestino)throws IOException{
        File inFile = new File(rutaArch);
        File outFile = new File(rutaArchDestino);

        FileInputStream in = new FileInputStream(inFile);
        FileOutputStream out = new FileOutputStream(outFile);

        int c;
        while ((c = in.read()) != -1)
        out.write(c);

        in.close();
        out.close();

        File file = new File(rutaArch);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void borrarArchivo(String rutaArch) throws Exception{
        File file = new File(rutaArch);
        if (file.exists()) {
            file.delete();
        }
    }
}
