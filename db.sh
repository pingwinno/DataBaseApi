#!/bin/sh

# Setup variables
EXEC=/usr/bin/jsvc
JAVA_HOME="$( readlink -f "$( which java )" | sed "s:bin/.*$::" )"
CLASS_PATH="/usr/share/java/commons-daemon.jar":"/usr/local/DataBaseApi/DataBaseApi.jar"
CLASS=com.pingwinno.Main
USER=db-daemon
PID=/tmp/db.pid
LOG_OUT=/tmp/db.out
LOG_ERR=/tmp/db.err

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
