package com.ote.test;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

public class TribesParser {

    Document document;

    public TribesParser(File file) throws ParserConfigurationException, IOException, SAXException {

        // NOT Thread Safe
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.document = dBuilder.parse(file);
    }

    public double getNumberOfFeatureTeamLeader() throws XPathExpressionException {

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        XPathExpression xPathExpression = xpath.compile("count(//tribes/tribe/featureTeam/person[role='FEATURE_TEAM_LEADER'])");
        return (Double) xPathExpression.evaluate(this.document, XPathConstants.NUMBER);
    }

    public String getNameOfFirstFeatureLeader() throws XPathExpressionException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        XPathExpression xPathExpression = xpath.compile("//tribes/tribe/featureTeam/person[role='FEATURE_TEAM_LEADER']/firstName");
        return (String) xPathExpression.evaluate(this.document, XPathConstants.STRING);
    }
}
