package net.streamarchive.dbapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/streams")
public class StreamsRestApi {

    @Autowired
    StreamsRepository streamsRepository;
    @Autowired
    StreamSearchService streamSearchService;

    @RequestMapping(value = "{streamer}", method = RequestMethod.GET)
    public List<Stream> getStreams(@PathVariable("streamer") String streamer, @RequestParam(value = "page", defaultValue = "0"
            , required = false) int page, @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                   @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort,
                                   @RequestParam("order_by") String orderBy) {

        Sort.Direction sortDirection;
        if (sort.equals("asc")) {
            sortDirection = Sort.Direction.ASC;
        } else if (sort.equals("desc")) {
            sortDirection = Sort.Direction.DESC;
        } else {
            throw new BadRequestException();
        }

        return streamsRepository.findAllByStreamer(streamer, PageRequest.of(page, size, Sort.by(Sort.Order.by(orderBy)
                .with(sortDirection))));
    }

    @RequestMapping(value = "/{streamer}/search", method = RequestMethod.GET)
    public List findStreams(@PathVariable("streamer") String streamer, @RequestParam("query") String query) {
        return streamSearchService.findStreams(streamer, query);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private class BadRequestException extends RuntimeException {
    }
}
