package com.ote.test;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CountNumberOfFeatureTeamLeadersHandler extends DefaultHandler {

    @Getter
    private long numberOfFeatureTeamLeader = 0;

    private boolean isRole;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("role")) {
            isRole = true;
        }
    }

    @Override
    public void characters(char[] data, int start, int length) throws SAXException {
        if (isRole) {
            String str = new String(data, start, length);
            if (str.equals("FEATURE_TEAM_LEADER")) {
                numberOfFeatureTeamLeader++;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("role")) {
            isRole = false;
        }
    }
}
