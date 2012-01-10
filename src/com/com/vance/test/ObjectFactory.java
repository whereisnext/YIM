package com.vance.test;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.vance.domain.summary;
//import net.javabeat.articles.java6.newfeatures.jaxb.Item;
//import net.javabeat.articles.java6.newfeatures.jaxb.Items;
//import net.javabeat.articles.java6.newfeatures.jaxb.ObjectFactory;

@XmlRegistry
public class ObjectFactory {

    private final static QName _ID_QNAME = new QName("", "id");
    private final static QName _FirstName_QNAME = new QName("", "first-name");
  private final static  QName _Summary_QNAME=new QName("","summary");

    public ObjectFactory() {
    }

    public UserProfile createItem() {
        return new UserProfile();
    }

//    public Items createItems() {
//        return new Items();
//    }

//    @XmlElementDecl(namespace = "", name = "id")
//    public JAXBElement<String> createPrice(String value) {
//        return new JAXBElement<String>(_ID_QNAME, String.class, null, value);
//    }
//
//    @XmlElementDecl(namespace = "", name = "first-name")
//    public JAXBElement<String> createName(String value) {
//        return new JAXBElement<String>(_FirstName_QNAME, String.class, null, value);
//    }
//    
//    @XmlElementDecl(namespace = "", name = "summary")
//    public JAXBElement<summary> createSummary(summary value) {
//		return new JAXBElement<summary>(_Summary_QNAME, summary.class, null, value);
//    }    

}
