package main;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//стартовая страница
@ApplicationPath("/")
//основной ресурс и подключение к другим классам
public class MyApplication extends Application{
    //возвращает коллекцию непустых классов
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( JsonTest.class );
        return h;
    }
}