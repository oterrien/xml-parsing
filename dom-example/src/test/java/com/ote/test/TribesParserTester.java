package com.ote.test;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.File;

public class TribesParserTester {

    @Test
    public void find_name_of_first_feature_team_leader_in_small_file() throws Exception {

        long begin = System.currentTimeMillis();
        try {
            TribesParser parser = new TribesParser(new File("../sample/sample1K.xml"));
            String firstFeatureTeamLeaderName = parser.getNameOfFirstFeatureLeader();
            Assertions.assertThat(firstFeatureTeamLeaderName).isEqualTo("23aea325-66a5-4e3a-8ac3-ad4b77be1b40");
        } finally {
            long duration = System.currentTimeMillis() - begin;
            System.out.printf("find_name_of_first_feature_team_leader_in_small_file ends in %d ms\n", duration);
        }
    }

    @Test
    public void find_name_of_first_feature_team_leader_in_large_file() throws Exception {

        long begin = System.currentTimeMillis();
        try {
            TribesParser parser = new TribesParser(new File("../sample/sample1M.xml"));
            String firstFeatureTeamLeaderName = parser.getNameOfFirstFeatureLeader();
            Assertions.assertThat(firstFeatureTeamLeaderName).isEqualTo("a3c3b78f-521b-4ee3-9f49-b0aa2566fb5c");
        } finally {
            long duration = System.currentTimeMillis() - begin;
            System.out.printf("find_name_of_first_feature_team_leader_in_large_file ends in %d ms\n", duration);
        }
    }

    @Test
    public void find_number_of_feature_team_leaders_in_large_file() throws Exception {

        long begin = System.currentTimeMillis();
        try {
            TribesParser parser = new TribesParser(new File("../sample/sample1M.xml"));
            double numberOfFT = parser.getNumberOfFeatureTeamLeader();
            Assertions.assertThat(numberOfFT).isEqualTo(99454);
        } finally {
            long duration = System.currentTimeMillis() - begin;
            System.out.printf("find_number_of_feature_team_leaders_in_large_file ends in %d ms\n", duration);
        }
    }
}
