package fr.istic.vv;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorWithDefaults;
import java.util.*;

// import printerWriter.

import java.io.File; // Import the File class 
import java.io.IOException; // Import the IOException class
import java.io.FileWriter;
import java.io.BufferedWriter;



// This class visits a compilation unit and
// prints all public enum, classes or interfaces along with their public methods
public class PublicElementsPrinter extends VoidVisitorWithDefaults<Void> {

    private LinkedList<String> privateAttribut = new LinkedList<String>(); //Attributs privées
    private LinkedList<String> methodsName = new LinkedList<String>(); //Nom de toute les méthodes
    private LinkedList<String> getMethods = new LinkedList<String>(); //Nom des méthodes etant des getter
    private LinkedList<String> getMethodsWithoutGet = new LinkedList<String>(); //Nom des méthodes etant des getter sans le prefixe get
    private LinkedList<String> noGetterFields = new LinkedList<String>(); //attributs privée ne possédant pas de getter

    private String text = new String();  
    

    public void printReport2(){
        System.out.println("privateAttribut:" + privateAttribut);
        System.out.println("methodsname:" + methodsName);
        System.out.println("getmethods:" + getMethods);
        System.out.println("getMethodsWithoutGet" + getMethodsWithoutGet);
        System.out.println("noGetterFields:" + noGetterFields);
        
    }

    public void checkGetters(){
        isGetter();
        hasNoGetter();
    }

    public void writeFile(String text) {
        BufferedWriter bw = null;
        try {

       File file = new File("D:/Documents/myfile.txt");
  
        if (!file.exists()) {
           file.createNewFile();
        }
  
        FileWriter fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
        bw.write(text);
            System.out.println("File written Successfully");
  
        } catch (IOException ioe) {
         ioe.printStackTrace();
        }
        finally
        { 
            try{
                if(bw!=null)
            bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
     }



    public void isGetter(){

        ListIterator<String> list_Iter = methodsName.listIterator(0); 
  
        while(list_Iter.hasNext()){ 
            String it = list_Iter.next();
            if (it.startsWith("get")){
                getMethods.addFirst(it);
            }
        } 

        removeGet();
    }

    public void removeGet(){
        ListIterator<String> Iter_getter = getMethods.listIterator(0); 

        while(Iter_getter.hasNext()){
            String str = Iter_getter.next().replace("get", "");
            getMethodsWithoutGet.add(str);
        }
    }

    public void hasNoGetter(){

        for (String e: privateAttribut){
            if (!getMethodsWithoutGet.contains(e)){
                noGetterFields.add(e);
            }
        }

    }

    public void makeEmptyLList(){
        //Desolée pour cette horreur
        ListIterator<String> it1 = privateAttribut.listIterator(0); 
        ListIterator<String> it2 = methodsName.listIterator(0); 
        ListIterator<String> it3 = getMethods.listIterator(0); 
        ListIterator<String> it4 = getMethodsWithoutGet.listIterator(0); 
        ListIterator<String> it6 = noGetterFields.listIterator(0); 


        while(it1.hasNext()){
            privateAttribut.remove();
        }
        while(it2.hasNext()){
            methodsName.remove();
        }        
        while(it3.hasNext()){
            getMethods.remove();
        }        
        while(it4.hasNext()){
            getMethodsWithoutGet.remove();
        }
        while(it6.hasNext()){
            noGetterFields.remove();
        }
    }

    public String removeOptional(String text){
        System.out.println(text);
        String str = text.replace("Optional[", "");
        String str2 = str.replace("]", "");
        System.out.println(str2);
        return str2;
    }


    @Override
    public void visit(CompilationUnit unit, Void arg) {
        for(TypeDeclaration<?> type : unit.getTypes()) {
            type.accept(this, null);
        }
    }

    public void visitTypeDeclaration(TypeDeclaration<?> declaration, Void arg) {

        if(!declaration.isPublic()) return;

        makeEmptyLList();
        String text2 = new String ("Classe: " + declaration.getNameAsString() +
        System.lineSeparator() + "Package: " + removeOptional(declaration.getFullyQualifiedName().toString()) + System.lineSeparator());


        for(MethodDeclaration method : declaration.getMethods()) {
            method.accept(this, arg);
        }
        // Printing nested types in the top level
        for(FieldDeclaration member : declaration.getFields()) {
            member.accept(this, arg);
        }

        checkGetters();
        String text3 = new String("     attributs privés sans getter: " + noGetterFields + System.lineSeparator() + System.lineSeparator());
        text = text + text2 + text3;
        writeFile(text);

    }

    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(EnumDeclaration declaration, Void arg) {
        visitTypeDeclaration(declaration, arg);
    }

    @Override
    public void visit(MethodDeclaration declaration, Void arg) {
        if(!declaration.isPublic()) return;
        methodsName.addFirst(declaration.getNameAsString());
    }

    @Override
    public void visit(FieldDeclaration declaration, Void arg){
        if(declaration.isPublic()) return;
        String convertedToString = String.valueOf(declaration.getVariables());
        String str = convertedToString.replace("[", "");
        String str2 = str.replace("]", "");
        privateAttribut.addFirst(str2);
    }

}
