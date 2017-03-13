/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author ayoro
 */
public class ClienteDAO {
    private RandomAccessFile raf;
    private RandomAccessFile rafTree;
    private long finalPos;
    private static ClienteDAO clienteDAO;

    private ClienteDAO() throws FileNotFoundException, IOException {
        this.raf = new RandomAccessFile("pruebaClientes.txt", "rw");
        this.rafTree = new RandomAccessFile("pruebaClientes.txt", "rw");//Maneja el árbol en el archivo profesor.txt
        this.raf.seek(0);
        long ref = -1;
        
        try {
            ref = this.raf.readLong();
            this.finalPos = ref;
        } catch (EOFException e) {
            this.finalPos = 8;
            this.raf.seek(0);
            this.raf.writeLong(finalPos);
            for (int i = 0; i < 2800; i++) {
                this.raf.writeChar('\u0000');
            }
        }

    }
    
    public static ClienteDAO getClienteDAO() throws IOException{
        if (clienteDAO == null) {
            clienteDAO = new ClienteDAO();
        }
        return clienteDAO;
    }
    
    public void crearArchivo(ClienteVO cliente) throws IOException{
        this.raf.seek(raf.length());
        long posicion = this.raf.getFilePointer();
        
        this.raf.writeInt(cliente.getCedula());
        char name [] = cliente.getNombre().toCharArray();
        char correo [] = cliente.getEmail().toCharArray();
        
        for (int i = 0; i < name.length; i++) {
            this.raf.writeChar(name[i]);
        }
        for (int i = name.length; i < 20; i++) {
            this.raf.writeChar('\u0000');
        }
        
        for (int i = 0; i < correo.length; i++) {
            this.raf.writeChar(correo[i]);
        }
        for (int i = correo.length; i < 20; i++) {
            this.raf.writeChar('\u0000');
        }
        
        this.raf.writeInt(cliente.getTelefono());
        arbol(cliente.getCedula(), posicion);
    }
    
    public int leerEntero(long posByte) throws IOException {
        raf.seek(posByte); //Salta a la posición a leer
        return raf.readInt(); //retorna el dato leído
    }

    public char[] leerChars(long posByte) throws IOException {
        char var[] = new char[20]; //Crea un nuevo vector para colocar las letras de la palabra
        raf.seek(posByte); //Salta a la posición a leer
        for (int i = 0; i < 20; i++) { //lee 20 veces una variable de tipo char
            var[i] = raf.readChar();
        }
        return var;
    }

    public long getTamanoLista() throws IOException {
        return this.raf.length(); //Retorna el tamaño del archivo
    }
    
    public void arbol(int id, long pos) throws IOException {
        rafTree.seek(8);
        boolean flag = false;

        while (!flag) {
            System.out.println("id: " + id);
            long posicion = this.rafTree.getFilePointer();
            System.out.println(posicion);
            if (finalPos == 8) {
                System.out.println("PRIMER IF");
                System.out.println(finalPos);
                this.rafTree.seek(finalPos);
                this.rafTree.writeInt(id);
                this.rafTree.writeLong(-1);
                this.rafTree.writeLong(-1);
                this.rafTree.writeLong(pos);
                System.out.println("se puso el primero");
                finalPos = this.rafTree.getFilePointer();
                System.out.println("POSICION FINAL " + finalPos);
                this.rafTree.seek(0);
                this.rafTree.writeLong(finalPos);
                flag = true;
            } else {
                System.out.println("ELSE PRIMER IF");
                int actual = this.rafTree.readInt();
//                int pruebaActual = ((actual-131071)/65536)+1;
                System.out.println("ID comparar " + id);
                System.out.println("ID actual: " + actual);
                this.rafTree.seek(posicion);
                if (actual < id) {
                    System.out.println("Entró primer condicional (El número metido es mayor que el que estaba)");
                    this.rafTree.seek(posicion + 12);
                    long derPos = this.rafTree.readLong();
                    this.rafTree.seek(posicion + 12);
                    if (derPos == -1) {
                        this.rafTree.writeLong(finalPos);
                        this.rafTree.seek(finalPos);
                        this.rafTree.writeInt(id);
                        this.rafTree.writeLong(-1);
                        this.rafTree.writeLong(-1);
                        this.rafTree.writeLong(pos);
                        flag = true;
                        finalPos = this.rafTree.getFilePointer();
                        System.out.println("POSICION FINAL " + finalPos);
                        this.rafTree.seek(0);
                        this.rafTree.writeLong(finalPos);
                        System.out.println("se añadio 1");
                    } else {
                        System.out.println("se fue a la pos: " + derPos);
                        this.rafTree.seek(derPos);
                    }
                } else {
                    System.out.println("Entró segundo condicional");
                    this.rafTree.seek(posicion + 4);
                    long izqPos = this.rafTree.readLong();
                    this.rafTree.seek(posicion + 4);
                    if (izqPos == -1) {
                        this.rafTree.writeLong(finalPos);
                        this.rafTree.seek(finalPos);
                        this.rafTree.writeInt(id);
                        this.rafTree.writeLong(-1);
                        this.rafTree.writeLong(-1);
                        this.rafTree.writeLong(pos);
                        flag = true;
                        finalPos = this.rafTree.getFilePointer();
                        System.out.println("POSICION FINAL " + finalPos);
                        this.rafTree.seek(0);
                        this.rafTree.writeLong(finalPos);
                        System.out.println("se añadio 2");
                    } else {
                        this.rafTree.seek(izqPos);
                    }
                }
            }
        }
    }
    
