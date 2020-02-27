package net.streamarchive.dbapi;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StreamsRepository extends JpaRepository<Stream, UUID> {
    List<Stream> findAllByStreamerName(String streamer, Pageable pageable);
}
