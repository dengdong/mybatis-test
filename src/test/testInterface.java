package test;

import model.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import mapper.PersonMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by adminstrator on 16/8/23.
 */
public class testInterface {


    private SqlSessionFactory factory ;

    @Before
    public  void testFactory() throws IOException {

        String configPath = "sqlConfiguration.xml";

        InputStream inputStream = Resources.getResourceAsStream(configPath);
        factory =  new SqlSessionFactoryBuilder().build(inputStream);


    }

    @Test
    public  void testGet(){

        SqlSession session = factory.openSession();
        PersonMapper iPerson = session.getMapper(PersonMapper.class);
        Person model = iPerson.get("kiki");
        System.out.println(model);
    }
}
