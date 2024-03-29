package com.zty.onlineedu.edu;

import com.zty.onlineedu.edu.pojo.entity.EduChapter;
import com.zty.onlineedu.edu.mapper.EduChapterMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author zty
 * @Date 2022/12/1 22:47
 */
@SpringBootTest
public class DemoTest01 {

    @Autowired
    private EduChapterMapper eduChapterMapper;

    @Test
    public void selectEdu(){
        List<EduChapter> allData = eduChapterMapper.getAllData("1");

        allData.forEach(System.out::println);

    }


    @Test
    public void insertEdu(){
        EduChapter eduChapter = new EduChapter();
        eduChapter.setId("2");


    }

    @Test
    public void test01(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dtf.format(now);

        System.out.println(format);

    }
}
