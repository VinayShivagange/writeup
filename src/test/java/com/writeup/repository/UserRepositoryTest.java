package com.writeup.repository;

import com.writeup.domain.User;
import com.writeup.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Inject
    private UserRepository repository;
    @Inject
    private TestEntityManager entityManager;

    @Test
    public void findByTitle_CorrectString_Success() {
        entityManager.merge(new User(1, "vinay", "vsn@apptio.com"));
        List<User> list = this.repository.findByUserName("vsn@apptio.com");
        list.stream().forEach(x -> System.out.println(x.getDisplayName()));
        assertEquals(list.size(), 1);
    }
}
