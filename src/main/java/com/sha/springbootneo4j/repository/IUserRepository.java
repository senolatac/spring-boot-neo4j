package com.sha.springbootneo4j.repository;

import com.sha.springbootneo4j.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sa
 * @date 2020-02-29
 * @time 14:09
 */
//handled save, delete, update ,findById,all operations automatically.
@Transactional //Using transactional is suggested for all neo4j database operations.
public interface IUserRepository extends Neo4jRepository<User, Long>
{
    User findByName(String name); // findBy + fieldName

    //Select * from user, country where user.id = country.user_id and country.code = :pCountryCode;
    @Query("MATCH (u:User)-[r:LIVES_IN]->(c:Country) WHERE c.countryCode = {0} RETURN u")
    List<User> findByCountry(String countryCode);

    //Insert into User VALUES(:friendName);
    //Select * from User where id = :userId;
    //Insert into HAS_FRIENDS values(user_id, friendId);
    @Query("CREATE (u:User {name: {1}}) WITH u MATCH (n:User) WHERE ID(n) = {0} WITH u,n CREATE (n)-[:HAS_FRIENDS]->(u) RETURN n")
    User addFriendRelation(Long userId, String friendName);

    //Parameters can be used with @Param annotation or like above examples with order-number-of-arg.
    //User1 is friend with User2 from n. hop level like Linkedin connections.
    //In SQL, it is not easy to handle. Select * from User u1, User u2, User u3, ... (number of max hops) then aggregate with case-when states...
    //[:HAS_FRIENDS*minHops...maxHops] or [:HAS_FRIENDS*]
    //Why min(path size); Because it can be more than one between two friends. u1 -> u2 -> u3 or u1 -> u4 -> u3; u1 can be friend with u2 and u4...
    //Why size(p); Because p is a relationship path so we need only length of path.
    @Query("MATCH (u:User)-[p:HAS_FRIENDS*]-(e:User) WHERE ID(u) = {user1Id} and ID(e) = {user2Id} RETURN min(size(p))")
    Long hopCountBetweenFriends(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
}
