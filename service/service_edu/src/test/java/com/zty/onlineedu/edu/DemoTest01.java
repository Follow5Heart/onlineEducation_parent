package com.zty.onlineedu.edu;

import com.zty.onlineedu.edu.entity.EduChapter;
import com.zty.onlineedu.edu.mapper.EduChapterMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
