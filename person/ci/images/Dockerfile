FROM selenium/base

USER root
RUN sudo -s
RUN apt-get update
RUN apt-get install -y --no-install-recommends \
	wget \
	git \
	openjdk-8-jdk

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

#============================== # Scripts to run Selenium Node #==============================
USER seluser
ENV SCREEN_WIDTH 1360
ENV SCREEN_HEIGHT 1020
ENV SCREEN_DEPTH 24
ENV DISPLAY :99.0
