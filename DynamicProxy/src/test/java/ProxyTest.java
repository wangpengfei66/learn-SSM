import com.kaishengit.proxy.Dell;
import com.kaishengit.proxy.Lenovo;
import com.kaishengit.proxy.LenovoProxy;
import org.junit.Test;

/**
 * Created by Administrator on 2017/7/12.
 */
public class ProxyTest {

    Dell dell = new Dell();
    LenovoProxy lenovoProxy = new LenovoProxy(dell);
    @Test
    public void sales() {
        lenovoProxy.sales();
    }

}
