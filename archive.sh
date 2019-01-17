#!/bin/sh

# Setup variables
EXEC=/usr/bin/jsvc
JAVA_HOME="$( readlink -f "$( which java )" | sed "s:bin/.*$::" )"
CLASS_PATH="/usr/share/java/commons-daemon.jar":"/usr/local/StreamArchiveBackend/ArchivePortal.jar"
CLASS=com.pingwinno.Main
USER=archive-daemon
PID=/tmp/archive.pid
LOG_OUT=/tmp/archive.out
LOG_ERR=/tmp/archive.err

do_exec()
{
    $EXEC -home "$JAVA_HOME" -cp $CLASS_PATH -user $USER -outfile $LOG_OUT -errfile $LOG_ERR -pidfile $PID $1 $CLASS
}

case "$1" in
    start)
        do_exec
            ;;
    stop)
        do_exec "-stop"
            ;;
    restart)
        if [ -f "$PID" ]; then
            do_exec "-stop"
            do_exec
        else
            echo "service not running, will do nothing"
            exit 1
        fi
            ;;
    *)
            echo "usage: daemon {start|stop|restart}" >&2
            exit 3
            ;;
esac
