package com.ote.test;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TribesParser {

    private File file;

    public TribesParser(File file) {
        this.file = file;
    }


    public double getNumberOfFeatureTeamLeader() throws ParserConfigurationException, SAXException, IOException {

        CountNumberOfFeatureTeamLeadersHandler handler = new CountNumberOfFeatureTeamLeadersHandler();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(new InputSource(new FileInputStream(file)));

        return handler.getNumberOfFeatureTeamLeader();
    }

    public String getNameOfFirstFeatureLeader() throws ParserConfigurationException, SAXException, IOException {

        NameOfFirstFeatureTeamLeaderHandler handler = new NameOfFirstFeatureTeamLeaderHandler();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(new InputSource(new FileInputStream(file)));

        return handler.getNameOfFirstFeatureTeamLeader();
    }
}
