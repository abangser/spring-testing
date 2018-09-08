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

#Install Google Chrome
#https://www.google.com/linuxrepositories/
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
RUN apt-get update
RUN apt-get install -y --no-install-recommends google-chrome-stable

#Install Java8 JDK
RUN apt-get install -y --no-install-recommends software-properties-common
RUN add-apt-repository -y ppa:openjdk-r/ppa
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
RUN apt-get install -y openjdk-8-jre
RUN update-alternatives --config java
RUN update-alternatives --config javac