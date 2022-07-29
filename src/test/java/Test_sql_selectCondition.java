import com.fth.mapper.ProductInfoMapper;
import com.fth.pojo.ProductInfo;
import com.fth.pojo.vo.ProductInfoVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author FengTianhao
 * @date 2022/7/29 11:24
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml","classpath:applicationContext_service.xml"})
public class Test_sql_selectCondition {
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Test
    public void test_sql_select_condition()
    {
        ProductInfoVo vo = new ProductInfoVo();
        vo.setpName("4");
        vo.setTypeId(3);
        vo.setlPrice(3000);
        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
        list.forEach(productInfo -> System.out.println(productInfo));
    }


}
