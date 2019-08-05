package net.streamarchive.dbapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/streams")
public class StreamerRestApi {

    @Autowired
    StreamsRepository streamsRepository;
    @Autowired
    StreamSearchService streamSearchService;

    @RequestMapping(value = "{streamer}", method = RequestMethod.GET)
    public List<Stream> getStreams(@PathVariable("streamer") String streamer) {
        return streamsRepository.findAllByStreamer(streamer);
    }

    @RequestMapping(value = "/{streamer}/search", method = RequestMethod.GET)
    public List findStreams(@PathVariable("streamer") String streamer, @RequestParam("query") String query) {
        return streamSearchService.findStreams(streamer, query);
    }

}