    public long buscarCliente(int id) throws IOException{
        this.rafTree.seek(8);
        boolean flag = false;
        while(!flag){
            int actual = this.rafTree.readInt();
            if (actual < id) {
                this.rafTree.skipBytes(8);
                long posDer = this.rafTree.readLong();
                if (posDer != -1) {
                    this.rafTree.seek(posDer);
                }else{
                    flag = true;
                }
            }else if(actual > id){
                long posIzq = this.rafTree.readLong();
                if(posIzq != -1){
                    this.rafTree.seek(posIzq);
                }else{
                    flag = true;
                }
            }else if(actual == id){
                this.rafTree.skipBytes(16);
                return this.rafTree.readLong();
            }
        }
        return -1;
    }
    
    public boolean editarNombreCliente(int id, String nombre) throws IOException{
        
        long pos = buscarCliente(id);
        
        if(pos == -1){
            return false;
        }else{
            this.raf.seek(pos+4);
            for (int i = 0; i < 20; i++) {
                this.raf.writeChar('\u0000');
            }
            this.raf.seek(pos+4);
            char name [] = nombre.toCharArray();
            for (int i = 0; i < name.length; i++) {
                this.raf.writeChar(name[i]);
            }
            for (int i = name.length; i < 20; i++) {
                this.raf.writeChar('\u0000');
            }
            return true;
        }
        
    }
    
    public boolean editarEmailCliente(int id, String email) throws IOException{
        
        long pos = buscarCliente(id);
        
        if (pos == -1) {
            return false;
        }else{
            this.raf.seek(pos+44);
            for (int i = 0; i < 20; i++) {
                this.raf.writeChar('\u0000');
            }
            this.raf.seek(pos+44);
            char correo [] = email.toCharArray();
            for (int i = 0; i < correo.length; i++) {
                this.raf.writeChar(correo[i]);
            }
            for (int i = correo.length; i < 20; i++) {
                this.raf.writeChar('\u0000');
            }
            return true;
        }
        
    }
    
    public boolean editarTelefonoCliente(int id, int telefono) throws IOException{
        
        long pos = buscarCliente(id);
        
        if (pos == -1) {
            return false;
        }else{
            this.raf.seek(pos+84);
            this.raf.writeInt(telefono);
            return true;
        }
        
    }
    
    public void borrarCliente(int id) throws IOException{
        this.rafTree.seek(8);
        boolean flag = false;
        while(!flag){
            int actual = this.rafTree.readInt();
            if (actual < id) {
                this.rafTree.skipBytes(8);
                long posDer = this.rafTree.readLong();
                if (posDer != -1) {
                    this.rafTree.seek(posDer);
                }else{
                    flag = true;
                }
            }else if(actual > id){
                long posIzq = this.rafTree.readLong();
                if(posIzq != -1){
                    this.rafTree.seek(posIzq);
                }else{
                    flag = true;
                }
            }else if(actual == id){
                this.rafTree.skipBytes(16);
                this.rafTree.writeFloat(-1);
            }
        }
    }
    
    public boolean usuarioRegistrado(int id) throws IOException{ 
        //Si no está registrado pero el id existe, toca meter los datos con el editar
        if (idExistente(id)) {
            for (int i = 8; i < finalPos; i += 28) {
                this.rafTree.seek(i);
                int actual = this.rafTree.readInt();
                if (actual == id) {
                    this.rafTree.seek(i+20);
                    long posId = this.rafTree.readLong();
                    if (posId != -1) {
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        } 
        return false;
    }
    
    public boolean idExistente(int id) throws IOException{
        for (int i = 8; i < finalPos; i+=28) {
            this.rafTree.seek(i);
            int actual = this.rafTree.readInt();
            if (actual == id) {
                return true;
            }
        }
        return false;
    }
    
    public long recuperarRegistro(int id) throws IOException{
        int pos = -1;
        for (int i = 8; i < finalPos; i+=28) {
            this.rafTree.seek(i);
            int actual = this.rafTree.readInt();
            if (actual == id) {
                pos = i;
                this.rafTree.skipBytes(16);
                this.rafTree.writeLong((((pos-8)/28)*88)+2808);
            }
        }
        
        return (((pos-8)/28)*88)+2808;
    }
    
    public ArrayList getNombres() throws IOException{
        ArrayList reg = new ArrayList();
        int cantidad = (int) finalPos/28;
        String nombres [] = new String [cantidad];
        this.rafTree.seek(28);
        long primeraPos = this.rafTree.readLong();
        for (int i = (int) primeraPos; i < this.raf.length(); i+=88) {
            this.raf.seek(i);
            int cedula = this.raf.readInt();
            reg.add(cedula);
            String nombre = "";
            for (int j = 0; j < 20; j++) {
                char n = this.raf.readChar();
                if (n == '\u0000') {
                    break;
                }else{
                    nombre += n;
                }
            }
            reg.add(nombre);
            this.raf.seek(i+44);
            String correo = "";
            for (int j = 0; j < 20; j++) {
                char c = this.raf.readChar();
                if (c == '\u0000') {
                    break;
                }else{
                    correo += c;
                }
            }
            reg.add(correo);
            this.raf.seek(i+84);
            int telefono = this.raf.readInt();
            reg.add(telefono);
        }
        return reg;
    }
}
