package com.heuristicservices.springbootuuids;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>
{
    @Query("SELECT u.id AS id, u.email AS email, u.dateOfBirth AS dateOfBirth FROM User u WHERE u.id = :userId")
    Optional<UserDisplay> getDisplayById(@Param("userId") UUID userId);

    @Query(value = "SELECT CAST(u.id AS VARCHAR) AS id, " +
            "u.email AS email," +
            "u.date_of_birth AS dateOfBirth " +
            "FROM spring_boot_uuids.users u WHERE u.id = :userId", nativeQuery = true)
    Optional<UserDisplay> getNativeDisplayById(@Param("userId") UUID userId);
}
