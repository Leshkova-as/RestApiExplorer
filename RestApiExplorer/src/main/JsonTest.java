package main;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.File;
import java.io.IOException;

// URI path "/jsontest"
@Path("/jsontest")
public class JsonTest {

    @GET

    @Produces("text/plain")
    public String getClichedMessage() {
        //работа с файлом в формате json с помощью библиотеки jackson
        ObjectMapper mapper = new ObjectMapper();
        MyBean obj = new MyBean("name1", "file");
        String jsonInString = null;
        try {
            mapper.writeValue(new File("file.json"), obj);
            jsonInString = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonInString;
    }
}