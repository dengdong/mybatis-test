package test;

import model.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by adminstrator on 16/8/21.
 */
public class mybatistest {

    private SqlSessionFactory factory ;

    @Before
    public  void testFactory() throws IOException {

        String configPath = "sqlConfiguration.xml";

        InputStream inputStream = Resources.getResourceAsStream(configPath);
        factory =  new SqlSessionFactoryBuilder().build(inputStream);


    }

    @Test
    public  void testFind(){
        SqlSession sqlSession = factory.openSession();
        List<Person> list = sqlSession.selectList("mapper.PersonMapper.find");

        for (Person p: list
             ) {
            System.out.println(p);
        }
    }

    @Test
    public void testGet(){
        SqlSession session = factory.openSession();
        Person p = session.selectOne("mapper.PersonMapper.get", "kiki");
        System.out.println(p);
    }

    @Test
    public void testInsert(){
        SqlSession session = factory.openSession();
        Person p = new Person();
        p.setAddress("深圳");
        p.setAge(15);
        p.setUserName("hello");
        session.insert("mapper.PersonMapper.insert",p);
        session.commit();
    }

    @Test
    public void testUpdate(){
        SqlSession session = factory.openSession();
        Person p = session.selectOne("mapper.PersonMapper.get", "kiki");
        p.setAddress("abcdefg");
        session.update("mapper.PersonMapper.update",p);
        session.commit();
    }

    @Test
    public  void testDelete(){
        SqlSession session = factory.openSession();
        session.delete("mapper.PersonMapper.delete",4);
        session.commit();
    }

    @Test
    public void testGetPersonInfo()
    {
        SqlSession session = factory.openSession();

        Map map = new Hashtable();
        map.put("userName", "kiki");

        List<Person > list = session.selectList("mapper.PersonInfoMapper.selectPersonInfo" ,map);
        System.out.println( list.size());
    }
}
