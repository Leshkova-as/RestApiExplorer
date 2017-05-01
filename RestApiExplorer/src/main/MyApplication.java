package main;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//start page
@ApplicationPath("/")
//main resource
public class MyApplication extends Application{
    //get class collection
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( FileTree.class );
        return h;
    }
}