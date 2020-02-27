# DataBaseApi
Api for streamarchive db. 

It contains ReadOnly method + search engine (Hibernate search)

API Endpoints:
-
Endpoint      | Description      | Params
------------- | -------------    | ------------- 
/streamers    |get all streamers | None
/streams      |get streams of streamer | required:'streamer' optional:'page'(default=0),'size'(default=20),'sort'(default=desc),'order_by(default=date)
/streams/{_id}| get specified stream| None
/streams/search | search streams on field 'title' and 'game' | required:'streamer','query'

Example of response entity
-
    [{"date":"2020-02-25T12:30:24.000+0000",
    "title":"ПОКРИНЖУЙ СО МНОЙ",
    "game":"Just Chatting",
    "duration":14970,
    "_id":"b3b848a4-4b83-4321-b18e-e00a3023caf6"},...]
