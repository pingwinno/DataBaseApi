package net.streamarchive.dbapi;

import javax.persistence.Id;

public class Streamer {
    @Id
    String name;
    String endpoint;
}
