package net.streamarchive.dbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DbApiApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DbApiApplication.class, args);
    }
/*
    @PostConstruct
    public void importStreams() throws IOException {
        User user = new User();
        Streamer albisha = new Streamer();
        albisha.setStreamerName("albisha");
        albisha.setStorageEndpoint("https://94491.s.time4vps.cloud");
        Streamer olyashaa = new Streamer();
        olyashaa.setStreamerName("olyashaa");
        olyashaa.setStorageEndpoint("https://storage.streamarchive.net");
        user.setEnabled(true);
        user.setUsername("admin");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        System.out.println(user.getPassword());
        List<Streamer> authorities = new ArrayList<>();
        authorities.add(albisha);
        authorities.add(olyashaa);
        user.setStreamers(authorities);
        userRepository.save(user);

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(List.class, Stream.class);
        List<Stream> albishaStreams = objectMapper.readValue(new URL("https://storage.streamarchive.net/db/streams/albisha?limit=1000")
                , type);

        albishaStreams.forEach(stream -> {


            stream.setStreamer("albisha");
            //System.out.println(stream);

        });
        streamRepository.saveAll(albishaStreams);

        List<Stream> olyashaaStreams = objectMapper.readValue(new URL("https://storage.streamarchive.net/db/streams/olyashaa?limit=1000")
                , type);

        olyashaaStreams.forEach(stream -> {


            stream.setStreamer("olyashaa");
            //System.out.println(stream);

        });
        streamRepository.saveAll(olyashaaStreams);
    }

*/
}
