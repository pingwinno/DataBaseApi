package net.streamarchive.dbapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@SpringBootApplication
public class DbApiApplication {
    @Autowired
    StreamerRepository streamerRepository;
    @Autowired
    StreamsRepository streamsRepository;
    public static void main(String[] args) throws IOException {
        SpringApplication.run(DbApiApplication.class, args);
    }

    @PostConstruct
    public void importStreams() throws IOException {

        Streamer albisha = new Streamer();
        albisha.setStreamerName("albisha");
        albisha.setStorageEndpoint("https://94491.s.time4vps.cloud");
        streamerRepository.save(albisha);
        Streamer olyashaa = new Streamer();
        olyashaa.setStreamerName("olyashaa");
        olyashaa.setStorageEndpoint("https://storage.streamarchive.net");
        streamerRepository.save(olyashaa);


        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(List.class, Stream.class);
        List<Stream> albishaStreams = objectMapper.readValue(new URL("https://storage.streamarchive.net/db/streams/albisha?limit=1000")
                , type);

        albishaStreams.forEach(stream -> {


            stream.setStreamer("albisha");
            //System.out.println(stream);

        });
        streamsRepository.saveAll(albishaStreams);

        List<Stream> olyashaaStreams = objectMapper.readValue(new URL("https://storage.streamarchive.net/db/streams/olyashaa?limit=1000")
                , type);

        olyashaaStreams.forEach(stream -> {


            stream.setStreamer("olyashaa");
            //System.out.println(stream);

        });
        streamsRepository.saveAll(olyashaaStreams);
    }


}
