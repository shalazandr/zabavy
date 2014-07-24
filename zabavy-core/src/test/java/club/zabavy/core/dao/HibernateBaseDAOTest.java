package club.zabavy.core.dao;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*/BaseDAOImplTest-context.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public abstract class HibernateBaseDAOTest {

}
