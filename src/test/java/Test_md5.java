import com.fth.utils.MD5Util;
import org.junit.Test;

/**
 * @author FengTianhao
 * @date 2022/7/22 17:21
 */
public class Test_md5 {
    @Test
    public void testMd5()
    {
        String str = MD5Util.getMD5("5201314");
        System.out.println(str);
    }

}
