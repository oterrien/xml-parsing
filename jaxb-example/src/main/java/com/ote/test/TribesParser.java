package com.ote.test;

import com.ote.test.structure.Tribes;
import org.apache.commons.jxpath.JXPathContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TribesParser {

    // Thread Safe
    private final static JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance(Tribes.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final Tribes tribes;

    public TribesParser(File file) throws JAXBException, FileNotFoundException {
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.tribes = jaxbUnmarshaller.unmarshal(new StreamSource(new FileInputStream(file)), Tribes.class).getValue();

    }

    public double getNumberOfFeatureTeamLeader() {
        JXPathContext context = JXPathContext.newContext(tribes);
        String xpath = "count(tribe/featureTeam/person[role='FEATURE_TEAM_LEADER'])";
        return (Double) context.getValue(xpath);
    }

    public String getNameOfFirstFeatureLeader() {
        JXPathContext context = JXPathContext.newContext(tribes);
        String xpath = "tribe/featureTeam/person[role='FEATURE_TEAM_LEADER']/firstName";
        return (String) context.getValue(xpath);
    }
}
