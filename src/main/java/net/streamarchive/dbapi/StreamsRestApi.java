package net.streamarchive.dbapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/streams")
public class StreamsRestApi {

    @Autowired
    StreamsRepository streamsRepository;
    @Autowired
    StreamSearchService streamSearchService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Stream> getStreams(@RequestParam(value = "streamer") String streamer, @RequestParam(value = "page", defaultValue = "0"
            , required = false) int page, @RequestParam(value = "size", defaultValue = "20", required = false) int size,
                                   @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort,
                                   @RequestParam(value = "order_by", defaultValue = "date", required = false) String orderBy) {

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

    @RequestMapping(value = "{uuid}")
    public Stream getStream(@PathVariable("uuid") String uuid) {

        return streamsRepository.findById(UUID.fromString(uuid)).orElseThrow(NotFoundException::new);

    }

    @RequestMapping(value = "/{streamer}/search", method = RequestMethod.GET)
    public List findStreams(@PathVariable("streamer") String streamer, @RequestParam("query") String query) {
        return streamSearchService.findStreams(streamer, query);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private class BadRequestException extends RuntimeException {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }
}
