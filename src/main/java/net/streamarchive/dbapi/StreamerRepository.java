package net.streamarchive.dbapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamerRepository extends JpaRepository<Streamer, String> {
}
