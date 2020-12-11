package concurrent;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *解决哈希冲突的四种方法，1 开放定址法 2 再哈希法 3 链地址法 4 建立公共溢出区
 */

public class Hash {
    Map map = new ConcurrentHashMap();
}
