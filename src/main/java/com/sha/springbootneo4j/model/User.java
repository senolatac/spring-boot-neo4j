package com.sha.springbootneo4j.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * @author sa
 * @date 2020-02-29
 * @time 13:48
 */
@Data //Getter,Setter, ToString, Equals...
@NoArgsConstructor // empty constructor
@NodeEntity
public class User
{
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Relationship(type = "HAS_FRIENDS") // it can be also converted to an entity.
    private List<User> friends;

    //User lives in these countries...
    @Relationship(type = LivesInRelation.TYPE)
    private LivesInRelation livesIn;

    public User(String name)
    {
        this.name = name;
    }
}
