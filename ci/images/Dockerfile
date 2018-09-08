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
RUN apt-get install -y openjdk-8-jre
RUN update-alternatives --config java
RUN update-alternatives --config javac

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
USER seluser
ARG CHROME_DRIVER_VERSION="latest"
RUN CD_VERSION=$(if [ ${CHROME_DRIVER_VERSION:-latest} = "latest" ]; then echo $(wget -qO- https://chromedriver.storage.googleapis.com/LATEST_RELEASE); else echo $CHROME_DRIVER_VERSION; fi) \
    && echo "Using chromedriver version: "$CD_VERSION \
    && wget --no-verbose -O /tmp/chromedriver_linux64.zip https://chromedriver.storage.googleapis.com/$CD_VERSION/chromedriver_linux64.zip \
    && rm -rf /opt/selenium/chromedriver \
    && unzip /tmp/chromedriver_linux64.zip -d /opt/selenium \
    && rm /tmp/chromedriver_linux64.zip \
    && mv /opt/selenium/chromedriver /opt/selenium/chromedriver-$CD_VERSION \
    && chmod 755 /opt/selenium/chromedriver-$CD_VERSION \
    && sudo ln -fs /opt/selenium/chromedriver-$CD_VERSION /usr/bin/chromedriver
COPY generate_config /opt/bin/generate_config
COPY chrome_launcher.sh /opt/google/chrome/google-chrome
RUN /opt/bin/generate_config > /opt/selenium/config.json

#============================== # Scripts to run Selenium Node #==============================
USER seluser
ENV SCREEN_WIDTH 1360
ENV SCREEN_HEIGHT 1020
ENV SCREEN_DEPTH 24
ENV DISPLAY :99.0

ENV NODE_MAX_INSTANCES 1
ENV NODE_MAX_SESSION 1
ENV NODE_PORT 5555
ENV NODE_REGISTER_CYCLE 5000
ENV NODE_POLLING 5000
ENV NODE_UNREGISTER_IF_STILL_DOWN_AFTER 60000
ENV NODE_DOWN_POLLING_LIMIT 2
ENV NODE_APPLICATION_NAME ""
ENV DBUS_SESSION_BUS_ADDRESS=/dev/null
