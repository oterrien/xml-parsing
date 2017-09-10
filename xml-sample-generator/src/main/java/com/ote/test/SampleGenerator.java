package com.ote.test;

import com.ote.test.structure.*;
import lombok.Getter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.util.Random;
import java.util.UUID;

public final class SampleGenerator {

    @Getter
    private final static SampleGenerator Instance = new SampleGenerator();

    private JAXBContext jaxbContext;

    private SampleGenerator() {
        try {
            jaxbContext = JAXBContext.newInstance(Tribes.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void generate(File outputFile,
                         int numberOfTribes,
                         int numberOfFeatureTeamsPerTribe,
                         int numberOfPersonsPerFeatureTeam) throws JAXBException {

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        QName qName = new QName("tribes");

        int numberOfFeatureTeams = 0;
        Tribes tribes = new Tribes();
        for (int i = 1; i <= numberOfTribes; i++) {
            Tribe tribe = new Tribe();
            tribe.setId(i);
            tribe.setPerimeter(randomString());
            for (int j = 1; j <= numberOfFeatureTeamsPerTribe; j++) {
                numberOfFeatureTeams += 1;
                FeatureTeam featureTeam = new FeatureTeam();
                featureTeam.setId(numberOfFeatureTeams);
                featureTeam.setName(randomString());
                featureTeam.setApplication(randomString());
                for (int k = 1; k <= numberOfPersonsPerFeatureTeam; k++) {
                    Person person = new Person();
                    person.setLastName(randomString());
                    person.setFirstName(randomString());
                    person.getRole().add(Role.values()[new Random().nextInt(Role.values().length)]);
                    featureTeam.getPerson().add(person);
                }
                tribe.getFeatureTeam().add(featureTeam);
            }
            tribes.getTribe().add(tribe);
        }

        jaxbMarshaller.marshal(new JAXBElement<>(qName, Tribes.class, tribes), outputFile);
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }
}
