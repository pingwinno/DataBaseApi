package net.streamarchive.dbapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface StreamsRepository extends JpaRepository<Stream, UUID> {
    List<Stream> findAllByStreamer(String streamer);
}
