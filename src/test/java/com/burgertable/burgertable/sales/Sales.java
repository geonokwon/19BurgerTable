package com.burgertable.burgertable.sales;

import com.burgertable.burgertable.entity.SalesEntity;
import com.burgertable.burgertable.repository.SalesRepository;
import com.burgertable.burgertable.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class Sales {

    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void salesPageTest(){
        Long naver = 1000L;
        Long cupang = 1000L;
        Long beamin = 1000L;
        Long tanyo = 1000L;
        Long yogiyo = 1000L;
        Long simple = 1000L;
        Long card = 1000L;
        Long cash = 1000L;
        Long social = naver + cupang + beamin + tanyo + yogiyo + simple;
        Long total = social + card + cash;
        String date = "2024-12-10";




        for (int i = 0; i < 100; i++){
            SalesEntity salesEntity = new SalesEntity();
            salesEntity.setCardSales(card);
            salesEntity.setCashSales(cash);
            salesEntity.setSimpleSales(simple);
            salesEntity.setBaeminSales(beamin);
            salesEntity.setCoupangSales(cupang);
            salesEntity.setTanyoSales(tanyo);
            salesEntity.setNaverSales(naver);
            salesEntity.setYogiyoSales(yogiyo);
            salesEntity.setSocialSales(social);
            salesEntity.setTotalSales(total);
            salesEntity.setUser(userRepository.findByName("임민규"));
            salesRepository.save(salesEntity);
        }



    }
}
