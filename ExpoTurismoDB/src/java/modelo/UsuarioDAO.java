package modelo;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Logger;

public class UsuarioDAO {

    private RandomAccessFile raf;
    private RandomAccessFile rafTree;
    private long ref;
    private long finalTreeLength;

    public UsuarioDAO() throws FileNotFoundException, IOException, EOFException {

        this.raf = new RandomAccessFile("/Volumes/NICOLAS/usuarios.txt", "rw");
        this.rafTree = new RandomAccessFile("/Volumes/NICOLAS/usuarios.txt", "rw");//Maneja el árbol en el archivo usuarios.txt

        this.raf.seek(0);

        try {
            ref = this.raf.readLong();
            finalTreeLength = ref;
        } catch (EOFException e) {
            finalTreeLength = 8;
            this.raf.seek(0);

            for (int i = 0; i < 2808; i = i + 2) {
                this.raf.writeChar('\u0000');
            }
        }

    }

    public boolean crearArchivo(UsuarioVO user) throws IOException {

        boolean existe = false;
        boolean existia = false;
        long posAntigua = 0;
        
        if(user.getId()<=0){
          return false;  
        }
        if(user.getUser().length==0||user.getUser()==null){
            return false;
        }
        if(user.getPassword().length==0||user.getPassword()==null){
            return false;
        }
        
        
        for (int i = 8; i < finalTreeLength; i = i + 28) {
            rafTree.seek(i);
            if (rafTree.readInt() == user.getId()) {
                existe = true;
                rafTree.skipBytes(16);
                System.out.println("i>>>> " + i);
                System.out.println("Ya existe!");

                if (rafTree.readLong() == -1) {
                    System.out.println("ay hola");

                    rafTree.seek(rafTree.getFilePointer() - 28);
                    posAntigua = 2808 + (rafTree.getFilePointer() - 8) / 28 * 84;

                    existe = false;
                    existia = true;
                }

            }
        }

        if (!existe) {

            if (existia) {
                raf.seek(posAntigua);
            } else {
                raf.seek(raf.length());//Salta en el archivo hasta la última posición

            }
            long posición = raf.getFilePointer();

            raf.writeInt(user.getId());//Mete el ID en el archivo

            //Mete el nombre en el archivo con los espacios adicionales para completar el tamaño
            for (int i = 0; i < user.getUser().length; i++) {
                raf.writeChar(user.getUser()[i]);
            }
            for (int i = user.getUser().length; i < 20; i++) {
                raf.writeChar('\u0000');

            }

            //Mete el correo en el archivo con los espacios adicionales para completar el tamaño
            for (int i = 0; i < user.getPassword().length; i++) {
                raf.writeChar(user.getPassword()[i]);
            }
            for (int i = user.getPassword().length; i < 20; i++) {
                raf.writeChar('\u0000');
            }
            arbol(user.getId(), posición, existia);
            System.out.println("Agregado");
            return true;
        } else {
            return false;
        }

    }

    private void arbol(int id, long pos, boolean existia) throws FileNotFoundException, IOException {
        rafTree.seek(8);
        boolean flag = false;

        while (!flag) {

            long posicion = this.rafTree.getFilePointer();

            if (finalTreeLength == 8) {

                this.rafTree.seek(finalTreeLength);
                this.rafTree.writeInt(id);
                this.rafTree.writeLong(-1);
                this.rafTree.writeLong(-1);
                this.rafTree.writeLong(pos);

                finalTreeLength = this.rafTree.getFilePointer();

                this.rafTree.seek(0);
                this.rafTree.writeLong(finalTreeLength);

                flag = true;

            } else {
                int actual = this.rafTree.readInt();

                this.rafTree.seek(posicion);

                if (actual < id) {

                    this.rafTree.seek(posicion + 12);
                    long derPos = this.rafTree.readLong();
                    this.rafTree.seek(posicion + 12);

                    if (derPos == -1) {
                        this.rafTree.writeLong(finalTreeLength);//???????????????????
                        this.rafTree.seek(finalTreeLength);
                        this.rafTree.writeInt(id); //Revisar
                        this.rafTree.writeLong(-1);
                        this.rafTree.writeLong(-1);
                        this.rafTree.writeLong(pos);

                        flag = true;

                        finalTreeLength = this.rafTree.getFilePointer();
                        this.rafTree.seek(0);
                        this.rafTree.writeLong(finalTreeLength);

                    } else {
                        //arbol(derPos, id, pos, cont);
                        this.rafTree.seek(derPos);
                    }
                } else if (actual > id) {

                    this.rafTree.seek(posicion + 4);
                    long izqPos = this.rafTree.readLong();
                    this.rafTree.seek(posicion + 4);
                    if (izqPos == -1) {
                        this.rafTree.writeLong(finalTreeLength);
                        this.rafTree.seek(finalTreeLength);
                        this.rafTree.writeInt(id); //Revisar
                        this.rafTree.writeLong(-1);
                        this.rafTree.writeLong(-1);
                        this.rafTree.writeLong(pos);

                        flag = true;

                        finalTreeLength = this.rafTree.getFilePointer();
                        this.rafTree.seek(0);
                        this.rafTree.writeLong(finalTreeLength);
                    } else {

                        this.rafTree.seek(izqPos);
                    }
                } else if (actual == id && existia) {

                    rafTree.skipBytes(20);
                    rafTree.writeLong(pos);
                    flag = true;

                }
            }
        }

    }

