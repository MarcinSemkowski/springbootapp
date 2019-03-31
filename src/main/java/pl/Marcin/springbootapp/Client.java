package pl.Marcin.springbootapp;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
@Service
public class Client {

    private  RestTemplate restTemplate = new RestTemplate();


    //@EventListener(ApplicationReadyEvent.class)
    public void getDataFromApi() {
        ResponseEntity<User[]> exchange = restTemplate.exchange("http://localhost:8080/Create/user-list", HttpMethod.GET,
                HttpEntity.EMPTY, User[].class);
        User[] body = exchange.getBody();

        Arrays.stream(body).forEach(System.out::println);

    }

  // @EventListener(ApplicationReadyEvent.class)
    public void deleteDataFromApi(){
       User user2 = new User();
        user2.setId(1);
        user2.setName("Anna");
        user2.setSurname("Nowak");
        user2.setSex(Sex.FEMALE);
        user2.setAge(18);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/Create/user-list?id=1",user2);
        System.out.println("Delete user 2 " + user2.getName());
    }



    @EventListener(ApplicationReadyEvent.class)

    public void addToApi() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        String json = "{\n" +

                "        \"id\": 3,\n" +

                "        \"name\": \"Joanna\",\n" +

                "        \"surname\": \"Malinowska\",\n" +

                "        \"age\": 23,\n" +

                "        \"sex\": \"female\"\n" +

                "    }";

        HttpEntity httpEntity = new HttpEntity(json, httpHeaders);

        restTemplate.exchange(

                "http://localhost:8080/api",

                HttpMethod.POST,

                httpEntity,

                Void.class);

    }




}
