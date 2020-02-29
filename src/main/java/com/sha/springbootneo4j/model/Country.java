package com.sha.springbootneo4j.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * @author sa
 * @date 2020-02-29
 * @time 13:59
 */
@Getter
@NoArgsConstructor
@NodeEntity
public class Country
{
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String countryCode;

    //User - [:LIVES_IN]->(c:Country) -> This is a OUTGOING relation for LIVES_IN.
    //User - [:LIVES_IN]<-(c:Country) -> This is a INCOMING relation for LIVES_IN.
    //direction -> default: OUTGOING
    @Relationship(type = LivesInRelation.TYPE, direction = Relationship.INCOMING)
    private List<LivesInRelation> liveFrom;

    public Country(String countryCode)
    {
        this.countryCode = countryCode;
    }
}
