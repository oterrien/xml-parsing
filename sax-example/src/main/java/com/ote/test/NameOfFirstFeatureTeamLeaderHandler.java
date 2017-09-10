package com.ote.test;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NameOfFirstFeatureTeamLeaderHandler extends DefaultHandler {

    @Getter
    private String nameOfFirstFeatureTeamLeader;

    private String role;

    private boolean isPerson, isFirstName, isRole, isFound = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            isPerson = true;
        } else if (qName.equals("firstName")) {
            isFirstName = true;
        } else if (qName.equals("role")) {
            isRole = true;
        }
    }

    @Override
    public void characters(char[] data, int start, int length) throws SAXException {

        if (!isFound) {
            if (isFirstName) {
                String str = new String(data, start, length);
                if ("FEATURE_TEAM_LEADER".equals(role)) {
                    nameOfFirstFeatureTeamLeader = str;
                    isFound = true;

                }
            } else if (isRole) {
                role = new String(data, start, length);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            isPerson = false;
        } else if (qName.equals("firstName")) {
            isFirstName = false;
        } else if (qName.equals("role")) {
            isRole = false;
        }
    }
}
