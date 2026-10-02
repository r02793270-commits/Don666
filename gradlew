#!/usr/bin/env sh

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links - $0 may be a softlink
PRG="$0"

# Need this for relative symlinks.
while [ -h "$PRG" ]; do
    ls=`ls -ld "$PRG"`
    link=`ls -ld "$PRG" | sed -e 's/.*-> //' `
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`/"$link"
    fi
done

SAVED="`pwd`"
CDPATH=
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Use the maximum available, or 6000, if we have trouble parsing it.
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/sh/java" ] ; then
        # IBM's JDK on AIX uses sh/java, not bin/java
        JAVACMD="$JAVA_HOME/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Increase the maximum file descriptors if we can.
case "`uname`" in
    Darwin* | BSD* )
        PRG="$0"
        PRGDIR=`dirname "$PRG"`
        MAX_FD=`launchctl limit maxfiles 2>/dev/null | awk '{print $2}'`
        if [ -n "$MAX_FD" ] && [ "$MAX_FD" != "unlimited" ] ; then
            ulimit -n $MAX_FD
        fi
        ;;
esac

# For Darwin, add options to specify how the application icon is displayed
if [ -n "$TERM" ] ; then
    case "`uname`" in
        Darwin* )
            GRADLE_OPTS="$GRADLE_OPTS -Dapple.awt.UIElement=true"
            ;;
    esac
fi

# Escape application args
exec "$JAVACMD" $DEFAULT_JVM_OPTS$GRADLE_OPTS "-classpath" "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
