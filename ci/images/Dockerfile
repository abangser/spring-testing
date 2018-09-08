FROM selenium/base

USER root
RUN sudo -s
RUN apt-get update
RUN apt-get install -y --no-install-recommends \
	wget \
	gnupg \
	curl \
	ca-certificates \
	bzip2 \
	git \
	lsof \
	libncurses5

#Install Java8 JDK
RUN apt-get install -y --no-install-recommends software-properties-common
RUN add-apt-repository -y ppa:openjdk-r/ppa
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
#RUN apt-get install -y openjdk-8-jre
#RUN update-alternatives --config java
#RUN update-alternatives --config javac

#Install Google Chrome
#https://www.google.com/linuxrepositories/
ARG CHROME_VERSION="google-chrome-stable"
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update -qqy \
    && apt-get -qqy install \
        ${CHROME_VERSION:-google-chrome-stable} \
    && rm /etc/apt/sources.list.d/google-chrome.list \
    && rm -rf /var/lib/apt/lists/* /var/cache/apt/*

#RUN Xvfb :99 -ac -screen 0 1280x1024x24 &
#ENV DISPLAY=:99
#RUN nice -n 10 x11vnc 2>&1 &

#============================== # Scripts to run Selenium Node #==============================
USER seluser
ENV SCREEN_WIDTH 1360
ENV SCREEN_HEIGHT 1020
ENV SCREEN_DEPTH 24
ENV DISPLAY :99.0

#ENV NODE_MAX_INSTANCES 1
#ENV NODE_MAX_SESSION 1
#ENV NODE_PORT 5555
#ENV NODE_REGISTER_CYCLE 5000
#ENV NODE_POLLING 5000
#ENV NODE_UNREGISTER_IF_STILL_DOWN_AFTER 60000
#ENV NODE_DOWN_POLLING_LIMIT 2
#ENV NODE_APPLICATION_NAME ""
#ENV DBUS_SESSION_BUS_ADDRESS=/dev/null
