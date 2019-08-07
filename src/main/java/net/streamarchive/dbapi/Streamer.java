package net.streamarchive.dbapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STREAMERS")
public class Streamer {
    @Id
    private String streamerName;
    private String storageEndpoint;

    public String getStreamerName() {
        return streamerName;
    }

    public void setStreamerName(String streamerName) {
        this.streamerName = streamerName;
    }

    public String getStorageEndpoint() {
        return storageEndpoint;
    }

    public void setStorageEndpoint(String storageEndpoint) {
        this.storageEndpoint = storageEndpoint;
    }
}
