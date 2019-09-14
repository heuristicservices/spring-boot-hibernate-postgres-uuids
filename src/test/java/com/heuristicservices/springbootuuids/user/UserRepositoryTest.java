package com.heuristicservices.springbootuuids.user;

import com.heuristicservices.springbootuuids.User;
import com.heuristicservices.springbootuuids.UserDisplay;
import com.heuristicservices.springbootuuids.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest
{
    private static final String TEST_EMAIL_ADDRESS = "test@example.com";

    @Autowired
    private UserRepository userRepository;

    @Test
    public void savesUserWithId()
    {
        User user = new User();
        user.setEmail(TEST_EMAIL_ADDRESS);
        User persistedUser = userRepository.save(user);
        assertThat(persistedUser.getId(), is(not(nullValue())));
    }

    @Test
    public void getsDisplayProjection()
    {
        User user = new User();
        user.setEmail(TEST_EMAIL_ADDRESS);
        user.setDateOfBirth(LocalDate.of(1980, 1, 1));
        User persistedUser = userRepository.save(user);
        Optional<UserDisplay> userDisplay = userRepository.getDisplayById(persistedUser.getId());
        assertThat(userDisplay.get().getId(), is(not(nullValue())));
        assertThat(userDisplay.get().getAge(), is(39));
    }

    @Test
    public void getsDisplayProjectionUsingNativeQuery()
    {
        User user = new User();
        user.setEmail(TEST_EMAIL_ADDRESS);
        user.setDateOfBirth(LocalDate.of(1980, 1, 1));
        User persistedUser = userRepository.save(user);
        Optional<UserDisplay> userDisplay = userRepository.getNativeDisplayById(persistedUser.getId());
        assertThat(userDisplay.get().getId(), is(not(nullValue())));
        assertThat(userDisplay.get().getAge(), is(39));
    }
}