    public long buscarUsuario(int id) throws IOException {

        int cont = 0;
        boolean flag = false;
        rafTree.seek(8);
        while (!flag) {
            int actual = this.rafTree.readInt();
            if (actual < id) {
                this.rafTree.skipBytes(8);
                long posDer = this.rafTree.readLong();
                if (posDer != -1) {
                    this.rafTree.seek(posDer);
                } else {
                    flag = true;
                }
            } else if (actual > id) {
                long posIzq = this.rafTree.readLong();
                if (posIzq != -1) {
                    this.rafTree.seek(posIzq);
                } else {
                    flag = true;
                }
            } else if (actual == id) {
                this.rafTree.skipBytes(16);
                return this.rafTree.readLong();
            }
        }
        return -1;
    }

    public int borrarUsuario(int id, String user, String password) throws IOException {

        long pos = buscarUsuario(id);

        if (pos == -1) {
            return 0;
        } else {
            
            raf.seek(pos+4);
            
            boolean names =compararStrings(raf,user);
            if(!names){
                return 1;
            }
            raf.seek(2852);// Posicion en el archivo en que se encuentra la primera contraseña(la del administrador)
            boolean pass = compararStrings(raf,password);
            if(!pass){
                return 2;
            }
            rafTree.seek(28 * ((pos - 2808) / 84) + 8);
            rafTree.skipBytes(20);
            rafTree.writeLong(-1);
            return 3;
        }

    }

    public boolean cambiarContraseña(int id, String password, String newP) throws IOException {

        long pos = buscarUsuario(id);

        if (pos == -1) {

            return false;

        } else {

            rafTree.seek(pos);
            rafTree.skipBytes(44);

            if (compararStrings(rafTree, password)) {

                rafTree.seek(pos + 44);

                for (int i = 0; i < newP.length(); i++) {
                    rafTree.writeChar(newP.charAt(i));
                }
                for (int i = newP.length(); i < 20; i++) {
                    rafTree.writeChar('\u0000');
                }
                return true;

            } else {
                return false;
            }

        }
    }

    private boolean compararStrings(RandomAccessFile raf, String s) throws IOException {
        String p = "";

        for (int i = 0; i < 40; i = i + 2) {

            p = p + raf.readChar();

        }
        String actual = "";
        for (int i = 0; i < p.length(); i++) {

            if (p.charAt(i) == '\u0000') {

                break;

            } else {
                actual = actual + p.charAt(i);

            }
        }
        if (s.equals(actual)) {

            return true;

        } else {
            return false;
        }
    }

    public boolean loggear(UsuarioVO user) throws IOException {

        long pos = buscarUsuario(user.getId());

        if (pos != -1) {

            raf.seek(pos);
            int id = raf.readInt();

            String usuario = "";

            for (int i = 0; i < user.getUser().length; i++) {
                usuario = usuario + user.getUser()[i];
            }

            String password = "";

            for (int i = 0; i < user.getPassword().length; i++) {
                password = password + user.getPassword()[i];
            }

            boolean uIguales = compararStrings(raf, usuario);
            boolean pIguales = compararStrings(raf, password);

            if (uIguales && pIguales) {
                return true;
            }

        } else {

            return false;

        }
        return false;
    }
}
