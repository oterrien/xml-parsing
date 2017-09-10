package com.ote.test;

import com.ote.test.structure.Tribes;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;

public class TribesGeneratorTester {

    @Test
    public void sample() throws Exception {

        JAXBContext jaxbContext = JAXBContext.newInstance(Tribes.class);

        // Parse file
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Tribes tribes = jaxbUnmarshaller.unmarshal(new StreamSource(ClassLoader.getSystemResourceAsStream("sample.xml")), Tribes.class).getValue();

        System.out.println(tribes);

        // Serialize file
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        QName qName = new QName("tribes");

        jaxbMarshaller.marshal(new JAXBElement<>(qName, Tribes.class, tribes), new FileWriter("sample2.xml"));
    }

    @Test
    public void generateSampleFile() throws Exception {

        SampleGenerator.getInstance().generate(new File("../sample/sample1K.xml"), 10, 10, 10);
    }
}
