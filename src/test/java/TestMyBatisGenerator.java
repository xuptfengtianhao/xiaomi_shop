import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 逆向工程测试类
 * @author FengTianhao
 * @date 2022/7/22 16:46
 */
public class TestMyBatisGenerator {
    public static void main(String[] args) throws Exception
    {
        List<String> warning = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(MyBatisGenerator.class.getClassLoader().getResource("generator_Config.xml").toURI());
        ConfigurationParser cp = new ConfigurationParser(warning);
        Configuration config = cp.parseConfiguration(configFile);
        ShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback,warning);
        myBatisGenerator.generate(null);
        System.out.println("MyBatis 逆向工程执行成功，刷新项目查看文件");
    }
}
