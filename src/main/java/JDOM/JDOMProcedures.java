/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDOM;

/**
 *
 * @author edgar
 */
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTextArea;
import org.jdom2.Content;
import org.jdom2.filter.Filters;

public class JDOMProcedures {

    private static Document docXML = new Document();
    private static Element raiz;
    private static Element nodoHijo;
    private static Element nodoPadre;
    Format format = Format.getPrettyFormat();
    XMLOutputter xmloutputter = new XMLOutputter(format);

    public Element getNodoPadre() {
        return nodoPadre;
    }

    public void setNodoPadre(Element nodoPadre) {
        this.nodoPadre = nodoPadre;
    }

    public Element getNodoHijo() {
        return nodoHijo;
    }

    public void setNodoHijo(Element nodoHijo) {
        this.nodoHijo = nodoHijo;
    }

    public Document getDocXML() {
        return docXML;
    }

    public void setDocXML(Document docXML) {
        this.docXML = docXML;
    }

    public Element getRaiz() {
        return raiz;
    }

    public void setRaiz(Element raiz) {
        this.raiz = raiz;
    }

    public Element crearNodo(String tagElemento, String contenido) {
        Element nodo = new Element(tagElemento);
        nodo.addContent(contenido);
        return nodo;
    }

    public Element crearNodo(String tagElemento) {
        Element nodo = new Element(tagElemento);
        return nodo;
    }

    public Element getNode(Iterator iterator, Integer indexElemento) {
        List<Element> elementList = new ArrayList<Element>();
        while (iterator.hasNext()) {
            elementList.add((Element) iterator.next());
        }
        return elementList.get(indexElemento);
    }

    public List<Content> getNodeList(Iterator iterator) {
        List<Content> elementList = new ArrayList<Content>();
        iterator = getDocXML().getDescendants(Filters.element());
        while (iterator.hasNext()) {
            elementList.add((Element) iterator.next());
        }
        return elementList;
    }

    public String xmlOrder(List<Object> orderDetails, List<Object[]> listItems) {
        setRaiz(crearNodo("Orders"));
        getDocXML().addContent(getRaiz());
        setNodoHijo(crearNodo("Order"));
        Element order = getNodoHijo();
        getNodoHijo().setAttribute("id", orderDetails.get(0).toString());
        getNodoHijo().addContent(crearNodo("Date", orderDetails.get(1).toString()));
        getNodoHijo().addContent(crearNodo("Mode", orderDetails.get(2).toString()));
        setNodoPadre(getNodoHijo());
        setNodoHijo(crearNodo("Customer", orderDetails.get(4).toString()));
        getNodoHijo().setAttribute("id", orderDetails.get(3).toString());
        getNodoPadre().addContent(getNodoHijo());
        setNodoHijo(getNodoPadre());
        getNodoHijo().addContent(crearNodo("Total", orderDetails.get(5).toString()));
        setNodoPadre(getNodoHijo());
        setNodoHijo(crearNodo("Sales-rep", orderDetails.get(7).toString()));
        getNodoHijo().setAttribute("id", orderDetails.get(6).toString());
        getNodoPadre().addContent(getNodoHijo());
        setNodoHijo(crearNodo("Items"));
        getNodoPadre().addContent(getNodoHijo()); //order es padre
        getRaiz().addContent(getNodoPadre()); //order se le agrega a orders
        setNodoPadre(getNodoHijo()); // Items es padre
        Element Item = getNodoPadre(); //referencia a Items como padre
        for (Object[] listItem : listItems) {
            setNodoPadre(Item);
            setNodoHijo(crearNodo("Item")); //Item es hijo
            getNodoHijo().setAttribute("id", listItem[0].toString());
            getNodoPadre().addContent(getNodoHijo()); //Item se le agrega a Items
            setNodoPadre(getNodoHijo()); //Item es padre
            setNodoHijo(crearNodo("Product", listItem[1].toString()));
            getNodoHijo().setAttribute("id", listItem[2].toString());
            setNodoPadre(Item);
            setNodoHijo(crearNodo("Unit-price", listItem[3].toString()));
            getNodoPadre().addContent(getNodoHijo());
            setNodoHijo(crearNodo("Quantity", listItem[4].toString()));
            getNodoPadre().addContent(getNodoHijo());
        }
        setNodoPadre(order);
        String docStr = xmloutputter.outputString(getNodoPadre());
        System.out.println(docStr);
        return null;
    }

    public static void main(String[] args) {
        JDOMProcedures p = new JDOMProcedures();
        Document docXML = p.getDocXML();
        Element raiz = p.crearNodo("CatLibros");
        docXML.addContent(raiz);

        Element nodoHijo = p.crearNodo("Libro");
        Element autor = p.crearNodo("Autor", "Oscar Rocha");
        nodoHijo.setAttribute("ISBN", "12345");
//        nodoHijo.addContent(p.crearNodo("Autor", "Oscar Rocha"));
        nodoHijo.addContent(autor);
        nodoHijo.addContent(p.crearNodo("Titulo", "Inteligencia de Negocios"));
        nodoHijo.addContent(p.crearNodo("Editorial", "McGraw-Hill"));
        nodoHijo.addContent(p.crearNodo("Precio", "120.00"));
        raiz.addContent(nodoHijo);

        nodoHijo = p.crearNodo("Libro");
        nodoHijo.setAttribute("ISBN", "56789");
        nodoHijo.addContent(p.crearNodo("Autor", "Julio Meza"));
        nodoHijo.addContent(p.crearNodo("Titulo", "Redes Sociales"));
        nodoHijo.addContent(p.crearNodo("Editorial", "McGraw-Hill"));
        nodoHijo.addContent(p.crearNodo("Precio", "220.00"));
        raiz.addContent(nodoHijo);

        nodoHijo = p.crearNodo("Libro");
        nodoHijo.setAttribute("ISBN", "54321");
        nodoHijo.addContent(p.crearNodo("Autor", "Mario Fuentes"));
        nodoHijo.addContent(p.crearNodo("Titulo", "Tecnologias Emergentes"));
        nodoHijo.addContent(p.crearNodo("Editorial", "Pearson"));
        nodoHijo.addContent(p.crearNodo("Precio", "320.00"));
        raiz.addContent(nodoHijo);

        nodoHijo = p.crearNodo("Libro");
        nodoHijo.setAttribute("ISBN", "13579");
        nodoHijo.addContent(p.crearNodo("Autor", "Ana Arteaga"));
        nodoHijo.addContent(p.crearNodo("Titulo", "Administracion de TI"));
        nodoHijo.addContent(p.crearNodo("Editorial", "Pearson"));
        nodoHijo.addContent(p.crearNodo("Precio", "260.00"));
        raiz.addContent(nodoHijo);

        for (Iterator iterator = docXML.getDescendants(Filters.element()); iterator.hasNext();) {
            Element next = (Element) iterator.next();
            System.out.println(next.getName());
        }

        p.setNodoPadre(p.getNode(p.getDocXML().getDescendants(Filters.element()), 20));
        p.setNodoHijo(p.crearNodo("Edad", "32"));

        p.getNodoPadre().addContent(p.getNodoHijo());

        Format format = Format.getPrettyFormat();
        XMLOutputter xmloutputter = new XMLOutputter(format);
        String docStr = xmloutputter.outputString(docXML);
        System.out.println(docStr);

        try {
            File f = new File("C:\\ApJava\\Libreria.xml");
            FileWriter fw = new FileWriter(f);
            xmloutputter.output(docXML, fw);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
