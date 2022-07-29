package com.fth.listener;

import com.fth.pojo.ProductType;
import com.fth.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author FengTianhao
 * @date 2022/7/28 9:23
 */
@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*.xml");
        ProductTypeService productTypeService = (ProductTypeService) context.getBean("ProductTypeServiceImpl");
        List<ProductType> typeList = productTypeService.getAll();
        servletContextEvent.getServletContext().setAttribute("typeList",typeList);
        System.out.println(typeList.size());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
