package com.cyws.tank.utils.ehcache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.cyws.tank.bean.common.ConstantBean;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class EhCacheUtil {
	private CacheManager manager;	
    private static EhCacheUtil ehCache;
    private static final String cache_list = "ehcacheLsit";
    private static final String cache_map = "ehcacheMap";
    private static final String cache_obj = "ehcacheObj";
    private static final String cache_disk = "diskEhcache";
    
    private EhCacheUtil() {
        //String str_url=System.getProperty("user.dir")+"/resources/ehcache.xml"; 
    	String str_url= ConstantBean.getConstantMap().get("EHCACHE_URL").toString();
        manager = CacheManager.create(str_url);        
    }
 
    //加锁
    static final Lock instanceLock = new ReentrantLock();
    public static EhCacheUtil getInstance() {
        if (ehCache == null) {
        	instanceLock.lock();
        	if (ehCache == null) {
            ehCache = new EhCacheUtil();
        	}
            instanceLock.unlock();
        }
        return ehCache;
    }
    
    private Cache getHashMapManager(){    	
    	 Cache cache = manager.getCache(cache_map);
        return cache;
    }

    private Cache getListManager(){
    	Cache cache = manager.getCache(cache_list);
        return cache;
    }
    
    private Cache getObjectManager(){
    	Cache cache = manager.getCache(cache_obj);
        return cache;
    }
    
    private Cache getDiskManager(){
    	Cache cache = manager.getCache(cache_disk);
        return cache;
    }
    
    /**
     * 对实体类操作方法
     * */
    //==========list=============
    public void putList(String key, Object value) {
        Cache cache = getListManager();
        Element element = new Element(key, value);
        cache.put(element);
    }
 
    public Object getList(String key) {
        Cache cache = getListManager();
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }
    
    public void removeList(String cacheName, String key) {
        Cache cache = getListManager();
        cache.remove(key);
    }
    
    //==========map=============
    public void putMap(String key, Object value) {
        Cache cache = getHashMapManager();
        Element element = new Element(key, value);
        cache.put(element);
    }
 
    public Object getMap(String key) {
        Cache cache = getHashMapManager();
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }
    
    public void removeMap(String cacheName, String key) {
        Cache cache = getHashMapManager();
        cache.remove(key);
    }
    
    //==========Object=============
    public void putObject(String key, Object value) {
        Cache cache = getObjectManager();
        Element element = new Element(key, value);
        cache.put(element);
    }
 
    public Object getObject(String key) {
        Cache cache = getObjectManager();
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }
    
    public void removeObject(String cacheName, String key) {
        Cache cache = getObjectManager();
        cache.remove(key);
    }
    
    //==========Disk=============
    public void putDisk(String key, Object value) {
        Cache cache = getDiskManager();
        Element element = new Element(key, value);
        cache.put(element);
        cache.flush();
    }
 
    public Object getDisk(String key) {
        Cache cache = getDiskManager();
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }
    
    public void removeDisk(String cacheName, String key) {
        Cache cache = getDiskManager();
        cache.remove(key);
    }
    
}
